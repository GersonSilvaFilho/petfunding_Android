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
import java.util.*

class CardsDataAdapter(context:Context, @LayoutRes resource: Int) : ArrayAdapter<Pet>(context, resource) {

    override fun getView(position: Int, contentView: View?, parent: ViewGroup): View {

        val imageView = contentView!!.findViewById(R.id.card_image) as ImageView
        if(!getItem(position).photosUrl.isEmpty())
        {
            Picasso.with(this.context)
                    .load(getItem(position).photosUrl.get(0))
                    .into(imageView)
        }


        val nameTextView = contentView.findViewById(R.id.textViewCardName) as TextView

        var ageString = ""
        val today = Date()
        val monthsTotal = getMonthDiff(getItem(position).birthDate, today)
        val years = monthsTotal / 12
        if(years > 0)
        {
            ageString = (years.toString() + " Ano")
            ageString +=  (if(years > 1) "s" else "")
        }
        val months = monthsTotal % 12
        if(months > 0)
        {
            if(ageString.isNotEmpty())
            {
                ageString += (years.toString() + ", ")
            }

            ageString = (months.toString() + " Mes")
            ageString +=  (if(months > 1) "es," else "")
        }

        nameTextView.text = getItem(position).name + ", " + ageString
        return contentView
    }

    private fun getMonthDiff(startDate:Date, endDate:Date):Int
    {
        val startCalendar = GregorianCalendar()
        startCalendar.time = startDate
        val endCalendar = GregorianCalendar()
        endCalendar.time = endDate

        val diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)
        return diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH)
    }

}