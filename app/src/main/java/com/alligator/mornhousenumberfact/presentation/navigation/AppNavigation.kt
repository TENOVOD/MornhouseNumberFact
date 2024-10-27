package com.alligator.mornhousenumberfact.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alligator.mornhousenumberfact.presentation.screen.detail.DetailsScreen
import com.alligator.mornhousenumberfact.presentation.screen.main.MainScreen
import com.alligator.mornhousenumberfact.presentation.viewmodel.SharedViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    //Shared viewModel for sharing screen from main to detail screen
    val sharedViewModel:SharedViewModel = hiltViewModel()


    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = Screen.MAIN_SCREEN.name

        ) {
            composable(
                route = Screen.MAIN_SCREEN.name
            ){
                MainScreen(sharedViewModel=sharedViewModel)
            }
            composable(
                route = Screen.DETAILS_SCREEN.name
            ){
                DetailsScreen(sharedViewModel=sharedViewModel)
            }
        }
    }
}


enum class Screen{
    MAIN_SCREEN,
    DETAILS_SCREEN
}