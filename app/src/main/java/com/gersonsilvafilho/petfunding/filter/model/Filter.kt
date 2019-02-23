package com.gersonsilvafilho.petfunding.filter.model

import java.io.Serializable

/**
 * Created by Gerson Silva Filho on 27.01.19.
 */
data class Filter<out OutputType>(val type: Type, val value: OutputType) : Serializable {

    enum class Type {
        AnimalType,
        Sex,
        Age,
        Size,
        Condition,
        Likes
    }
}

data class FilterList(val filters: List<Filter<Any>>) : Serializable

data class Interval(val from: Int, val to: Int) : Serializable


fun FilterList.containsString(type: Filter.Type, filter: String): Boolean {
    val currentValue = (this.filters.firstOrNull { it.type == type } as? Filter<List<String>>?)?.value
    return currentValue == null || (currentValue.isEmpty() || currentValue.contains(filter))
}

fun FilterList.containsStringWithValue(type: Filter.Type, filter: String, boolValue: Boolean): Boolean {
    val currentValue = (this.filters.firstOrNull { it.type == type } as? Filter<List<String>>?)?.value
    return currentValue == null || (currentValue.isEmpty() || (currentValue.contains(filter) && boolValue))
}