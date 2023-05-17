package com.newsapp.feature_news.presentation.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.newsapp.core.util.Constants.Companion.BASE_URL
import com.newsapp.core.util.Constants.Companion.selectedItem
import com.newsapp.core.util.loadPicture
import com.newsapp.feature_news.presentation.viewmodels.NewsViewModel
import com.newsapp.ui.theme.newsBlacklight
import com.newsapp.ui.theme.newsDarkWhiteColor
import com.newsapp.ui.theme.newsGold
import com.newsapp.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsDetailsScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val viewModel: NewsViewModel = hiltViewModel()
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(selectedItem?.url)) }


    Scaffold(
        backgroundColor = Color.Transparent,
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = { scaffoldState.snackbarHostState }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .border(
                            width = 1.dp,
                            color = newsGold,
                            shape = RoundedCornerShape(15.dp)
                        ),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp
                ) {


                    Column(  modifier = Modifier.background(color= newsBlacklight)) {


                        Row(modifier= Modifier
                            .fillMaxWidth()
                            .weight(4f)) {

                            Column(Modifier.fillMaxSize() ) {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight() ,verticalAlignment = Alignment.Top) {
                                    Box(modifier = Modifier.fillMaxSize()){
                                        val image = loadPicture(url = "${selectedItem?.urlToImage}", defaultImage = R.drawable.placeholder).value
                                        image?.let{
                                            Image(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                ,
                                                bitmap = it.asImageBitmap(),
                                                contentDescription = "",
                                                contentScale = ContentScale.FillBounds
                                            )
                                        }
                                        Box(modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                Brush.verticalGradient(
                                                    colors = listOf(
                                                        Color.Transparent,
                                                        newsBlacklight
                                                    )
                                                )
                                            )){

                                        }

                                    }
                                }
                            }



                        }


                        Divider(color = newsGold, thickness = 1.dp)
                        Row(modifier= Modifier
                            .fillMaxWidth()
                            .weight(6f) ,) {
                            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().padding(10.dp)) {
                                Text(""+ (selectedItem?.title ?:"" ), style = TextStyle(color = newsGold, fontSize = 18.sp ))
                                Text(""+ (selectedItem?.description ?:"" ), style = TextStyle(color = newsDarkWhiteColor, fontSize = 16.sp ))
                                 Row(horizontalArrangement = Arrangement.End ,modifier = Modifier.fillMaxWidth()) {
                                    Text(stringResource(R.string.lire_l_article), modifier = Modifier.clickable() {
                                        context.startActivity(intent)
                                        } , style = TextStyle(color = newsGold, fontSize = 16.sp ) )
                                }
                            }
                        }


                    }


                }
            }
        }
        }
        }



