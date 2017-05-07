package com.gersonsilvafilho.petfunding.add_pet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import kotlinx.android.synthetic.main.about_add_fragment.*


class AboutAddFragment(private val presenter: AddPetContract.Presenter) : Fragment(), AddPetContract.ViewAbout {

    override fun nameChanges(): Observable<CharSequence> = addEditTextName.textChanges()
    override fun descriptionChanges(): Observable<CharSequence> = addEditTextDescription.textChanges()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.about_add_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
//        addEditTextName.textChanges().subscribe { a -> Log.d("OPA", a.toString())  }

        presenter.init(this)
    }

}// Required empty public constructor