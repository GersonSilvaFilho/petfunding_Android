package com.gersonsilvafilho.petfunding.myPets

import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.mypet_item.view.*
import org.jetbrains.anko.onClick


/**
 * Created by GersonSilva on 5/22/17.
 */


class MyPetsAdapter(val items: List<Pet>, val listener: (Pet) -> Unit, val editListener: (Pet) -> Unit) : RecyclerView.Adapter<MyPetsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mypet_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, editListener)

    override fun getItemCount() = items.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(pet: Pet, listener: (Pet) -> Unit, editListener: (Pet) -> Unit) = with(itemView) {
            itemView.findViewById(R.id.cardMypets)?.setOnClickListener { listener(pet) }
            firstLine.text = pet.name
            secondLine.text = pet.contactName
            Picasso.with(this.context)
                    .load(pet.photosUrl[0])
                    .into(likedListImage)
            textViewOptions.onClick {
                val popup = PopupMenu(this.context, textViewOptions)
                popup.inflate(R.menu.menu_detail)
                popup.setOnMenuItemClickListener { item ->
                    when (item.getItemId()) {
                        R.id.action_view -> {
                            listener(pet)
                        }
                        R.id.action_edit ->
                        {
                            editListener(pet)
                        }
                    }
                    false
                }
                popup.show()}

        }


    }
}