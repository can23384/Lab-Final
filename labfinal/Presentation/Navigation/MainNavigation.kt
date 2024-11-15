package com.example.labfinal.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labfinal.Presentation.AssetDetailScreen
import com.example.labfinal.Presentation.AssetsListScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "assets_list") {
        composable("assets_list") {
            AssetsListScreen(
                onAssetClick = { id -> navController.navigate("asset_detail/$id") },
                onSaveOfflineClick = { /* Acción para guardar offline */ }
            )
        }
        composable(
            route = "asset_detail/{assetId}",
            arguments = listOf(navArgument("assetId") { type = NavType.StringType })
        ) { backStackEntry ->
            val assetId = backStackEntry.arguments?.getString("assetId") ?: return@composable
            AssetDetailScreen(
                assetId = assetId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}