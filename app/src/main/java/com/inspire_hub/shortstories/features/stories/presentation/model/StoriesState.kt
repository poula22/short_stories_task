package com.inspire_hub.shortstories.features.stories.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class StoriesState(
    val resourceList: List<String>,
    val storyList: List<StoryItem>,
)
