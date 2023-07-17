package com.rezapour.dogsbreeds.features.breedslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import com.rezapour.dogsbreeds.data.exception.DataProviderException
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.usecase.BreedDetailUseCase
import com.rezapour.dogsbreeds.domain.usecase.BreedUseCase
import com.rezapour.dogsbreeds.domain.usecase.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsListViewModel @Inject constructor(
    private val breedUseCase: BreedUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val breedDetailUseCase: BreedDetailUseCase,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<DataState<List<BreedDomain>>> =
        MutableStateFlow(DataState.Loading)

    val uiState: StateFlow<DataState<List<BreedDomain>>> = _uiState

    init {
        loadData()
    }

    fun loadData() {
        _uiState.value = DataState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            breedUseCase.getBreed().catch {
                _uiState.value = DataState.Error((it as DataProviderException).messageId)

            }.collect {
                _uiState.value = DataState.Success(it.sortedBy { it.title })
            }
        }
    }


    fun addFavorite(breed: BreedDomain) {
        viewModelScope.launch(dispatcherProvider.main) {
            favoriteUseCase.addFavorite(breed)
        }
    }

    fun deleteFavorite(breed: BreedDomain) {
        viewModelScope.launch(dispatcherProvider.main) {
            favoriteUseCase.deleteFavorite(breed)
        }
    }

    fun navigateToBreedDetail(breed: BreedDomain) {
        breedDetailUseCase.upDataData(breed)
    }
}