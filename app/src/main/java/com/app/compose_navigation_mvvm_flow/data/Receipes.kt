package com.app.compose_navigation_mvvm_flow.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Receipes(
    @SerialName("limit")
    val limit: Int? = null,
    @SerialName("recipes")
    val recipes: List<Recipe>? = null,
    @SerialName("skip")
    val skip: Int? = null,
    @SerialName("total")
    val total: Int? = null
) {
    @Serializable
    data class Recipe(
        @SerialName("caloriesPerServing")
        val caloriesPerServing: Int? = null,
        @SerialName("cookTimeMinutes")
        val cookTimeMinutes: Int? = null,
        @SerialName("cuisine")
        val cuisine: String? = null,
        @SerialName("difficulty")
        val difficulty: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("image")
        val image: String? = null,
        @SerialName("ingredients")
        val ingredients: List<String?>? = null,
        @SerialName("instructions")
        val instructions: List<String?>? = null,
        @SerialName("mealType")
        val mealType: List<String?>? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("prepTimeMinutes")
        val prepTimeMinutes: Int? = null,
        @SerialName("rating")
        val rating: Double? = null,
        @SerialName("reviewCount")
        val reviewCount: Int? = null,
        @SerialName("servings")
        val servings: Int? = null,
        @SerialName("tags")
        val tags: List<String?>? = null,
        @SerialName("userId")
        val userId: Int? = null
    )
}