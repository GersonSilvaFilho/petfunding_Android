package com.gersonsilvafilho.petfunding.addpet.fragments

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
import com.gersonsilvafilho.petfunding.addpet.AddPetContract
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.about_add_fragment.addEditTextDescription
import kotlinx.android.synthetic.main.about_add_fragment.addEditTextName
import kotlinx.android.synthetic.main.about_add_fragment.image1
import kotlinx.android.synthetic.main.about_add_fragment.image2
import kotlinx.android.synthetic.main.about_add_fragment.image3
import kotlinx.android.synthetic.main.about_add_fragment.image4


class AboutAddFragment(private val presenter: AddPetContract.Presenter,val pet:Pet?) : Fragment(), AddPetContract.ViewAbout {

    var imageViews:ArrayList<ImageView> = ArrayList<ImageView>()

    override fun nameChanges(): Observable<CharSequence> = addEditTextName.textChanges()
    override fun descriptionChanges(): Observable<CharSequence> = addEditTextDescription.textChanges()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.about_add_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageViews.add(image1 as ImageView)
        imageViews.add(image2 as ImageView)
        imageViews.add(image3 as ImageView)
        imageViews.add(image4 as ImageView)

        for ((index, value) in imageViews.withIndex())
        {
            value.clicks().subscribe { v(index) }
        }

        if(pet != null)
        {
            addEditTextName.setText(pet.name)
            addEditTextDescription.setText(pet.description)

            for ((index, image) in pet.photosUrl.take(4).withIndex())
            {
                Picasso.get()
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
            .subscribe { response ->
                    Log.i("RXPAPARAZZO", response.resultCode().toString())
                    // See response.resultCode() doc
                    if (response.resultCode() == RESULT_OK) {
                        if (response.data()?.file != null) {
                            Picasso.get()
                                .load(response.data().file)
                                .into(imageViews[num])
                            presenter.imageReady(num, response.data().file)
                        }

                    }
            }
    }


    override fun onResume() {
        super.onResume()
        presenter.initAbout(this)
    }

    override fun showInvalidName() {
        addEditTextName.setError("Nome inválido")
        addEditTextDescription.selectAll()
    }

    override fun showInvalidDescription() {
        addEditTextDescription.setError("Descrição inválida")
        addEditTextDescription.selectAll()
    }

    override fun showInvalidPhotosMessage() {
        Snackbar.make(this.view!!, "Seu pet deve ter uma ou mais fotos!", Snackbar.LENGTH_LONG).show()
    }
}