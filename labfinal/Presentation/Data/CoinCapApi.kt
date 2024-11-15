package com.example.labfinal.Presentation.Data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

object CoinCapApi {
    private const val BASE_URL = "https://api.coincap.io/v2/"

    private val client = HttpClient {
        install(Logging) {
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Ignorar claves desconocidas en la respuesta JSON
            })
        }
    }

    suspend fun getAssets(): List<Asset> {
        // Usa body() para deserializar el cuerpo de la respuesta en AssetResponse
        val response: AssetResponse = client.get("${BASE_URL}assets").body()
        return response.data // Devuelve solo la lista de Assets
    }

    suspend fun getAssetById(id: String): AssetDetail {
        // Usa body() para deserializar el cuerpo de la respuesta en AssetDetailResponse
        val response: AssetDetailResponse = client.get("${BASE_URL}assets/$id").body()
        return response.data // Devuelve solo el detalle del Asset
    }
}

@Serializable
data class Asset(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val vwap24Hr: String?
)

@Serializable
data class AssetResponse(
    val data: List<Asset> // La API devuelve una lista de Assets en el campo "data"
)

@Serializable
data class AssetDetailResponse(
    val data: AssetDetail // La API devuelve un objeto AssetDetail en el campo "data"
)

@Serializable
data class AssetDetail(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val vwap24Hr: String?,
    val explorer: String?
)
