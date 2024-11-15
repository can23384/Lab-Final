package com.example.labfinal.Presentation

import androidx.compose.material3.Text
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labfinal.Presentation.Data.AssetsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AssetDetailScreen(
    assetId: String,
    onBackClick: () -> Unit,
    viewModel: AssetsViewModel = viewModel()
) {
    val assetDetail by viewModel.selectedAsset.collectAsState()

    LaunchedEffect(assetId) {
        viewModel.fetchAssetById(assetId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles de Criptomoneda") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        assetDetail?.let { asset ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Nombre: ${asset.name}", style = MaterialTheme.typography.headlineMedium)
                Text("Símbolo: ${asset.symbol}", style = MaterialTheme.typography.bodyMedium)
                Text("Precio (USD): ${asset.priceUsd}", style = MaterialTheme.typography.bodyMedium)
                Text("Cambio 24Hr: ${asset.changePercent24Hr}%", style = MaterialTheme.typography.bodyMedium)
                Text("Supply: ${asset.supply}", style = MaterialTheme.typography.bodyMedium)
                Text("Max Supply: ${asset.maxSupply ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
                Text("Market Cap: ${asset.marketCapUsd}", style = MaterialTheme.typography.bodyMedium)
                Text("Última actualización: ${asset.explorer ?: "No disponible"}", style = MaterialTheme.typography.bodySmall)
            }
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewAssetDetailScreen() {
//    MaterialTheme {
//        AssetDetailScreen(
//            onBackClick = { /* Acción simulada para volver */ }
//        )
//    }
//}