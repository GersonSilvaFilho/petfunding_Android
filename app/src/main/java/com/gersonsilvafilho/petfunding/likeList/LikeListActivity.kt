package com.gersonsilvafilho.petfunding.likeList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.detail.DetailActivity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.util.PetApplication
import kotlinx.android.synthetic.main.activity_like_list.*
import kotlinx.android.synthetic.main.content_navigation.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class LikeListActivity : AppCompatActivity(), LikeListContract.View {

    @Inject
    lateinit var  mActionsListener: LikeListContract.Presenter

    private lateinit var  mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_list)
        initDagger()

        mLayoutManager = LinearLayoutManager(this)
        recyclerLikes.setLayoutManager(mLayoutManager)
    }

    private fun initDagger()
    {
        (application as PetApplication).get(this)
                .getUserComponent()!!
                .plus(LikeListModule(this))
                .inject(this)
    }

    override fun setAdapter(likedPets: List<Pet>) {
        val mAdapter = LikeListAdapter(likedPets, onPetClicked())

        recyclerLikes.adapter = mAdapter
    }

    override fun onPetClicked(): (Pet) -> Unit  = {
        mActionsListener.petSelected(it)
    }

    override fun startDetails(pet:Pet)
    {
        startActivity<DetailActivity>("pet" to pet)
    }
}
