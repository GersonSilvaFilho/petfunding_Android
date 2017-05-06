package com.gersonsilvafilho.petfunding.add_pet

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.info_add_fragment.*


class InfoAddFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.info_add_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        spinnerMonth.setItems("1 Mês", "2 Meses", "3 Meses","4 Meses", "5 Meses", "6 Meses", "7 Meses", "8 Meses", "9 Meses", "10 Meses", "11 Meses")
        spinnerMonth.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item -> Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show() })

        spinnerYear.setItems("0 Anos", "1 Ano", "2 anos", "3 anos","4 anos", "5 anos", "6 anos", "7 anos", "8 anos", "9 anos", "10 anos", "11 anos", "12 anos")
        spinnerYear.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item -> Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show() })

    }

}// Required empty public constructor