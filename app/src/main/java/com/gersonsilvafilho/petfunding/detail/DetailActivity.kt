package com.gersonsilvafilho.petfunding.detail

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.detail.fragments.AboutFragment
import com.gersonsilvafilho.petfunding.detail.fragments.ContactFragment
import com.gersonsilvafilho.petfunding.detail.fragments.InfoFragment
import com.gersonsilvafilho.petfunding.detail.fragments.StatusFragment
import com.gersonsilvafilho.petfunding.model.pet.Pet
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val pet = intent.getSerializableExtra("pet") as Pet

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle(pet.name)
        supportActionBar!!.setSubtitle("Macho")

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        setupViewPager(viewpager, pet)
        tabs.setupWithViewPager(viewpager)

        val pagerAdapter = ImagePagerAdapter(this, pet.photosUrl)
        imageviewpager.adapter = pagerAdapter
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
}
