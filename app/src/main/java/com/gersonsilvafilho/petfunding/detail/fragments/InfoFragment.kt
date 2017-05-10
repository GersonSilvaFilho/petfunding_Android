package com.gersonsilvafilho.petfunding.detail.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.pet.Pet
import kotlinx.android.synthetic.main.info_fragment.*


class InfoFragment (val mPet: Pet): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.info_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        textViewDetailSize.text = mPet.size
        textViewDetailSex.text = mPet.sex
        textViewDetailSize.text = mPet.size
//        textViewDetailFurColor.text = mPet.furColors[0]
        textViewDetailLikeChildren.text = (if(mPet.likeChildren) "Sim" else "Não")
        textViewDetailOtherAnimals.text = (if(mPet.likeAnimals) "Sim" else "Não")

    }

}// Required empty public constructor