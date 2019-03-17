package com.gersonsilvafilho.petfunding.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_chat.input
import kotlinx.android.synthetic.main.activity_chat.messagesList
import javax.inject.Inject


class ChatActivity : AppCompatActivity(), ChatContract.View {


    @Inject
    lateinit var presenter: ChatContract.Presenter

    private lateinit var adapter: MessagesListAdapter<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val pet = intent.getSerializableExtra("pet") as Pet
        val userId = intent.getStringExtra("userId")
        presenter.initChat(pet, userId)
    }

    private fun initDagger()
    {
        AndroidInjection.inject(this)
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

    override fun onSendMessageClick() = input.button.clicks()
    override fun onTextChange() = input.inputEditText.textChanges()

    override fun clearMessageBox() {
        input.inputEditText.setText("")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home)
        {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    val imageLoader = ImageLoader { imageView, url, payload -> Picasso.get().load(url).into(imageView) }
}

