package com.gersonsilvafilho.petfunding.util

import java.util.*

/**
 * Created by GersonSilva on 6/1/17.
 */
fun Date.monthsSinceNow():Int
{
    val startCalendar = GregorianCalendar()
    startCalendar.time = this
    val endCalendar = GregorianCalendar()
    endCalendar.time = Date()

    val diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)
    return diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH)
}