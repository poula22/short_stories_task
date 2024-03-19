package com.inspire_hub.shortstories.features.stories.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.inspire_hub.shortstories.features.stories.presentation.model.StoryItem

@Composable
fun StoryListItem(modifier: Modifier = Modifier, story: StoryItem, scale: Float) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 16.dp),
        horizontalAlignment = AbsoluteAlignment.Right
    ) {
        Text(
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = story.title,

            color = Color.Red,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = MaterialTheme.typography.headlineMedium.fontSize * scale,
                lineHeight = MaterialTheme.typography.headlineMedium.lineHeight * scale

            )
        )
        Text(
//            modifier = modifier.drawWithCache {
//                onDrawBehind {
//                    drawRect(Color.Yellow)
//                }
//            },
            text = story.content,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize =  MaterialTheme.typography.bodyMedium.fontSize * scale,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * scale
            )
        )
//        OutlinedTextField(
//            value = story.content,
//            onValueChange = {},
//            readOnly = true,
//            textStyle = MaterialTheme.typography.bodyMedium.copy(
//                fontSize = MaterialTheme.typography.bodyMedium.fontSize * scale,
//                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * scale
//            )
//        )
    }
}