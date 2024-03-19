package com.inspire_hub.shortstories.features.stories.presentation

import androidx.compose.foundation.gestures.animateZoomBy
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.gestures.zoomBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.inspire_hub.shortstories.R
import com.inspire_hub.shortstories.core.domain.Result
import com.inspire_hub.shortstories.features.stories.presentation.component.HeaderElement
import com.inspire_hub.shortstories.features.stories.presentation.component.ResourceListItem
import com.inspire_hub.shortstories.features.stories.presentation.component.StoryListItem
import com.inspire_hub.shortstories.features.stories.presentation.model.StoriesState
import kotlinx.coroutines.launch

@Composable
fun StoriesScreen(
    modifier: Modifier = Modifier,
    state: Result<StoriesState>
) {
    var scrollIndex by rememberSaveable {
        mutableIntStateOf(-1)
    }
    val listState = rememberLazyListState()
    var scale by remember {
        mutableFloatStateOf(1f)
    }
    val transformState = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale = (scale * zoomChange).coerceIn(0.5f, 1.5f)
    }
    val coroutineScope = rememberCoroutineScope()

    when (state) {
        is Result.Success -> {
            Scaffold(
                bottomBar = {
                    BottomAppBar(containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f)){
                        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                            IconButton(onClick = {
                                if (scale<1.5f){
                                    scale += 0.1f
                                }
                            }) {
                                Icon(modifier = Modifier.size(50.dp),painter = painterResource(id = R.drawable.ic_zoom_in), contentDescription =null )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButton(onClick = {
                                if (scale > 0.5){
                                    scale -= 0.1f
                                }
                            }) {
                                Icon(modifier = Modifier.size(50.dp), painter = painterResource(id = R.drawable.ic_zoom_out), contentDescription = null)
                            }
                        }
                    }
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier
                        .padding(paddingValues)
                        .padding(8.dp)
                        .transformable(transformState)
                        .fillMaxSize(),
                    state = listState,
                    horizontalAlignment = AbsoluteAlignment.Right
                ) {
                    item {
                        HeaderElement(title = "اجمل القصص القصيره:", modifier, scale)
                    }
                    itemsIndexed(state.data.resourceList) { index, resoure ->
                        ResourceListItem(
                            modifier = modifier,
                            resource = resoure,
                            scale = scale,
                            onClick = {
                                coroutineScope.launch {
                                    scrollIndex = index + 1 + state.data.resourceList.size
                                    listState.scrollToItem(scrollIndex)
                                }
                            })
                    }
                    items(state.data.storyList) {
                        StoryListItem(story = it, scale = scale)
                    }
                }
            }

        }

        is Result.Error -> {
            Dialog(onDismissRequest = { /*TODO*/ }) {
                Text(text = "error ")
            }
        }

        else -> Unit
    }
}