package com.example.assignmentproject.ui

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

fun Modifier.gradientBackground(): Modifier {
    return this.background(
        brush = Brush.verticalGradient(
            colors = listOf(Color(0xFF87CEEB), Color.White), // 设置渐变色
            startY = 200f, // 设置渐变的起始位置
            endY = 800f // 设置渐变的结束位置
        )
    )
}

@Composable
fun ScreenTitleText(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier,
    )
}

@Composable
fun ScreenSubTitleText(
    modifier: Modifier = Modifier,
    title: String,
    fontWeight: FontWeight? = FontWeight.SemiBold
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = Color.White,
        fontWeight = fontWeight,
        modifier = modifier,
    )
}

@Composable
fun PreviewContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .gradientBackground()
            .padding(15.dp, 20.dp)
    ) {
        content()
    }
}

@Composable
fun rememberForResultLauncher(): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }
    return launcher
}



