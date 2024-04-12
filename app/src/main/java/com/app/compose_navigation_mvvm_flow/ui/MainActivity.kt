package com.app.compose_navigation_mvvm_flow.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.compose_navigation_mvvm_flow.ui.composeui.RecipeDetailScreen
import com.app.compose_navigation_mvvm_flow.ui.composeui.RecipesScreen
import com.app.compose_navigation_mvvm_flow.ui.ui.theme.NavigationExampleTheme
import com.app.compose_navigation_mvvm_flow.utils.Routes
import com.app.compose_navigation_mvvm_flow.viewmodels.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainContent()
        }
    }

    @Composable
    fun mainContent(){
        val mainViewModel : MainViewModel = koinViewModel()
        NavigationExampleTheme {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.LIST_SCREEN) {
                composable(Routes.LIST_SCREEN) {
                    RecipesScreen(navigation= navController, mainViewModel)
                }
                composable(
                    Routes.DETAIL_SCREEN,arguments= listOf(navArgument("idValue"){
                   type = NavType.IntType
                })
                ) {backStackEntry->
                    RecipeDetailScreen(navController,mainViewModel, backStackEntry.arguments?.getInt(Routes.Values.IDVALUE,0))
                }
            }
        }
    }


}