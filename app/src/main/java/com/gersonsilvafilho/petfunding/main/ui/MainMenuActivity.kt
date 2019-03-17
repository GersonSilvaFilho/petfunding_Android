package com.gersonsilvafilho.petfunding.main.ui

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
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.addpet.AddPetActivity
import com.gersonsilvafilho.petfunding.chat.ChatActivity
import com.gersonsilvafilho.petfunding.detail.DetailActivity
import com.gersonsilvafilho.petfunding.filter.model.FilterList
import com.gersonsilvafilho.petfunding.likelist.LikeListActivity
import com.gersonsilvafilho.petfunding.main.CardsDataAdapter
import com.gersonsilvafilho.petfunding.main.SwipeListener
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.User
import com.gersonsilvafilho.petfunding.mypets.MyPetsActivity
import com.gersonsilvafilho.petfunding.splash.ui.FilterActivity
import com.gersonsilvafilho.petfunding.splash.ui.SplashActivity
import com.jakewharton.rxbinding3.view.clicks
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.app_bar_navigation.cardStack
import kotlinx.android.synthetic.main.app_bar_navigation.fabLike
import kotlinx.android.synthetic.main.app_bar_navigation.fabdislike
import kotlinx.android.synthetic.main.app_bar_navigation.ripple
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class MainMenuActivity : AppCompatActivity(),
    MainMenuContract.View,
    NavigationView.OnNavigationItemSelectedListener,
    SwipeListener.mClickListener {

    @Inject
    lateinit var presenter: MainMenuContract.Presenter

    private var filterStatus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        cardStack.setContentResource(R.layout.card_layout)

        val listn = SwipeListener(this)
        cardStack.setListener(listn)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        fabLike.clicks().subscribe {
            cardStack.discardTop(3)
        }
        fabdislike.clicks().subscribe {
            cardStack.discardTop(2)
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun showItsMatchDialog(pet: Pet) {
        val dialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.match_layout)
        val imageView = dialog.findViewById<ImageView>(R.id.imageMatch)
        Picasso.get()
            .load(pet.photosUrl[0])
            .into(imageView)
        val textView = dialog.findViewById<TextView>(R.id.textViewDialogName)
        textView.setText(pet.name + " está muito feliz que você deseja adotá-lo!")

        val button = dialog.findViewById<Button>(R.id.buttonMatchMessage)
        button.setOnClickListener { startActivity<ChatActivity>("pet" to pet) }
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun startDetailActivity(pet: Pet) {
        startActivity<DetailActivity>("pet" to pet)
    }

    override fun setDrawerUserInformation(user: User) {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val hView = navigationView.getHeaderView(0)
        val usernameHeaderTextView = (hView.findViewById<TextView>(R.id.usernameHeaderTextView))
        usernameHeaderTextView.text = user.name

        val emailHeaderTextView = (hView.findViewById<TextView>(R.id.emailHeaderTextView))
        emailHeaderTextView.text = user.email

        if (!user.imageUrl.isNullOrEmpty()) {
            val imageView = (hView.findViewById<ImageView>(R.id.headerUserImage))

            Picasso.get()
                .load(user.imageUrl)
                .into(imageView)
        }

    }

    override fun updateCardAdapter(pets: List<Pet>) {
        val mCardAdapter = CardsDataAdapter(applicationContext, 0)
        mCardAdapter.addAll(pets)
        cardStack.setAdapter(mCardAdapter)
        cardStack.reset(true)
    }

    override fun mClick() {
        ///launch(this, card_full)
        startActivity<DetailActivity>("pet" to getCurrentPet())
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_filter) {
            val i = Intent(this, FilterActivity::class.java)
            i.putExtra("filters", presenter.filterList)
            startActivityForResult(i, 999)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

//    private fun toggleFilter() {
//        val viewSize = cardStack.height
//        if (!filterStatus) {
//            val animation = DropDownAnim(filter, viewSize, true)
//            animation.duration = 700
//            filter.startAnimation(animation)
//            filterStatus = true
//        } else {
//            val animation = DropDownAnim(filter, viewSize, false)
//            animation.duration = 700
//            filter.startAnimation(animation)
//            filterStatus = false
//        }
//    }
//
//    override fun hideFilterView() {
//        val viewSize = cardStack.height
//        val animation = DropDownAnim(filter, viewSize, false)
//        animation.duration = 700
//        filter.startAnimation(animation)
//        filterStatus = false
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_main -> {

            }
            R.id.nav_likes -> startActivity<LikeListActivity>()
            //Add new pet activity
            R.id.nav_add -> startActivity<AddPetActivity>()
            //Add new pet activity
            R.id.nav_my_pets -> startActivity<MyPetsActivity>()
            //Add new pet activity
            R.id.nav_logout -> presenter.userLogout()
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    override fun cardDiscartedRight(cardId: Int) {
        presenter.userMatchedPet(getLastPet())
    }

    override fun cardDiscartedLeft(cardId: Int) {
        presenter.userUnmatchedPet(getLastPet())
    }

    private fun getCurrentPet(): Pet {
        return cardStack.adapter.getItem(cardStack.currIndex % cardStack.adapter.count) as Pet
    }

    private fun getLastPet(): Pet {
        return cardStack.adapter.getItem((cardStack.currIndex - 1) % cardStack.adapter.count) as Pet
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

    private fun getActionBarSize(): Int {
        val styledAttributes = theme.obtainStyledAttributes(arrayOf(android.R.attr.actionBarSize).toIntArray())
        val actionBarSize = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()
        return actionBarSize
    }

    private fun getStatusBarHeight(): Int {
        var height = 0
        val idStatusBarHeight = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (idStatusBarHeight > 0) {
            height = getResources().getDimensionPixelSize(idStatusBarHeight)
        } else {
            height = 0
        }
        return height;
    }

    override fun showRippleWaiting() {
        fabLike.visibility = View.GONE
        fabdislike.visibility = View.GONE
        cardStack.visibility = View.GONE
        ripple.visibility = View.VISIBLE
        ripple.startRippleAnimation()
    }

    override fun hideRippleWaiting() {
        fabLike.visibility = View.VISIBLE
        fabdislike.visibility = View.VISIBLE
        fabLike.visibility = View.VISIBLE
        cardStack.visibility = View.VISIBLE
        ripple.visibility = View.GONE
        ripple.stopRippleAnimation()
    }

    override fun startSplashActivity() = startActivity<SplashActivity>()

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 999) {
            val filterList = data?.getSerializableExtra("filters") as FilterList
            Log.d("Result Filter", filterList.toString())
            presenter.loadPets(filterList)
        }

    }
}


