package com.gersonsilvafilho.petfunding.util

import android.content.Context
import android.support.v7.widget.CardView
import org.jetbrains.anko.backgroundColor

/**
 * Created by GersonSilva on 5/27/17.
 */
class CardViewWithValidation(context: Context?) : CardView(context)
{
    var normalBackgroundColor:Int? = null

    fun OnError()
    {
        normalBackgroundColor = this.backgroundColor
        this.backgroundColor = 0xff0000
        this.setOnFocusChangeListener { v, hasFocus ->
            if(this.isFocused)
            {
                this.backgroundColor = normalBackgroundColor!!
            }
        }
    }
}