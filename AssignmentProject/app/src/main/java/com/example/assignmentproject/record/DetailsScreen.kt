package com.example.assignmentproject.record

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    unit: String,
    options: List<String>,
    onChange: (Int) -> Unit
) {

    val pickerState = rememberPickerState()
    val dialogShow = remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(vertical = 10.dp)
            .clickable { dialogShow.value = true }
            .background(Color.Gray, RoundedCornerShape(10.dp))
            .padding(20.dp, 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = "$content $unit",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier.weight(3f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.White
        )
    }

    if (dialogShow.value) {
        AlertDialog(onDismissRequest = { dialogShow.value = false }) {
            Card {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = title, style = MaterialTheme.typography.headlineMedium)
                    WheelPicker(
                        items = options.map { it },
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .fillMaxWidth(0.5f),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        ),
                        state = pickerState,
                        textModifier = Modifier.padding(10.dp),
                        dividerColor = Color(0xddD3D3D3)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { dialogShow.value = false }) {
                            Text("Cancel")
                        }
                        TextButton(onClick = {
                            onChange(pickerState.selectedItem)
                            dialogShow.value = false
                        }) {
                            Text("Confirm")
                        }
                    }
                }
            }
        }
    }

}







