package com.newsapp.core.composables


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.newsapp.ui.theme.newsBlacklight
import com.newsapp.ui.theme.newsGold


@Composable
fun SmallLoadingButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = newsGold,
    contentColor: Color = newsBlacklight,
    text: String,
    displayProgressBar: Boolean = false,
    onClick: () -> Unit
) {
    if(!displayProgressBar) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor) ,
            onClick = onClick,
            modifier = Modifier.padding(2.dp)
        ) {
            Text(text = text,  style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = contentColor,
            ) , fontSize = 16.sp)
        }

    } else {

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor) ,
            onClick = onClick,
            modifier = Modifier.padding(2.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(30.dp),
                color = contentColor,
                strokeWidth = 2.dp
            )
        }

    }
}