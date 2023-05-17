package com.newsapp.core.navigation

sealed  class Screen  (val route:String) {

    object NewsScreen: Screen("news_screen")

    object NewsDetailsScreen: Screen("news_detail_screen")
}