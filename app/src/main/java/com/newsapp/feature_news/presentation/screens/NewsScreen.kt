package com.newsapp.feature_news.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newsapp.core.composables.DefaultSnackbar
import com.newsapp.core.composables.NewsCard
import com.newsapp.core.composables.SmallLoadingButton
import com.newsapp.core.composables.util.SnackbarController
import com.newsapp.core.composables.util.WindowInfo
import com.newsapp.core.composables.util.rememberWindowInfo
import com.newsapp.core.navigation.Screen
import com.newsapp.core.util.Constants.Companion.selectedItem
import com.newsapp.core.util.loadPicture
import com.newsapp.feature_news.presentation.viewmodels.NewsViewModel
import com.newsapp.ui.theme.newsDarkWhiteColor
import com.newsapp.ui.theme.newsGold
import com.newsapp.ui.theme.newsWhite
import com.newsapp.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable
fun NewsScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarController = SnackbarController(coroutineScope)
    val scaffoldState = rememberScaffoldState()
    val viewModel: NewsViewModel = hiltViewModel()
    val windowInfo = rememberWindowInfo()

    LaunchedEffect(key1 = true ){
        viewModel.eventFlow.collectLatest {
                event ->
            when(event){

                is NewsViewModel.UIEvent.ShowSnackbar->{
                    snackbarController.getScope().launch {
                        snackbarController.showSnackbar(
                            scaffoldState = scaffoldState,
                            message = event.message,
                            actionLabel = "Hide",
                            {}
                        )
                    }
                }

            }
        }

    }

    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {

        Scaffold(
            backgroundColor = Color.Transparent,
            scaffoldState = scaffoldState,
            modifier = Modifier
                .fillMaxSize(),
            snackbarHost = { scaffoldState.snackbarHostState }
        ) {

            Box(modifier = Modifier.fillMaxSize()) {


                Column(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 2.dp) , horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = newsGold,
                                    fontSize = 14.sp
                                ),
                            ) {
                                append(stringResource(R.string.n))
                            }
                            append(stringResource(R.string.ews_app))
                        }, style = TextStyle(color = newsWhite, fontSize = 14.sp))

                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = newsGold ) ,
                            onClick = { viewModel.news() },
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = "Refresh",  style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = newsWhite,
                            ) , fontSize = 16.sp)
                        }
                    }

                    if(viewModel.newsState.value.isloading){
                        Column(Modifier.fillMaxSize() , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(50.dp),
                                color = newsDarkWhiteColor,
                                strokeWidth = 3.dp
                            )
                        }

                    }else{


                        if(viewModel.newsState.value.Error){
                            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                                Image(
                                    painter = painterResource(id = R.drawable.nodata),
                                    modifier = Modifier
                                        .height(80.dp)
                                        .width(60.dp),
                                    contentDescription = "",
                                    contentScale = ContentScale.FillBounds
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text ="Echec de la connecxion" , style = TextStyle(color = newsWhite, fontSize = 12.sp))
                                Spacer(modifier = Modifier.height(10.dp))
                                SmallLoadingButton(text = "Actualiser" , onClick = {viewModel.news()})


                            }

                        }else{

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            ) {

                                itemsIndexed(viewModel.newsState.value.News ?: emptyList()) { index, item ->
                                    // https://apimobile.condor.dz/img/Event/85dd370d-32c7-48bb-99f4-4bdfe79959d1.jpg
                                    val painter = painterResource(id = R.drawable.placeholder)
                                    val image = loadPicture(url = "${item.urlToImage}", defaultImage = R.drawable.placeholder).value
                                    image?.let{
                                        NewsCard(
                                            bitmap = it,
                                            shortDiscription = item.description.toString(),
                                            longDiscription =  item.content.toString(),
                                            eventDate = item.source?.name ?: item.author,
                                            title = item.title.toString() ,
                                            onCardClicked = {
                                                selectedItem = item
                                                navController.navigate(Screen.NewsDetailsScreen.route)
                                            }
                                        )
                                    }


                                }



                            }
                        }


                    }

                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp), contentAlignment = Alignment.BottomCenter) {
                    DefaultSnackbar(
                        snackbarHostState = scaffoldState.snackbarHostState,
                        onDismiss = {
                            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                        }, )
                }

            }


        }

    }else{
        Scaffold(
            backgroundColor = Color.Transparent,
            scaffoldState = scaffoldState,
            modifier = Modifier
                .fillMaxSize(),
            snackbarHost = { scaffoldState.snackbarHostState }
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                Column(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 2.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = newsGold,
                                    fontSize = 34.sp
                                ),
                            ) {
                                append(stringResource(R.string.n))
                            }
                            append(stringResource(R.string.ews_app))
                        }, style = TextStyle(color = newsWhite, fontSize = 24.sp))
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = newsGold ) ,
                            onClick = { viewModel.news() },
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = "Refresh",  style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = newsWhite,
                            ) , fontSize = 16.sp)
                        }
                    }

                    if(viewModel.newsState.value.isloading){
                        Column(Modifier.fillMaxSize() , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(50.dp),
                                color = newsDarkWhiteColor,
                                strokeWidth = 3.dp
                            )
                        }

                    }else{


                        if(viewModel.newsState.value.Error){
                            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                                Image(
                                    painter = painterResource(id = R.drawable.nodata),
                                    modifier = Modifier
                                        .height(120.dp)
                                        .width(100.dp),
                                    contentDescription = "",
                                    contentScale = ContentScale.FillBounds
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(text ="Echec de la connecxion" , style = TextStyle(color = newsWhite, fontSize = 16.sp))
                                Spacer(modifier = Modifier.height(20.dp))
                                SmallLoadingButton(text = "Actualiser" , onClick = {viewModel.news()})


                            }

                        }else{

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            ) {

                                itemsIndexed(viewModel.newsState.value.News ?: emptyList()) { index, item ->
                                    // https://apimobile.condor.dz/img/Event/85dd370d-32c7-48bb-99f4-4bdfe79959d1.jpg
                                    val painter = painterResource(id = R.drawable.placeholder)
                                    val image = loadPicture(url = "${item.urlToImage}", defaultImage = R.drawable.placeholder).value
                                    image?.let{
                                        NewsCard(
                                            bitmap = it,
                                            shortDiscription = item.description.toString(),
                                            longDiscription =  item.content.toString(),
                                            eventDate = item.source?.name ?: item.author,
                                            title = item.title.toString() ,
                                            onCardClicked = {
                                                selectedItem = item
                                                navController.navigate(Screen.NewsDetailsScreen.route)
                                            }
                                        )
                                    }


                                }



                            }
                        }


                    }

                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp), contentAlignment = Alignment.BottomCenter) {
                    DefaultSnackbar(
                        snackbarHostState = scaffoldState.snackbarHostState,
                        onDismiss = {
                            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                        }, )
                }

            }


        }

    }


}