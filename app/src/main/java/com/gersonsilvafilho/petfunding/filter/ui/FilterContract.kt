package com.gersonsilvafilho.petfunding.splash.ui

import com.gersonsilvafilho.petfunding.filter.model.FilterList
import io.reactivex.Observable


/**
 * Created by GersonSilva on 3/21/17.
 */
interface FilterContract {
    interface View {

        fun filterTypeList(): List<String>
        fun filterSexList(): List<String>
        fun filterSizeList(): List<String>
        fun filterConditionList(): List<String>
        fun filterLikeList(): List<String>
        fun filterAgeList(): List<String>
        fun applyButtonClicked(): Observable<Unit>
        fun finishFilter(filters: FilterList)
        fun setTypeList(list: List<String>)
        fun setSexList(list: List<String>)
        fun setSizeList(list: List<String>)
        fun setConditionList(list: List<String>)
        fun setLikeList(list: List<String>)
        fun setAgeList(list: List<String>)
    }

    interface Presenter {
        fun initFilterCache(filter: FilterList)
        fun onStop()
        fun onCreate()
    }
}