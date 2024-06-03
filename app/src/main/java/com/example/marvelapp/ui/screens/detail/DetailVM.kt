package com.example.marvelapp.ui.screens.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.mapper.CatalogDtoEntityMap
import com.example.marvelapp.data.mapper.CatalogEntityMapper
import com.example.marvelapp.data.model.CatalogDataAccessObject
import com.example.marvelapp.data.model.ui.UiResults
import com.example.marvelapp.data.network.api.ApiStatus
import com.example.marvelapp.data.network.repository.CatalogRep
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val catalogRep: CatalogRep,
    private val catalogDataAccessObject: CatalogDataAccessObject,
    private val dtoToEntityMapper: CatalogDtoEntityMap,
    private val entityToUiMapper: CatalogEntityMapper
    ) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _cardDataModel = MutableLiveData<UiResults>()
    val cardDataModel: LiveData<UiResults> = _cardDataModel

    fun getCardById(cardId: Int) {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                val localCard = catalogDataAccessObject.cardById(cardId)
                val uiCardData = entityToUiMapper.map(localCard)
                _cardDataModel.value = uiCardData
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                Log.e("DetailVM", "Error fetching card data", e)
            }
        }
    }
}