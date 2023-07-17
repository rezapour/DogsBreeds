package com.rezapour.dogsbreeds.features.favoritelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.R
import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.usecase.BreedDetailUseCase
import com.rezapour.dogsbreeds.domain.usecase.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
    private val breedDetailUseCase: BreedDetailUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState: MutableStateFlow<DataState<List<BreedDomain>>> =
        MutableStateFlow(DataState.Loading)

    val uiState: StateFlow<DataState<List<BreedDomain>>> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = DataState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            favoriteUseCase.getFavorite()
                .catch { _uiState.value = DataState.Error(R.string.error_default_message) }
                .collect { data ->
                    _uiState.value = DataState.Success(data)
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