package com.gersonsilvafilho.petfunding.main

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.add_pet.AddPetActivity
import com.gersonsilvafilho.petfunding.chat.ChatActivity
import com.gersonsilvafilho.petfunding.detail.DetailActivity
import com.gersonsilvafilho.petfunding.likeList.LikeListActivity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.util.PetApplication
import com.jakewharton.rxbinding2.view.clicks
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotlinx.android.synthetic.main.content_navigation.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject



class MainMenuActivity : AppCompatActivity(), MainMenuContract.View , NavigationView.OnNavigationItemSelectedListener, SwipeListener.mClickListener{

    @Inject
    lateinit var  mActionsListener: MainMenuContract.Presenter

    override fun showItsMatchDialog(pet:Pet) {
        val dialog = Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.match_layout)
        val imageView = dialog.findViewById(R.id.imageMatch) as ImageView
        Picasso.with(this)
                .load(pet.photosUrl[0])
                .into(imageView)
        val textView = dialog.findViewById(R.id.textViewDialogName) as TextView
        textView.setText(pet.name + " está muito feliz que você deseja adotá-lo!")

        val button = dialog.findViewById(R.id.buttonMatchMessage) as Button
        button.setOnClickListener { startActivity<ChatActivity>("matchId" to pet.uid) }
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun startDetailActivity(pet: Pet) {
        startActivity<DetailActivity>("pet" to pet)
    }


    override fun updateCardAdapter(pets: List<Pet>) {
        var mCardAdapter = CardsDataAdapter(this, applicationContext, 0)
        mCardAdapter.addAll(pets)
        cardStack.setAdapter(mCardAdapter)
    }



    override fun mClick() {
        ///launch(this, card_full)

        startActivity<DetailActivity>("pet" to cardStack.adapter.getItem(cardStack.currIndex))
        //startActivity<DetailActivity>()
    }

    // Methods inside this block are static
    companion object {
        fun launch(activity: Activity, sharedView: View) {
            val transitionName = activity.resources.getString(R.string.image_transition_name)
            val launcher = Intent(activity, DetailActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedView, transitionName)
            activity.startActivity(launcher, options.toBundle())
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        initDagger()

        cardStack.setContentResource(R.layout.card_layout)
        cardStack.setStackMargin(20)

        val listn = SwipeListener(this)
        cardStack.setListener(listn)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        fabLike.clicks().subscribe {
            cardStack.discardTop(3)
        }
        fabdislike.clicks().subscribe {
            cardStack.discardTop(2)
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        mActionsListener.loadPets()
    }



    private fun initDagger()
    {
        (application as PetApplication).get(this)
                .getUserComponent()!!
                .plus(MainMenuModule(this))
                .inject(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_main) {

        }
        else if (id == R.id.nav_likes) {
            startActivity<LikeListActivity>()
            //Add new pet activity

        }
        else if (id == R.id.nav_add) {
            startActivity<AddPetActivity>()
            //Add new pet activity

        } else if (id == R.id.nav_logout) {
            mActionsListener.userLogout()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun cardDiscartedRight(cardId: Int) {
        mActionsListener.userMatchedPet((cardStack.adapter.getItem(cardStack.currIndex - 1) as Pet))
    }

    override fun cardDiscartedLeft(cardId: Int) {
        mActionsListener.userUnmatchedPet((cardStack.adapter.getItem(cardStack.currIndex - 1) as Pet))
    }
}
