package com.shourov.apps.pacedream.core.data.util

import java.util.Calendar

fun getDefaultDateInMillis(): Long {
    val cal = Calendar.getInstance()
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH)
    val date = cal.get(Calendar.DATE) + 1
    cal.clear()
    cal.set(year, month, date)
    return cal.timeInMillis
}