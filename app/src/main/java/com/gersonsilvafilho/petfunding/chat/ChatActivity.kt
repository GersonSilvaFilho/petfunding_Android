package com.gersonsilvafilho.petfunding.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.message.Message
import com.gersonsilvafilho.petfunding.model.user.User
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*


class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val imageLoader = object : ImageLoader {
            override fun loadImage(imageView: ImageView, url: String) {
                Picasso.with(imageView.context).load(url).into(imageView)
            }
        }

        val message = Message()
        message.itext = "Oi"
        message.date = Date()
        message.user = User()

        val adapter = MessagesListAdapter<Message>("jV7gZgMG4paP2p8OpRURMeLKFY73", imageLoader)
        messagesList.setAdapter(adapter)
        adapter.addToStart(message, true)


        input.setInputListener {
            input ->
            val message = Message()
            message.itext = input.toString()
            message.date = Date()
            val use =  User()
            use.username = "Gerson"
            use.uid = "jV7gZgMG4paP2p8OpRURMeLKFY73"
            message.user = use
            adapter.addToStart(message, true)
            true
        }
    }
}
