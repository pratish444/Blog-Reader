package com.example.blogreader.presentation.bloglist.blogdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogreader.data.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogDetailViewModel @Inject constructor(
    private val repository: BlogRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(BlogDetailState())
    val state: StateFlow<BlogDetailState> = _state.asStateFlow()

    init {
        val blogId = savedStateHandle.get<Int>("blogId")
        blogId?.let { loadBlog(it) }
    }

    private fun loadBlog(blogId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val blog = repository.getBlogById(blogId)

            if (blog != null) {
                _state.value = _state.value.copy(
                    blog = blog,
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(
                    error = "Blog not found",
                    isLoading = false
                )
            }
        }
    }
}
