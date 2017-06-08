package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.chat.Chat
import com.gersonsilvafilho.petfunding.model.chat.ChatRepository
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import durdinapps.rxfirebase2.RxFirebaseChildEvent

/**
 * Created by GersonSilva on 5/12/17.
 */
class ChatPresenter : ChatContract.Presenter
{
    var mChatRepository: ChatRepository
    var mUserRepository: UserRepository
    var mMatchRepository: MatchReposity
    var mPetRepository: PetRepository

    private var mView: ChatContract.View

    private var  mCurrentChatId:String? = null
    private var  mCurrentText:String? = null


    constructor(chatView: ChatContract.View, chatRepository: ChatRepository, userRepository: UserRepository, matchReposity: MatchReposity,petRepository: PetRepository)
    {
        //initDagger()
        mView = chatView
        mChatRepository = chatRepository
        mUserRepository = userRepository
        mMatchRepository = matchReposity
        mPetRepository = petRepository

        mView.initChatView(mUserRepository.getCurrentUserId())

        mView.onSendMessageClick().map { mCurrentText != null }.subscribe { sendMessage(mCurrentText!!) }
        mView.onTextChange().subscribe { s -> mCurrentText = s.toString() }
    }

    override fun initChat(pet: Pet, userId: String?)
    {
        if(pet.createdBy == mUserRepository.getCurrentUserId() && !userId.isNullOrEmpty()) {
            mMatchRepository.getMatch(pet.uid, userId!!).subscribe { match, t2 ->
                mCurrentChatId = match.chatId
                mChatRepository.getChatFromId(mCurrentChatId!!)
                        .subscribe({ l: Chat ->
                            mChatRepository.listenMessages(mCurrentChatId!!).subscribe(
                                    { msg ->
                                        if(msg.eventType == RxFirebaseChildEvent.EventType.ADDED)
                                        {
                                            mView.addNewMessage(msg.value)
                                        } },
                                    { t -> })
                        }, { t -> })
            }
        }
        else
        {
            mMatchRepository.getAllMatches(mUserRepository.getCurrentUserId())
                    .subscribe { matches ->
                        val matches = matches.filter { m -> m.petId == pet.uid }
                        val match = matches.first()
                        mCurrentChatId = match.chatId
                        if(mCurrentChatId != null && mCurrentChatId != "")
                        {
                            mChatRepository.listenMessages(mCurrentChatId!!)
                                    .subscribe({ msg -> mView.addNewMessage(msg.value)},{ t -> })

                        }
                        else
                        {
                            mChatRepository.initNewChat(match.uid, mUserRepository.getCurrentUserId())
                                    .doAfterSuccess{
                                        mCurrentChatId = it
                                        mMatchRepository.addChatToMatch(match, it)
                                        mChatRepository.listenMessages(mCurrentChatId!!).subscribe({ msg -> mView.addNewMessage(msg.value)},{ t -> })
                                    }
                                    .subscribe { a -> a}
                        }
                    }
        }

    }



    override fun sendMessage(message: String) {
        val msg = Message()
        msg.itext = message
        msg.userId = mUserRepository.getCurrentUserId()
        mChatRepository.sendMessage(mCurrentChatId!!, msg)
                .subscribe { id ->
            msg.uid = id
            //mView.addNewMessage(msg)
            mView.clearMessageBox()
                }
    }
}