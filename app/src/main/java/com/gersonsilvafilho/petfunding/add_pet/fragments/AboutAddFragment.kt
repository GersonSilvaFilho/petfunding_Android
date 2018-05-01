package com.gersonsilvafilho.petfunding.add_pet.fragments

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.add_pet.AddPetContract
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.jakewharton.rxbinding2.view.selected
import com.jakewharton.rxbinding2.widget.textChanges
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.about_add_fragment.*
import org.jetbrains.anko.onClick


class AboutAddFragment(private val presenter: AddPetContract.Presenter,val pet:Pet?) : Fragment(), AddPetContract.ViewAbout {

    var imageViews:ArrayList<ImageView> = ArrayList<ImageView>()

    override fun nameChanges(): Observable<CharSequence> = addEditTextName.textChanges()
    override fun descriptionChanges(): Observable<CharSequence> = addEditTextDescription.textChanges()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.about_add_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageViews.add(image1)
        imageViews.add(image2)
        imageViews.add(image3)
        imageViews.add(image4)

        for ((index, value) in imageViews.withIndex())
        {
            value.onClick { v(index) }
        }

        if(pet != null)
        {
            addEditTextName.setText(pet.name)
            addEditTextDescription.setText(pet.description)

            for ((index, image) in pet.photosUrl.take(4).withIndex())
            {
                Picasso.with(this.activity)
                        .load(image)
                        .into(imageViews[index])
            }
        }
    }

    var v = {num:Int ->
        Log.i("RXPAPARAZZO", "Click")
        RxPaparazzo.single(this)
                .crop()
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.i("RXPAPARAZZO", response.resultCode().toString())
                    // See response.resultCode() doc
                    if (response.resultCode() == RESULT_OK) {
                        if(response.data()?.file != null)
                        {
                            Picasso.with(this.activity)
                                    .load(response.data().file)
                                    .into(imageViews[num])
                            presenter.imageReady(num, response.data().file)
                        }

                    }
                })
    }


    override fun onResume() {
        super.onResume()
        presenter.initAbout(this)
    }

    override fun showInvalidName() {
        addEditTextName.setError("Nome inválido")
        addEditTextDescription.selected()
    }

    override fun showInvalidDescription() {
        addEditTextDescription.setError("Descrição inválida")
        addEditTextDescription.selected()
    }

    override fun showInvalidPhotosMessage() {
        Snackbar.make(this.view!!, "Seu pet deve ter uma ou mais fotos!", Snackbar.LENGTH_LONG).show()
    }
}