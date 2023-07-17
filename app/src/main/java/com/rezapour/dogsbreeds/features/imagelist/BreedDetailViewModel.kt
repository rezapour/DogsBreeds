package com.rezapour.dogsbreeds.features.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.R
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
        loadData()
    }
    fun loadData() {
        _uiState.value = DataState.Loading
        viewModelScope.launch(dispatcherProvider.main) {
            breedUseCase.breedState.collect { breed ->
                try {
                    _uiState.value = DataState.Success(breedUseCase.getBreedImages(breed))
                } catch (e: DataProviderException) {
                    _uiState.value = DataState.Error(e.messageId)
                } catch (e: Exception) {
                    _uiState.value = DataState.Error(R.string.error_default_message)
                }
            }

        }
    }
}