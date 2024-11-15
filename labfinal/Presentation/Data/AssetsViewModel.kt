package com.example.labfinal.Presentation.Data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AssetsViewModel : ViewModel() {
    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets: StateFlow<List<Asset>> = _assets

    private val _selectedAsset = MutableStateFlow<AssetDetail?>(null)
    val selectedAsset: StateFlow<AssetDetail?> = _selectedAsset

    fun fetchAssets() {
        viewModelScope.launch {
            try {
                val assetsList = CoinCapApi.getAssets()
                _assets.value = assetsList
            } catch (e: Exception) {
                // Manejar errores
            }
        }
    }

    fun fetchAssetById(id: String) {
        viewModelScope.launch {
            try {
                val assetDetail = CoinCapApi.getAssetById(id)
                _selectedAsset.value = assetDetail
            } catch (e: Exception) {
                // Manejar errores
            }
        }
    }
}
