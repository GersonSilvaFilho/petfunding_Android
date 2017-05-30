package com.gersonsilvafilho.petfunding.detail.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.pet.Pet
import kotlinx.android.synthetic.main.status_fragment.*


class StatusFragment (val mPet: Pet): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.status_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        textViewStatusCastrated.text = (if(mPet.castrated) "Sim" else "Não")
        textViewStatusVaccinated.text = (if(mPet.vaccinated) "Sim" else "Não")
        textViewStatusDewormed.text = (if(mPet.dewormed) "Sim" else "Não")


    }

}// Required empty public constructor