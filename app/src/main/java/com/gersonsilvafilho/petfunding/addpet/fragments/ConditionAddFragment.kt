package com.gersonsilvafilho.petfunding.addpet.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.addpet.AddPetContract
import com.gersonsilvafilho.petfunding.model.pet.Pet
import io.reactivex.Observable
import kotlinx.android.synthetic.main.condition_add_fragment.*


class ConditionAddFragment(private val presenter: AddPetContract.Presenter,val pet: Pet?)  : Fragment(), AddPetContract.ViewCondition {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.condition_add_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.initCondition(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(pet != null)
        {
            var stateList = ArrayList<String>()
            if (pet.vaccinated) stateList.add("Vacinado")
            if (pet.castrated) stateList.add("Castrado")
            if (pet.dewormed) stateList.add("Desverminado")
            group_choices_state.setObjectsWithNames(stateList)

            var likeList = ArrayList<String>()
            if (pet.likeChildren) likeList.add("Crianças")
            if (pet.likeAnimals) likeList.add("Outros Animais")
            if (pet.likeElders) likeList.add("Idosos")
            group_choices_like.setObjectsWithNames(likeList)

            var specialList = ArrayList<String>()
            if (pet.hasLocomotionProblems) specialList.add("Problema Físico")
            if (pet.blind) specialList.add("Cego")
            if (pet.hasBadBehaviour) specialList.add("Comportamento")
            group_choices_like.setObjectsWithNames(specialList)

            group_choices_person.setObjectsWithNames(pet.behaviour)
        }
    }

    override fun stateChanges(): Observable<List<String>> = group_choices_state.OnCheckedStateChangeListener()
    override fun likeChanges(): Observable<List<String>> = group_choices_like.OnCheckedStateChangeListener()
    override fun specialNeedsChanges(): Observable<List<String>> = group_choices_special.OnCheckedStateChangeListener()
    override fun personalityChanges(): Observable<List<String>> = group_choices_person.OnCheckedStateChangeListener()

}