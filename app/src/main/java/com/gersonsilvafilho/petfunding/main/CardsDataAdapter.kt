package com.gersonsilvafilho.petfunding.main

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.util.SquareImageView
import com.gersonsilvafilho.petfunding.util.monthsSinceNow
import com.squareup.picasso.Picasso
import java.util.*

class CardsDataAdapter(context:Context, @LayoutRes resource: Int) : ArrayAdapter<Pet>(context, resource) {

    override fun getView(position: Int, contentView: View?, parent: ViewGroup): View {

        val imageView = contentView!!.findViewById<ImageView>(R.id.card_image)

        val pet = getItem(position)
        if(!pet.photosUrl.isEmpty())
        {
            Picasso.get()
                    .load(pet.photosUrl.get(0))
                    .into(imageView)
        }


        val nameTextView = contentView.findViewById<TextView>(R.id.textViewCardName)

        var ageString = ""
        val today = Date()
        val monthsTotal = pet.birthDate.monthsSinceNow()
        val years = monthsTotal / 12
        if(years > 0)
        {
            ageString = (years.toString() + " Ano")
            ageString +=  (if(years > 1) "s" else "")
        }
        val months = monthsTotal % 12
        if(months > 0 && years < 2)
        {
            if(ageString.isNotEmpty())
            {
                ageString += " e "
            }

            ageString += (months.toString() + " Mes")
            ageString +=  (if(months > 1) "es" else "")
        }

        nameTextView.text = pet.name + ", " + ageString

        val genderImageView = contentView.findViewById<ImageView>(R.id.genderImageView)

        if(pet.sex == "Masculino")
        {
            genderImageView.setImageResource(R.drawable.male_icon)
        }
        else
        {
            genderImageView.setImageResource(R.drawable.female_icon)
        }

        val vaccinated = contentView.findViewById<ImageView>(R.id.vacinnatedImageView)
        vaccinated.visibility = if (pet.vaccinated) VISIBLE else GONE

        val castrated = contentView.findViewById<ImageView>(R.id.castratesImageView)
        castrated.visibility = if (pet.castrated) VISIBLE else GONE

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