package com.gersonsilvafilho.petfunding.splash.ui

import com.gersonsilvafilho.petfunding.filter.model.Filter
import com.gersonsilvafilho.petfunding.filter.model.FilterList
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by GersonSilva on 3/21/17.
 */
class FilterPresenter(private val view: FilterContract.View) : FilterContract.Presenter {
    override fun initFilterCache(filter: FilterList) {
        getListFromType(filter, Filter.Type.AnimalType)?.let {
            view.setTypeList(it)
        }

        getListFromType(filter, Filter.Type.Size)?.let {
            view.setSizeList(it)
        }

        getListFromType(filter, Filter.Type.Condition)?.let {
            view.setConditionList(it)
        }

        getListFromType(filter, Filter.Type.Likes)?.let {
            view.setLikeList(it)
        }

        getListFromType(filter, Filter.Type.Age)?.let {
            view.setAgeList(it)
        }

        getListFromType(filter, Filter.Type.Sex)?.let {
            view.setSexList(it)
        }

    }

    private val compositeDisposable = CompositeDisposable()

    init {

        compositeDisposable.add(
            view.applyButtonClicked()
                .subscribe {
                    val filters = FilterList(listOf(getTypeFilter(), getSexFilter(), getAgeFilter(), getSizeFilter(), getFilterCondition(), getLikeFilter()))
                    view.finishFilter(filters)
                }
        )


    }

    override fun onStop() {
        compositeDisposable.clear()
    }

    private fun getTypeFilter(): Filter<List<String>> = Filter(Filter.Type.AnimalType, view.filterTypeList())

    private fun getSexFilter(): Filter<List<String>> = Filter(Filter.Type.Sex, view.filterSexList())

    private fun getAgeFilter(): Filter<List<String>> = Filter(Filter.Type.Age, view.filterAgeList())

    private fun getSizeFilter(): Filter<List<String>> = Filter(Filter.Type.Size, view.filterSizeList())

    private fun getFilterCondition(): Filter<List<String>> = Filter(Filter.Type.Condition, view.filterConditionList())

    private fun getLikeFilter(): Filter<List<String>> = Filter(Filter.Type.Likes, view.filterLikeList())


    private fun getListFromType(filter: FilterList, type: Filter.Type) = filter.filters.firstOrNull { it.type == type }?.value as? List<String>

}