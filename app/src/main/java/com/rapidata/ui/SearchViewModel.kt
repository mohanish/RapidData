package com.rapidata.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rapidata.box.BaseRepository
import com.rapidata.box.LoadingState
import kotlinx.coroutines.launch

class SearchViewModel(private val baseRepository: BaseRepository) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val searchResultData = baseRepository.searchResultData

    init {
        fetchSearchData()
    }

    private fun fetchSearchData() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                baseRepository.refresh()
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}