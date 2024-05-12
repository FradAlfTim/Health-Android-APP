package com.example.assignmentproject.record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.assignmentproject.ui.ScreenSubTitleText
import com.example.assignmentproject.ui.ScreenTitleText


@Composable
fun <T> CommonListSubTitleScreen(
    title: String,
    modifier: Modifier = Modifier,
    records: List<T>,
    itemLayout: @Composable (T) -> Unit
) {
    CommonListScreen(title = title, records = records, subTitleLayout = {
        ScreenSubTitleText(title = "Past 7 Days", modifier = Modifier.padding(top = 20.dp))
    }) {
        itemLayout(it)
    }
}

@Composable
fun <T> CommonListScreen(
    title: String,
    modifier: Modifier = Modifier,
    records: List<T>,
    subTitleLayout: @Composable () -> Unit,
    itemLayout: @Composable (T) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {

        ScreenTitleText(title = title)

        subTitleLayout()

        LazyColumn(modifier = modifier, contentPadding = PaddingValues(bottom = 20.dp)) {
            items(records) {
                itemLayout(it)
            }
        }

    }
}


@Composable
fun CommonItemText(title: String, value: String, fontWeight: FontWeight? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontWeight = fontWeight
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontWeight = fontWeight

        )

    }
}





