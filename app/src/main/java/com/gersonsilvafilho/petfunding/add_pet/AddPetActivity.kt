package com.gersonsilvafilho.petfunding.add_pet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.gersonsilvafilho.petfunding.R
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_add_pet.*
import java.util.*


class AddPetActivity : AppCompatActivity(), AddPetContract.View {

    override fun saveButtonClick(): Observable<Unit> = addPetButtonSave.clicks()

    lateinit var mActionsListener: AddPetContract.Presenter

    private lateinit var aboutFrag:AboutAddFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pet)

        val toolbar = findViewById(R.id.toolbar) as Toolbar

        toolbar.setTitle("Adicionar PET")
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        mActionsListener = AddPetPresenter(this)
        aboutFrag = AboutAddFragment(mActionsListener)

        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(aboutFrag, "Info")
        adapter.addFragment(InfoAddFragment(), "Dados")
        adapter.addFragment(ConditionAddFragment(), "Condição")
        adapter.addFragment(ContactAddFragment(), "Contato")
        viewPager.adapter = adapter
    }

//    override fun descriptionChanges(): Observable<CharSequence> = addEditTextDescription.textChanges()
//    override fun typeChanges(): Observable<CharSequence> = group_choices_type.OnCheckedChangeListener()
//
//    override fun sexChanges(): Observable<CharSequence>  = addEditTextDescription.textChanges()
//
//    override fun birthChanges(): Observable<Date> = addEditTextDescription.textChanges().map{ t -> Date()}
//
//    override fun sizeChanges(): Observable<CharSequence> = addEditTextDescription.textChanges()
//
//    override fun furSizeChanges(): Observable<CharSequence> = addEditTextDescription.textChanges()
//
//    override fun getPetFunColor(): Observable<List<String>> = addEditTextDescription.textChanges().map{ t -> listOf("test", "test")}
//
//    override fun isPetVaccinated(): Observable<Boolean> = addEditTextDescription.textChanges().map{ t -> true}

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


    fun com.nex3z.togglebuttongroup.SingleSelectToggleGroup.OnCheckedChangeListener() : Observable<CharSequence> {
        return Observable.defer<CharSequence> {
            Observable.create {
                if (!it.isDisposed) {

                    setOnCheckedChangeListener { group, checkedId ->  it.onNext((group as TextView).text)}
                }
            }}
    }
}
