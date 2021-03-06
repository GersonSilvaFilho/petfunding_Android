package com.gersonsilvafilho.petfunding.detail

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.chat.ChatActivity
import com.gersonsilvafilho.petfunding.detail.fragments.AboutFragment
import com.gersonsilvafilho.petfunding.detail.fragments.ContactFragment
import com.gersonsilvafilho.petfunding.detail.fragments.InfoFragment
import com.gersonsilvafilho.petfunding.detail.fragments.StatusFragment
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.splash.ui.SplashActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.imageviewpager
import kotlinx.android.synthetic.main.activity_detail.tabs
import kotlinx.android.synthetic.main.activity_detail.viewpager
import org.jetbrains.anko.startActivity
import java.util.ArrayList
import javax.inject.Inject


class DetailActivity : AppCompatActivity(), DetailContract.View {

    @Inject
    lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val pet = intent.getSerializableExtra("pet") as Pet

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(pet.name)
        supportActionBar?.setSubtitle("Macho")

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            presenter.onFloatingButtonClicked()
        }

        setupViewPager(viewpager, pet)
        tabs.setupWithViewPager(viewpager)

        val pagerAdapter = ImagePagerAdapter(this, pet.photosUrl)
        imageviewpager.adapter = pagerAdapter
        presenter.onCreate(pet)
    }

    private fun setupViewPager(viewPager: ViewPager, pet:Pet) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AboutFragment(pet), "Info")
        adapter.addFragment(InfoFragment(pet), "Dados")
        adapter.addFragment(StatusFragment(pet), "Condição")
        adapter.addFragment(ContactFragment(pet), "Contato")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList.get(position)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun startChatActivity(pet: Pet, userId: String) {
        startActivity<ChatActivity>("pet" to pet, "userId" to userId)
    }

    override fun startSplashActivity() = startActivity<SplashActivity>()
}
