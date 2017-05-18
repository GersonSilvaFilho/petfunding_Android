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
    override fun sendMessage(message: String) {
        val msg = Message()
        msg.itext = message
        msg.userId = mUserRepository.getCurrentUserId()
        mChatRepository.sendMessage(mCurrentChatId!!, msg)
    }

    private var  mView: ChatContract.View
    private var  mChatRepository: ChatRepository
    private var  mUserRepository: UserRepository
    private var  mCurrentChatId:String? = null
    private var  mCurrentText:String? = null

    constructor(chatView: ChatContract.View, chatRepository: ChatRepository, userRepository: UserRepository, match: String)
    {
        mView = chatView
        mChatRepository = chatRepository
        mUserRepository = userRepository
        chatRepository.initNewChat(match, userRepository.getCurrentUserId())
                .doOnComplete{
                    mView.initChatView(userRepository.getCurrentUserId())
                    mCurrentChatId = chatRepository.getCurrentChat().uid
                    chatRepository.loadChatMessages(mCurrentChatId!!)
                            .subscribe { l:Chat -> mView.loadChatMessages(l.messages.values.toList()) }
                }
                .subscribe()



        mView.onSendMessageClick().map { mCurrentText != null }.subscribe { sendMessage(mCurrentText!!) }
        mView.onTextChange().subscribe { s -> mCurrentText = s.toString() }
    }

    private fun initChat()
    {

    }
}