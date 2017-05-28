package com.gersonsilvafilho.petfunding.add_pet.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.add_pet.AddPetContract
import com.jakewharton.rxbinding2.widget.textChanges
import com.jaredrummler.materialspinner.MaterialSpinner
import io.reactivex.Observable
import kotlinx.android.synthetic.main.contact_add_fragment.*


class ContactAddFragment(private val presenter: AddPetContract.Presenter) : Fragment(), AddPetContract.ViewContact {

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

    override fun onResume() {
        super.onResume()
        presenter.initContact(this)
    }

    override fun ufChanges(): Observable<CharSequence> = spinnerState.textChanges()

    override fun cityChanges(): Observable<CharSequence> = spinnerCity.textChanges()

    override fun contactNameChanges(): Observable<CharSequence> = editTextContactName.textChanges()

    override fun contactPhoneChanges(): Observable<CharSequence> = editTextContactPhone.textChanges()

    override fun ongChanges(): Observable<CharSequence> = spinnerOng.textChanges()

    override fun setContactNameError() {
        Snackbar.make(this.view!!, "Insira o nome do contato", Snackbar.LENGTH_LONG).show()
    }

    override fun setContactPhoneError() {
        Snackbar.make(this.view!!, "Insira o telefone do contato", Snackbar.LENGTH_LONG).show()
    }

}