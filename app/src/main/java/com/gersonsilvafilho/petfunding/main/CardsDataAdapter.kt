package com.gersonsilvafilho.petfunding.main

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class CardsDataAdapter(context: Context, @LayoutRes resource: Int) : ArrayAdapter<String>(context, resource) {

    override fun getView(position: Int, contentView: View?, parent: ViewGroup): View {
        //supply the layout for your card
        return contentView!!
    }

}