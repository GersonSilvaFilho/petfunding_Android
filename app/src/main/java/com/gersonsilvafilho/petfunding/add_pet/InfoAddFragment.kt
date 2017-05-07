package com.gersonsilvafilho.petfunding.add_pet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.nex3z.togglebuttongroup.button.LabelToggle
import io.reactivex.Observable
import kotlinx.android.synthetic.main.info_add_fragment.*
import java.util.*

class InfoAddFragment(private val presenter: AddPetContract.Presenter) : Fragment(), AddPetContract.ViewInfo {


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
        spinnerYear.setItems("0 Anos", "1 Ano", "2 anos", "3 anos","4 anos", "5 anos", "6 anos", "7 anos", "8 anos", "9 anos", "10 anos", "11 anos", "12 anos")

    }

    override fun typeChanges(): Observable<CharSequence> = group_choices_type.OnCheckedChangeListener()

    override fun sexChanges(): Observable<CharSequence>  = group_choices_sex.OnCheckedChangeListener()

    override fun ageChanges(): Observable<Date> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sizeChanges(): Observable<CharSequence> = group_choices_size.OnCheckedChangeListener()

    override fun furSizeChanges(): Observable<CharSequence> = group_choices_fur.OnCheckedChangeListener()

    override fun furColorChanges(): Observable<List<String>> = group_choices_fur_color.OnCheckedStateChangeListener()

    override fun onResume() {
        super.onResume()
        presenter.initInfo(this)
    }

}

