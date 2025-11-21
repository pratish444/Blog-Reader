package com.example.blogreader.presentation.bloglist

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogreader.data.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogListViewModel @Inject constructor(
    private val repository: BlogRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(BlogListState())
    val state: StateFlow<BlogListState> = _state.asStateFlow()

    init {
        loadBlogs()
        observeBlogs()
    }

    private fun observeBlogs() {
        viewModelScope.launch {
            repository.getBlogs().collectLatest { blogs ->
                _state.value = _state.value.copy(
                    blogs = blogs,
                    isLoading = false,
                    isLoadingMore = false
                )
            }
        }
    }

    fun loadBlogs(isRefresh: Boolean = false) {
        if (_state.value.isLoading || _state.value.isLoadingMore) return

        viewModelScope.launch {
            if (isRefresh) {
                _state.value = _state.value.copy(
                    isLoading = true,
                    error = null,
                    currentPage = 1
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = _state.value.blogs.isEmpty(),
                    error = null
                )
            }

            val isOnline = isNetworkAvailable()
            _state.value = _state.value.copy(isOffline = !isOnline)

            if (isOnline) {
                val result = repository.fetchAndCacheBlogs(_state.value.currentPage)
                result.onFailure { error ->
                    _state.value = _state.value.copy(
                        error = error.message ?: "Unknown error occurred",
                        isLoading = false
                    )
                }
            } else if (_state.value.blogs.isEmpty()) {
                _state.value = _state.value.copy(
                    error = "No internet connection",
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    fun loadMoreBlogs() {
        if (_state.value.isLoadingMore || !_state.value.hasMorePages || !isNetworkAvailable()) return

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingMore = true)

            val nextPage = _state.value.currentPage + 1
            val result = repository.fetchAndCacheBlogs(nextPage)

            result.onSuccess {
                _state.value = _state.value.copy(
                    currentPage = nextPage,
                    isLoadingMore = false
                )
            }.onFailure { error ->
                if (error.message?.contains("404") == true) {
                    _state.value = _state.value.copy(
                        hasMorePages = false,
                        isLoadingMore = false
                    )
                } else {
                    _state.value = _state.value.copy(
                        error = error.message ?: "Failed to load more blogs",
                        isLoadingMore = false
                    )
                }
            }
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}