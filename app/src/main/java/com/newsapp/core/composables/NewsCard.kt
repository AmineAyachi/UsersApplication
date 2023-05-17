package com.newsapp.core.composables

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.newsapp.ui.theme.newsBlacklight
import com.newsapp.ui.theme.newsGold
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

import com.newsapp.ui.theme.newsWhite

@Composable
fun NewsCard(
    bitmap: Bitmap,
    shortDiscription: String?,
    longDiscription: String?,
    eventDate:String?,
    title: String?,
    modifier: Modifier = Modifier,
    onCardClicked:()-> Unit,
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 10.dp, start = 10.dp)
            .border(width = 1.dp, color = newsGold, shape = RoundedCornerShape(15.dp)).clickable {
                onCardClicked()
            },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(newsBlacklight)
        ) {
            Row(){
                Column() {
                    Row() {

                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = shortDiscription,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    Row() {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),

                            ) {
                            Column() {
                                Text(""+title, style = TextStyle(color = newsGold, fontSize = 16.sp ))
//                                Text(stringResource(R.string.phone2) +shortDiscription, style = TextStyle(color = newsWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold))
                                Text(""+longDiscription, style = TextStyle(color = newsWhite, fontSize = 14.sp))
                                Row(horizontalArrangement = Arrangement.End , modifier = Modifier.fillMaxWidth()){
                                    Spacer(Modifier.height(5.dp))
                                    Text(""+eventDate , style = TextStyle(color = newsGold, fontSize = 14.sp))
                                }
                            }

                        }

                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth()){
                Row(horizontalArrangement = Arrangement.Start , modifier= Modifier.padding(20.dp)) {
                   // Image(  modifier = Modifier.height(20.dp).width(30.dp),painter = painterResource(id = R.drawable.map), contentDescription = null , contentScale = ContentScale.Crop)
                }


            } }
    }


}