package com.gersonsilvafilho.petfunding.likelist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.HORIZONTAL
import android.view.MenuItem
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.detail.DetailActivity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_like_list.recyclerLikes
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class LikeListActivity : AppCompatActivity(), LikeListContract.View {

    @Inject
    lateinit var  mActionsListener: LikeListContract.Presenter

    private lateinit var  mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_list)
        setupToolbar()
        mLayoutManager = LinearLayoutManager(this)
        recyclerLikes.setLayoutManager(mLayoutManager)
    }

    private fun setupToolbar()
    {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setAdapter(likedPets: List<Pet>) {
        val mAdapter = LikeListAdapter(likedPets, onPetClicked())

        recyclerLikes.adapter = mAdapter

        val dividerItemDecoration = DividerItemDecoration(this,
                HORIZONTAL)
        recyclerLikes.addItemDecoration(dividerItemDecoration)
    }

    override fun onPetClicked(): (Pet) -> Unit  = {
        mActionsListener.petSelected(it)
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
}
