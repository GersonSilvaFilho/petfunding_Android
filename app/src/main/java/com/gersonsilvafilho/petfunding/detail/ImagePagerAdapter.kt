package com.gersonsilvafilho.petfunding.detail

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import com.gersonsilvafilho.petfunding.R
import com.squareup.picasso.Picasso

internal class ImagePagerAdapter(var mContext: Context, var mResources:List<String>) : PagerAdapter() {

    var mLayoutInflater: LayoutInflater

    init {
        mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return mResources.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.image_pager, container, false)

        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        Picasso.with(mContext)
                .load(mResources[position])
                .into(imageView)

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}