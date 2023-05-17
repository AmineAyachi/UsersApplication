package com.newsapp.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.newsapp.feature_news.presentation.screens.NewsScreen
import com.newsapp.feature_news.presentation.screens.NewsDetailsScreen

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun Navigation (navController: NavHostController, closeApp: () -> Unit ) {

    AnimatedNavHost(navController = navController, startDestination = Screen.NewsScreen.route) {


        composable(route = Screen.NewsScreen.route,
            enterTransition= { fadeIn(animationSpec = tween(700)) },
            exitTransition = { fadeOut(animationSpec = tween(700)) },
            popEnterTransition =   { fadeIn(animationSpec = tween(700)) },
            popExitTransition =  { fadeOut(animationSpec = tween(700)) },
        )
        {
            NewsScreen(navController = navController)
        }

        composable(route = Screen.NewsDetailsScreen.route,
            enterTransition= { fadeIn(animationSpec = tween(700)) },
            exitTransition = { fadeOut(animationSpec = tween(700)) },
            popEnterTransition =   { fadeIn(animationSpec = tween(700)) },
            popExitTransition =  { fadeOut(animationSpec = tween(700)) },
        )
        {
            NewsDetailsScreen(navController= navController )
        }







    }

}