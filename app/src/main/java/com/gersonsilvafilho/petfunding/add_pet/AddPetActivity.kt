package com.gersonsilvafilho.petfunding.add_pet

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.add_pet.fragments.AboutAddFragment
import com.gersonsilvafilho.petfunding.add_pet.fragments.ConditionAddFragment
import com.gersonsilvafilho.petfunding.add_pet.fragments.ContactAddFragment
import com.gersonsilvafilho.petfunding.add_pet.fragments.InfoAddFragment
import com.gersonsilvafilho.petfunding.model.pet.PetFirebaseRepository
import com.gersonsilvafilho.petfunding.model.user.UserFirebaseRepository
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_add_pet.*
import org.jetbrains.anko.contentView
import java.util.*


class AddPetActivity : AppCompatActivity(), AddPetContract.View {


    override fun saveButtonClick(): Observable<Unit> = addPetButtonSave.clicks()

    lateinit var mActionsListener: AddPetContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pet)

        val toolbar = findViewById(R.id.toolbar) as Toolbar

        toolbar.setTitle("Adicionar PET")
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        mActionsListener = AddPetPresenter(this, PetFirebaseRepository(), UserFirebaseRepository())

        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AboutAddFragment(mActionsListener), "Info")
        adapter.addFragment(InfoAddFragment(mActionsListener), "Dados")
        adapter.addFragment(ConditionAddFragment(mActionsListener), "Condição")
        adapter.addFragment(ContactAddFragment(mActionsListener), "Contato")
        viewPager.adapter = adapter
    }

    override fun finishActivity() {
        finish()
    }

    override fun showSuccessMessage() {
        Snackbar.make(this.contentView!!, "Adicionado com sucesso", Snackbar.LENGTH_LONG)
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
