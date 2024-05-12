package com.example.assignmentproject.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

@Composable
fun PieChartScreen(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val pieEntries = listOf(
            PieEntry(35f, "Sun"),
            PieEntry(10f, "Mon"),
            PieEntry(5f, "Tues"),
            PieEntry(10f, "Wed"),
            PieEntry(10f, "Thurs"),
            PieEntry(10f, "Fri"),
            PieEntry(10f, "Sat"),
        )
        val pieDataSet = PieDataSet(pieEntries, "Pie Data Set")
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        val pieData = PieData(pieDataSet)
        pieDataSet.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
        pieDataSet.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
        pieDataSet.valueFormatter = PercentValueFormatter()
        pieDataSet.valueTextSize = 40f

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                PieChart(context).apply {
                    data = pieData
                    description.isEnabled = false
                    centerText = "Exercise "
                    setDrawCenterText(true)
                    setEntryLabelTextSize(14f)
                    animateY(4000)
                }
            }
        )

        IconButton(
            onClick = onClose,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close"
            )
        }
    }
}