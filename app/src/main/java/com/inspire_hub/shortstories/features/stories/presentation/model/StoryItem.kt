package com.inspire_hub.shortstories.features.stories.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class StoryItem(
    val title: String,
    val content:String
)