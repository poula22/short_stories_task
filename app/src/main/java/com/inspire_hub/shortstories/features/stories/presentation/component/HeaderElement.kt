package com.inspire_hub.shortstories.features.stories.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderElement(title: String, modifier: Modifier = Modifier, scale: Float) {
        Text(
            modifier = modifier.padding(vertical = 4.dp),
            text = title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize =  MaterialTheme.typography.headlineLarge.fontSize * scale,
                lineHeight = MaterialTheme.typography.headlineLarge.lineHeight * scale
            )
        )
}