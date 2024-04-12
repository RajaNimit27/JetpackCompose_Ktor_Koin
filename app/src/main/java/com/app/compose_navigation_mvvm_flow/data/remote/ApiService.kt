package com.app.compose_navigation_mvvm_flow.data.remote

import com.app.compose_navigation_mvvm_flow.data.Receipes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class ApiService(private val httpClient: HttpClient) {

    suspend fun getReceipes(): Receipes = httpClient.get("https://dummyjson.com/recipes").body<Receipes>()

    suspend fun getReceipeDetails(@Path("id")id:Int?): Receipes.Recipe = httpClient.get("https://dummyjson.com/recipes/$id").body<Receipes.Recipe>()
}