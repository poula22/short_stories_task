package com.inspire_hub.shortstories.features.stories.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.inspire_hub.shortstories.R
import com.inspire_hub.shortstories.core.domain.Result
import com.inspire_hub.shortstories.features.stories.presentation.model.StoriesState
import com.inspire_hub.shortstories.features.stories.presentation.model.StoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(
    private val app: Application,
) : AndroidViewModel(app) {
    val storiesState = flow<Result<StoriesState>> {
        val resources = mutableListOf<String>()
        val stories = mutableListOf<StoryItem>()
        getContent(resources,stories)
        emit(Result.Success(StoriesState(
            resourceList = resources,
            storyList = stories
        )))
    }.flowOn(Dispatchers.IO).catch {
        emit(Result.Error(it))
    }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            Result.Loading
    )

    private suspend fun getContent(resources: MutableList<String>, stories: MutableList<StoryItem>): Unit = withContext(Dispatchers.IO) {
        val inputStream = app.resources.openRawResource(R.raw.stories)
        val bufferReader = inputStream.bufferedReader()
        var title = StringBuffer()
        var content = StringBuffer()
        bufferReader.forEachLine { line->
            if(line.isEmpty() || line.isBlank() || line == "أجمل القصص القصيرة"){
            }else if (line[0] in '1'..'9'){
                resources.add(line.substring(2).trim())
            }else if (resources.contains(line.trim())){
                if (content.isNotEmpty()){
                    stories.add(
                        StoryItem(
                            title = title.toString(),
                            content = content.toString()
                        )
                    )
                    title = StringBuffer()
                    content = StringBuffer()
                }
                title.append(line.trim())
                title.append("\n")
            }else if (line.length>1){
                content.append(line.trim())
                content.append("\n")
            }
        }
        if (content.isNotEmpty()){
            stories.add(
                StoryItem(
                    title = title.toString(),
                    content = content.toString()
                )
            )
        }
        bufferReader.close()
        inputStream.close()
    }
}