package com.example.marvelapp.ui.screens.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.local.CatalogDataAccessObject
import com.example.marvelapp.data.mapper.CatalogDtoEntityMap
import com.example.marvelapp.data.mapper.CatalogEntityMapper
import com.example.marvelapp.data.model.ui.UiResults
import com.example.marvelapp.data.repository.CatalogRep
import com.example.marvelapp.network.api.ApiStatus
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
                if (localCard != null) {
                    val uiCardData = entityToUiMapper.map(localCard)
                    _cardDataModel.value = uiCardData
                } else {
                    val remoteCard = catalogRep.getCardById(cardId)
                    val entity = dtoToEntityMapper.map(remoteCard)
                    entity.forEach { catalogDataAccessObject.insert(it) }
                }
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                Log.e("DetailVM", "Error fetching card data", e)
            }
        }
    }
}