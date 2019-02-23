package com.gersonsilvafilho.petfunding.splash.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.CallbackManager
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.addpet.fragments.getSelectedList
import com.gersonsilvafilho.petfunding.filter.model.FilterList
import com.gersonsilvafilho.petfunding.splash.dagger.FilterModule
import com.gersonsilvafilho.petfunding.util.PetApplication
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_filter.applyFilterButton
import kotlinx.android.synthetic.main.activity_filter.group_choices_age
import kotlinx.android.synthetic.main.activity_filter.group_choices_condition
import kotlinx.android.synthetic.main.activity_filter.group_choices_like
import kotlinx.android.synthetic.main.activity_filter.group_choices_sex
import kotlinx.android.synthetic.main.activity_filter.group_choices_size
import kotlinx.android.synthetic.main.activity_filter.group_choices_type
import javax.inject.Inject


class FilterActivity : AppCompatActivity(), FilterContract.View {

    private var callbackManager: CallbackManager = CallbackManager.Factory.create()

    @Inject
    lateinit var presenter: FilterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        setupActivityComponent()
    }

    private fun setupActivityComponent() {
        (application as PetApplication).get(this)
            .getAppComponent()
            .plus(FilterModule(this))
            .inject(this)
    }

    override fun filterTypeList() = group_choices_type.getSelectedList()
    override fun filterSexList() = group_choices_sex.getSelectedList()
    override fun filterSizeList() = group_choices_size.getSelectedList()
    override fun filterConditionList() = group_choices_condition.getSelectedList()
    override fun filterLikeList() = group_choices_like.getSelectedList()
    override fun filterAgeList() = group_choices_age.getSelectedList()

    override fun applyButtonClicked() = applyFilterButton.clicks()


    override fun finishFilter(filters: FilterList) {
        val intent = Intent().putExtra("filters", filters)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}
