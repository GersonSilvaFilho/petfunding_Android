package com.gersonsilvafilho.petfunding.add_pet.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.add_pet.AddPetContract
import io.reactivex.Observable
import kotlinx.android.synthetic.main.condition_add_fragment.*


class ConditionAddFragment(private val presenter: AddPetContract.Presenter)  : Fragment(), AddPetContract.ViewCondition {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.condition_add_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.initCondition(this)
    }

    override fun stateChanges(): Observable<List<String>> = group_choices_state.OnCheckedStateChangeListener()
    override fun likeChanges(): Observable<List<String>> = group_choices_like.OnCheckedStateChangeListener()
    override fun specialNeedsChanges(): Observable<List<String>> = group_choices_special.OnCheckedStateChangeListener()
    override fun personalityChanges(): Observable<List<String>> = group_choices_person.OnCheckedStateChangeListener()

}