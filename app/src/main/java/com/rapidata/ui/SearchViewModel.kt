package com.rapidata.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rapidata.box.LoadingState
import com.rapidata.box.data.BaseRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val baseRepository: BaseRepository) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState
    private var pageNumber: Int = 0
    val searchResultData = baseRepository.searchResultData

    fun fetchSearchData(pageNum: Int, searchText: String) {
        viewModelScope.launch {
            try {
                if (pageNum == 0) pageNumber = 1
                else pageNumber += pageNum

                _loadingState.value = LoadingState.LOADING
                baseRepository.refresh(pageNumber, searchText)
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}