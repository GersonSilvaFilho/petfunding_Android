package com.gersonsilvafilho.petfunding.myPets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.add_pet.AddPetActivity
import com.gersonsilvafilho.petfunding.detail.DetailActivity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.util.PetApplication
import kotlinx.android.synthetic.main.activity_like_list.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class MyPetsActivity : AppCompatActivity(), MyPetsContract.View {

    @Inject
    lateinit var  mActionsListener: MyPetsContract.Presenter

    private lateinit var  mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_list)
        initDagger()
        setupToolbar()

        mLayoutManager = LinearLayoutManager(this)
        recyclerLikes.setLayoutManager(mLayoutManager)
    }

    private fun initDagger()
    {
        (application as PetApplication).get(this)
                .getUserComponent()!!
                .plus(MyPetsModule(this))
                .inject(this)
    }

    private fun setupToolbar()
    {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setAdapter(likedPets: List<Pet>) {
        val mAdapter = MyPetsAdapter(likedPets, onPetClicked(), onPetEdit())

        recyclerLikes.adapter = mAdapter
    }

    override fun onPetClicked(): (Pet) -> Unit  = {
        mActionsListener.petSelected(it)
    }

    override fun onPetEdit(): (Pet) -> Unit  = {
        mActionsListener.petEdit(it)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home)
        {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun startDetails(pet:Pet)
    {
        startActivity<DetailActivity>("pet" to pet)
    }

    override fun startEditPet(pet:Pet)
    {
        startActivity<AddPetActivity>("pet" to pet)
    }
}
