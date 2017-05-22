package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.chat.Chat
import com.gersonsilvafilho.petfunding.model.chat.ChatRepository
import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.model.user.UserRepository

/**
 * Created by GersonSilva on 5/12/17.
 */
class ChatPresenter : ChatContract.Presenter
{
    var mChatRepository: ChatRepository
    var mUserRepository: UserRepository

    private var mView: ChatContract.View

    private var  mCurrentChatId:String? = null
    private var  mCurrentText:String? = null


    constructor(chatView: ChatContract.View, chatRepository: ChatRepository, userRepository: UserRepository)
    {
        //initDagger()
        mView = chatView
        mChatRepository = chatRepository
        mUserRepository = userRepository

        mView.initChatView(mUserRepository.getCurrentUserId())

        mView.onSendMessageClick().map { mCurrentText != null }.subscribe { sendMessage(mCurrentText!!) }
        mView.onTextChange().subscribe { s -> mCurrentText = s.toString() }
    }

    override fun initChat(match:String)
    {
        mCurrentChatId = mUserRepository.checkIfChatExists(match)
        if(mCurrentChatId != null && mCurrentChatId != "")
        {
            mChatRepository.loadChatMessages(mCurrentChatId!!)
                    .subscribe { l:Chat -> mView.loadChatMessages(l.messages.values.toList().sortedByDescending { m -> m.date }) }
        }
        else
        {
            mChatRepository.initNewChat(match, mUserRepository.getCurrentUserId())
                    .doOnComplete{

                        mCurrentChatId = mChatRepository.getCurrentChat().uid
                        mChatRepository.loadChatMessages(mCurrentChatId!!)
                                .subscribe { l:Chat -> mView.loadChatMessages(l.messages.values.toList()) }
                    }
                    .subscribe()
        }
    }



    override fun sendMessage(message: String) {
        val msg = Message()
        msg.itext = message
        msg.userId = mUserRepository.getCurrentUserId()
        mChatRepository.sendMessage(mCurrentChatId!!, msg)
                .subscribe { id ->
            msg.uid = id
            mView.addNewMessage(msg)
            mView.clearMessageBox()
            }
    }
}