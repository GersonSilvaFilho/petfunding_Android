package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.chat.Chat
import com.gersonsilvafilho.petfunding.model.chat.ChatRepository
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import durdinapps.rxfirebase2.RxFirebaseChildEvent

/**
 * Created by GersonSilva on 5/12/17.
 */
class ChatPresenter(
    private val view: ChatContract.View,
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository,
    private val matchReposity: MatchReposity
) : ChatContract.Presenter {

    private var mCurrentChatId: String? = null
    private var mCurrentText: String? = null


    private var userId: String? = null

    init {

        userRepository.getCurrentUserId()?.let {
            userId = it
            view.initChatView(it)
        }

        view.onSendMessageClick().map { mCurrentText != null }.subscribe { sendMessage(mCurrentText!!) }
        view.onTextChange().subscribe { s -> mCurrentText = s.toString() }
    }

    override fun initChat(pet: Pet, userId: String?) {
        if (pet.createdBy == userRepository.getCurrentUserId() && !userId.isNullOrEmpty()) {
            matchReposity.getMatch(pet.uid, userId!!).subscribe { match ->
                mCurrentChatId = match.chatId
                chatRepository.getChatFromId(mCurrentChatId!!)
                    .subscribe({ l: Chat ->
                        chatRepository.listenMessages(mCurrentChatId!!).subscribe(
                            { msg ->
                                if (msg.eventType == RxFirebaseChildEvent.EventType.ADDED) {
                                    view.addNewMessage(msg.value)
                                }
                            },
                            { t -> })
                    }, { t -> })
            }
        } else {
            matchReposity.getAllMatches(userId!!)
                .subscribe { matches ->
                    val match = matches.firstOrNull { m -> m.petId == pet.uid }
                    mCurrentChatId = match?.chatId
                    if (mCurrentChatId != null && mCurrentChatId != "") {
                        chatRepository.listenMessages(mCurrentChatId!!)
                            .subscribe({ msg -> view.addNewMessage(msg.value) }, { t -> })

                    } else if (match != null) {
                        chatRepository.initNewChat(match.uid, userId!!)
                            .subscribe { chatIt ->
                                mCurrentChatId = chatIt
                                matchReposity.addChatToMatch(match, chatIt)
                                chatRepository.listenMessages(mCurrentChatId!!).subscribe({ msg -> view.addNewMessage(msg.value) })
                            }

                    }
                }

        }
    }


    override fun sendMessage(message: String) {
        val msg = Message()
        msg.itext = message
        msg.userId = userId!!
        chatRepository.sendMessage(userId!!, msg)
            .subscribe { id ->
                msg.uid = id
                //mView.addNewMessage(msg)
                view.clearMessageBox()
            }
    }
}