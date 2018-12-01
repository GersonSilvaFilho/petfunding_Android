package com.gersonsilvafilho.petfunding.mypets.expandable

import android.support.v7.widget.CardView
import android.support.v7.widget.PopupMenu
import android.view.View
import com.ericliu.asyncexpandablelist.async.AsyncExpandableListView
import com.ericliu.asyncexpandablelist.async.AsyncHeaderViewHolder
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.User
import com.jakewharton.rxbinding3.view.clicks
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.mypet_item.view.firstLine
import kotlinx.android.synthetic.main.mypet_item.view.likedListImage
import kotlinx.android.synthetic.main.mypet_item.view.secondLine
import kotlinx.android.synthetic.main.mypet_item.view.textViewOptions


/**
 * Created by GersonSilva on 6/2/17.
 */
class MyPetsParentViewHolder(itemView: View, groupOrdinal: Int, asyncExpandableListView: AsyncExpandableListView<Pet, User>) : AsyncHeaderViewHolder(
    itemView,
    groupOrdinal,
    asyncExpandableListView
), AsyncExpandableListView.OnGroupStateChangeListener {
    override fun onGroupCollapsed() {
    }

    override fun onGroupExpanded() {
    }

    override fun onGroupStartExpending() {

    }

    val mAsyncExpandableListView: AsyncExpandableListView<Pet, User> = asyncExpandableListView


    fun setPet(pet: Pet, listener: (Pet) -> Unit, editListener: (Pet) -> Unit, groupOrdinal: Int) = with(itemView) {

        itemView.findViewById<CardView>(R.id.cardMypets).setOnClickListener {
            mAsyncExpandableListView.onGroupClicked(groupOrdinal)
        }
        firstLine.text = pet.name
        secondLine.text = pet.contactName
        Picasso.with(this.context)
                .load(pet.photosUrl[0])
                .into(likedListImage)
        textViewOptions.clicks().subscribe {
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
                    R.id.action_chat ->
                    {
                        mAsyncExpandableListView.onGroupClicked(groupOrdinal)
                    }
                }
                false
            }
            popup.show()}
    }



}