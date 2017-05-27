package com.gersonsilvafilho.petfunding.add_pet.fragments

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.add_pet.AddPetContract
import com.jakewharton.rxbinding2.widget.textChanges
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.about_add_fragment.*
import org.jetbrains.anko.onClick


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


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        image1.onClick { v(1, image1) }
        image2.onClick { v(2, image2) }
        image3.onClick { v(3, image3) }
        image4.onClick { v(4, image4) }
    }

    var v = {num:Int, view: ImageView ->
        Log.i("RXPAPARAZZO", "Click")
        RxPaparazzo.single(this)
                .crop()
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.i("RXPAPARAZZO", response.resultCode().toString())
                    // See response.resultCode() doc
                    if (response.resultCode() !== RESULT_OK) {

                    }

                    Picasso.with(this.activity)
                            .load(response.data().file)
                            .into(view)
                    presenter.imageReady(num, response.data().file)
                })}

    override fun onResume() {
        super.onResume()
        presenter.initAbout(this)
    }

    override fun showInvalidName() {
        addEditTextName.setError("Nome inválido")
    }


}