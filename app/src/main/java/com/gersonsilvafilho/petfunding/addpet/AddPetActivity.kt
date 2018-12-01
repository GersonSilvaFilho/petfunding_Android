package com.gersonsilvafilho.petfunding.addpet

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.addpet.fragments.AboutAddFragment
import com.gersonsilvafilho.petfunding.addpet.fragments.ConditionAddFragment
import com.gersonsilvafilho.petfunding.addpet.fragments.ContactAddFragment
import com.gersonsilvafilho.petfunding.addpet.fragments.InfoAddFragment
import com.gersonsilvafilho.petfunding.chat.AddPetModule
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.util.PetApplication
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_add_pet.*
import org.jetbrains.anko.contentView
import java.util.*
import javax.inject.Inject


class AddPetActivity : AppCompatActivity(), AddPetContract.View {

    override fun saveButtonClick(): Observable<Unit> = addPetButtonSave.clicks()

    @Inject
    lateinit var mActionsListener: AddPetContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pet)

        val pet = intent.getSerializableExtra("pet") as Pet?

        initDagger(pet)

        setupToolbar()
        setupViewPager(viewpager, pet)
        tabs.setupWithViewPager(viewpager)
    }

    private fun initDagger(pet:Pet?)
    {
        (application as PetApplication).get(this)
                .getUserComponent()!!
                .plus(AddPetModule(this, pet))
                .inject(this)
    }

    private fun setupToolbar()
    {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle("Adicionar PET")
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViewPager(viewPager: ViewPager, pet:Pet?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AboutAddFragment(mActionsListener, pet), "Info")
        adapter.addFragment(InfoAddFragment(mActionsListener, pet), "Dados")
        adapter.addFragment(ConditionAddFragment(mActionsListener, pet), "Condição")
        adapter.addFragment(ContactAddFragment(mActionsListener, pet), "Contato")
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

    override fun showTab(tabId: Int) {
        tabs.getTabAt(tabId)!!.select()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home)
        {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}