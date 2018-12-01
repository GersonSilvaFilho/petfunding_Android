package com.gersonsilvafilho.petfunding.detail.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.pet.Pet
import kotlinx.android.synthetic.main.contact_fragment.textViewDetailCity
import kotlinx.android.synthetic.main.contact_fragment.textViewDetailName
import kotlinx.android.synthetic.main.contact_fragment.textViewDetailOng
import kotlinx.android.synthetic.main.contact_fragment.textViewDetailPhone


class ContactFragment (val mPet: Pet): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contact_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textViewDetailName.setText(mPet.contactName)
        textViewDetailPhone.setText(mPet.contactPhone)
        textViewDetailCity.setText(mPet.city)
        textViewDetailOng.setText(mPet.ongName)
    }

}