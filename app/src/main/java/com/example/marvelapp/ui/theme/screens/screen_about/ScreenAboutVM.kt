package com.example.marvelapp.ui.theme.screens.screen_about

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
class ScreenAboutVM @Inject constructor(
    private val CatalogRep: CatalogRep,
) : ViewModel() {

    private val _status = MutableLiveData<ResponseStatus>()
    val status: LiveData<ResponseStatus> = _status

    private val _CatalogDataModel = MutableLiveData<CatalogDataModel>()
    val CatalogDataModel: LiveData<CatalogDataModel> = _CatalogDataModel


    fun getCardById(heroId: Int) {
        viewModelScope.launch {
            try {
                _status.value = ResponseStatus.LOADING
                val heroData = CatalogRep.getCardById(heroId)
                _CatalogDataModel.value = heroData
                _status.value = ResponseStatus.DONE
            } catch (e: Exception) {
                _status.value = ResponseStatus.ERROR
                Log.e("ScreenAboutVM", "Error", e)
            }
        }
    }
}