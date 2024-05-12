package com.example.assignmentproject.main

import com.github.mikephil.charting.formatter.ValueFormatter

//we used this class for formatting value (adding % sign)
class PercentValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        //you can create your own formatting style below
        return "${value.toInt()}%"
    }
}