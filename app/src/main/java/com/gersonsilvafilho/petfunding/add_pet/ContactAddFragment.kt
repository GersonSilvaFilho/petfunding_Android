package com.gersonsilvafilho.petfunding.add_pet

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.contact_add_fragment.*


class ContactAddFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.contact_add_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        spinnerState.setItems("PR","SP")
        spinnerState.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item -> Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show() })

        spinnerCity.setItems("Curitiba", "Pinhais", "São Paulo")
        spinnerCity.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item -> Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show() })

        spinnerOng.setItems("Animalia", "AmigoBicho", "Amigo Animal", "SPA", "Cãopanheiro")
        spinnerOng.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item -> Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show() })

    }

}// Required empty public constructor