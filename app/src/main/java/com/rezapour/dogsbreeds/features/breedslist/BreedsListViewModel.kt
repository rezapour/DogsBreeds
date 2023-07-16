package com.rezapour.dogsbreeds.features.breedslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.data.exception.DataProviderException
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.usecase.BreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsListViewModel @Inject constructor(private val breedUseCase: BreedUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<DataState<List<BreedDomain>>> =
        MutableStateFlow(DataState.Loading)

    val uiState: StateFlow<DataState<List<BreedDomain>>> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = DataState.Loading
        viewModelScope.launch {
            breedUseCase.getBreed().catch {
                _uiState.value = DataState.Error((it as DataProviderException).messageId)

            }.collect {
                _uiState.value = DataState.Success(it)
            }
        }
    }
}