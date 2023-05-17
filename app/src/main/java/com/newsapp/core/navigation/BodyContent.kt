package com.newsapp.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.newsapp.core.activities.view_models.MainViewModel
import com.newsapp.ui.theme.newsBlue


@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun BodyContent(navController: NavHostController, navigateClick: Boolean, setnavigateClickFalse: () -> Unit, closeApp: () -> Unit  ) {
    val offSetAnim by animateDpAsState(targetValue = if (navigateClick) 253.dp else 0.dp)
    val scaleAnim by animateFloatAsState(targetValue = if (navigateClick) 0.6f else 1.0f)
    val viewModel : MainViewModel = hiltViewModel()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(newsBlue)
    ) {


        Column(
            modifier = Modifier.fillMaxSize().clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() } ){   },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
          Navigation(navController , {closeApp()})
        }
    }
}
