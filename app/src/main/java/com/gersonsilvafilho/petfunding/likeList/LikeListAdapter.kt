package com.gersonsilvafilho.petfunding.likeList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.like_item.view.*


/**
 * Created by GersonSilva on 5/22/17.
 */


class LikeListAdapter(val items: List<Pet>, val listener: (Pet) -> Unit) : RecyclerView.Adapter<LikeListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.like_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Pet, listener: (Pet) -> Unit) = with(itemView) {
            setOnClickListener { listener(item) }
            firstLine.text = item.name
            secondLine.text = item.contactName
            Picasso.with(this.context)
                    .load(item.photosUrl[0])
                    .into(likedListImage)
        }
    }
}