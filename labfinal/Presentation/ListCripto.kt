package com.example.labfinal.Presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.labfinal.Presentation.Data.Asset

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetsListScreen(
    onAssetClick: (String) -> Unit,
    onSaveOfflineClick: () -> Unit,
    viewModel: AssetsViewModel = viewModel()
) {
    val assets by viewModel.assets.collectAsState()

    // Llama a la función para obtener los datos cuando se inicia la pantalla
    LaunchedEffect(Unit) {
        viewModel.fetchAssets()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Criptomonedas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onSaveOfflineClick) {
                Icon(Icons.Default.Save, contentDescription = "Guardar Offline")
            }
        }
    ) {
        LazyColumn {
            items(assets) { asset ->
                AssetItem(asset = asset, onClick = { onAssetClick(asset.id) })
            }
        }
    }
}

@Composable
fun AssetItem(asset: Asset, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
   //     elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = asset.name, style = MaterialTheme.typography.headlineLarge)
                Text(text = asset.symbol, style = MaterialTheme.typography.titleSmall)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "$${asset.priceUsd}", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "${asset.changePercent24Hr}%",
                    color = if (asset.changePercent24Hr.toDouble() > 0) Color.Green else Color.Red
                )
            }
        }
    }
}

@Composable
fun AssetPlaceholderItem(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
       // elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Criptomoneda", style = MaterialTheme.typography.headlineLarge)
                Text(text = "Símbolo", style = MaterialTheme.typography.titleSmall)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "$0.00", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "0.00%",
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAssetsListScreen() {
    MaterialTheme {
        AssetsListScreen(
            onAssetClick = { /* Acción simulada para click en un asset */ },
            onSaveOfflineClick = { /* Acción simulada para guardar offline */ }
        )
    }
}