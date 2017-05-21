package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.model.chat.Chat
import com.gersonsilvafilho.petfunding.model.chat.ChatRepository
import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import javax.inject.Inject

/**
 * Created by GersonSilva on 5/12/17.
 */
class ChatPresenter : ChatContract.Presenter
{
    @Inject
    lateinit var mChatRepository: ChatRepository
    @Inject
    lateinit var mUserRepository: UserRepository

    lateinit private var mView: ChatContract.View

    private var  mCurrentChatId:String? = null
    private var  mCurrentText:String? = null


    override fun initView(chatView: ChatContract.View, match: String)
    {
        //initDagger()
        mView = chatView
        mView.initChatView(mUserRepository.getCurrentUserId())

        mCurrentChatId = mUserRepository.checkIfChatExists(match)
        if(mCurrentChatId != null)
        {
            mChatRepository.loadChatMessages(mCurrentChatId!!)
                    .subscribe { l:Chat -> mView.loadChatMessages(l.messages.values.toList()) }
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

        mView.onSendMessageClick().map { mCurrentText != null }.subscribe { sendMessage(mCurrentText!!) }
        mView.onTextChange().subscribe { s -> mCurrentText = s.toString() }
    }

    private fun initDagger() {
        DaggerChatComponent
                .builder()
                .build()
                .inject(this)
    }

    override fun sendMessage(message: String) {
        val msg = Message()
        msg.itext = message
        msg.userId = mUserRepository.getCurrentUserId()
        mChatRepository.sendMessage(mCurrentChatId!!, msg).subscribe { id ->
            msg.uid = id
            mView.addNewMessage(msg) }
    }
}