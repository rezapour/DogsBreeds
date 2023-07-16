package com.rezapour.dogsbreeds.features.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import com.rezapour.dogsbreeds.data.exception.DataProviderException
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.usecase.BreedDetailUseCase
import com.rezapour.dogsbreeds.domain.usecase.BreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    private val breedUseCase: BreedDetailUseCase,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<DataState<List<String>>> =
        MutableStateFlow(DataState.Loading)

    val uiState: StateFlow<DataState<List<String>>> = _uiState

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            breedUseCase.breedState.collect { breed ->
                loadData(breed)
            }
        }
    }

    fun loadData(breedName: BreedDomain) {
        _uiState.value = DataState.Loading
        viewModelScope.launch(dispatcherProvider.main) {
            try {
                _uiState.value = DataState.Success(breedUseCase.getBreedImages(breedName))
            } catch (e: DataProviderException) {
                _uiState.value = DataState.Error(e.messageId)
            }
        }
    }
}