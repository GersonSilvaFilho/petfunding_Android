package com.gersonsilvafilho.petfunding.main

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.squareup.picasso.Picasso

class CardsDataAdapter(context: Context, @LayoutRes resource: Int) : ArrayAdapter<Pet>(context, resource) {

    override fun getView(position: Int, contentView: View?, parent: ViewGroup): View {

        val imageView = contentView!!.findViewById(R.id.card_image) as ImageView
        Picasso.with(this.context)
                .load(getItem(position).photosUrl.get(0))
                .into(imageView)

        val nameTextView = contentView!!.findViewById(R.id.textViewCardName) as TextView
        nameTextView.text = getItem(position).name

        return contentView!!
    }

}