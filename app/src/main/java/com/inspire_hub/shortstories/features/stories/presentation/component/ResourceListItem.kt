package com.inspire_hub.shortstories.features.stories.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun ResourceListItem(
    resource: String,
    scale :Float,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .padding(4.dp)
            .clickable { onClick() },
        text = resource,
        color = Color.Blue,
        textDecoration = TextDecoration.Underline,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontSize =  MaterialTheme.typography.bodyLarge.fontSize * scale
        )
    )
}