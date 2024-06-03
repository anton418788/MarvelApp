package com.example.marvelapp.ui.theme.screens.catalog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.network.ResponseStatus
import com.example.marvelapp.network.rep.CatalogRep
import com.example.marvelapp.ui.components.CatalogDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogVM @Inject constructor(
    private val CatalogRep: CatalogRep,
) : ViewModel() {
    private val _status = MutableLiveData<ResponseStatus>()
    val status: LiveData<ResponseStatus> = _status

    private val _heroesDataModel = MutableLiveData<CatalogDataModel>()
    val heroesDataModel: LiveData<CatalogDataModel> = _heroesDataModel

    init {
        getResponseList()
    }

    fun getResponseList() {
        viewModelScope.launch {
            try {
                _status.value = ResponseStatus.LOADING
                val heroData = CatalogRep.getResponseList()
                _heroesDataModel.value = heroData
                _status.value = ResponseStatus.DONE
            } catch (e: Exception) {
                _status.value = ResponseStatus.ERROR
                Log.e("CatalogVM", "Error", e)
            }
        }
    }
}