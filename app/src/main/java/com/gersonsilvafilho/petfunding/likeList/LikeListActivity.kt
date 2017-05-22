package com.gersonsilvafilho.petfunding.likeList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import com.gersonsilvafilho.petfunding.util.PetApplication
import kotlinx.android.synthetic.main.activity_like_list.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class LikeListActivity : AppCompatActivity(), LikeListContract.View {

    private lateinit var  mLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mUserRepository: UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_list)
        initDagger()

        mLayoutManager = LinearLayoutManager(this)
        recyclerLikes.setLayoutManager(mLayoutManager)

        // define an adapter
        val mAdapter = LikeListAdapter(mUserRepository.getAllMatches())
        {
            toast("${it.chatId} Clicked")
        }

        recyclerLikes.adapter = mAdapter
    }

    private fun initDagger()
    {
        (application as PetApplication).get(this)
                .getUserComponent()!!
                .plus(LikeListModule(this))
                .inject(this)
    }
}
