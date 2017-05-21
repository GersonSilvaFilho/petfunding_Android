package com.gersonsilvafilho.petfunding.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.util.PetApplication
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject


class ChatActivity : AppCompatActivity(), ChatContract.View {

    @Inject
    lateinit var mActionsListener: ChatContract.Presenter

    private lateinit var adapter: MessagesListAdapter<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initDagger()

        val match = intent.getStringExtra("matchId")

        mActionsListener.initView(this, match)
    }

    private fun initDagger()
    {
        (application as PetApplication).getAppComponent().inject(this)
        (application as PetApplication).getAppComponent().inject(mActionsListener)
    }

    override fun initChatView(currentUserId: String) {
        adapter = MessagesListAdapter<Message>(currentUserId, imageLoader)
        messagesList.setAdapter(adapter)

    }

    override fun loadChatMessages(messages: List<Message>)
    {
        adapter.addToEnd(messages, false)
    }

    override fun addNewMessage(message: Message) {
        adapter.addToStart(message, true)
    }

    override fun onSendMessageClick() = input.button.clicks().doOnComplete { input.inputEditText.setText("")  }
    override fun onTextChange() = input.inputEditText.textChanges()

    val imageLoader = object : ImageLoader {
        override fun loadImage(imageView: ImageView, url: String) {
            Picasso.with(imageView.context).load(url).into(imageView)
        }
    }
}

