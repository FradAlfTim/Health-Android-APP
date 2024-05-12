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
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

@Composable
fun BarChartScreen(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val barEntries = listOf(
            BarEntry(0f, 1070f),
            BarEntry(1f, 4050f),
            BarEntry(2f, 3890f),
            BarEntry(3f, 5599f),
            BarEntry(4f, 2300f),
            BarEntry(5f, 4055f)
        )
        val barDataSet = BarDataSet(barEntries, "Steps")
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        val barData = BarData(barDataSet)
        barData.barWidth = 1.0f

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                BarChart(context).apply {
                    data = barData
                    description.isEnabled = false
                    setFitBars(true)
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.valueFormatter = IndexAxisValueFormatter(
                        listOf("Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat")
                    )
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