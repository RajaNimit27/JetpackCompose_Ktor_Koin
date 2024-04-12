package com.app.compose_navigation_mvvm_flow.ui.composeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.app.compose_navigation_mvvm_flow.data.Receipes
import com.app.compose_navigation_mvvm_flow.utils.Routes
import com.app.compose_navigation_mvvm_flow.utils.UiState
import com.app.compose_navigation_mvvm_flow.viewmodels.MainViewModel

@Composable
fun RecipesScreen(navigation: NavController, mainViewModel: MainViewModel) {
    Scaffold(
        topBar = {
            CustomToolbarScreen(navController = navigation, title = "Home", false)
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //add your code
            LaunchedEffect(key1 = Unit) {
                getReceipesListAPI(mainViewModel)
            }
            val state = mainViewModel.uiStateReceipeList.collectAsState()
            when (state.value) {
                is UiState.Success -> {
                    ProgressLoader(isLoading = false)
                    (state.value as UiState.Success<Receipes>).data?.let {
                        it.recipes?.let { it1 ->
                            RecipeList(recipes = it1) { recipe ->
                                // Handle recipe click here
                                navigation.navigate(Routes.getSecondScreenPath(recipe.id))
                            }
                        }
                    }
                }

                is UiState.Loading -> {
                    ProgressLoader(isLoading = true)
                }

                is UiState.Error -> {
                    ProgressLoader(isLoading = false)
                    //Handle Error
                    SimpleAlertDialog(message =  ((state.value as UiState.Error<Receipes>).message))
                }
            }
        }
    }


}

@Composable
fun RecipeListCard(recipe: Receipes.Recipe, onRecipeClick: (Receipes.Recipe) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onRecipeClick(recipe) },
        shape = RoundedCornerShape(10),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(recipe.image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = recipe.name ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Prep Time: ${recipe.prepTimeMinutes} mins",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "Cook Time: ${recipe.cookTimeMinutes} mins",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "Servings: ${recipe.servings}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeList(recipes: List<Receipes.Recipe>, onRecipeClick: (Receipes.Recipe) -> Unit) {
    LazyColumn {
        items(recipes) { recipe ->
            RecipeListCard(recipe = recipe, onRecipeClick = onRecipeClick)
        }
    }
}

private fun getReceipesListAPI(mainViewModel: MainViewModel) {
    // Call the function to fetch recipes
    mainViewModel.getReceipesList()
}
