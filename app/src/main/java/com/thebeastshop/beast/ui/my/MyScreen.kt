package com.thebeastshop.beast.ui.my


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.customview.R
import com.thebeastshop.beast.data.DemoDataProvider.item
import com.thebeastshop.beast.theme.AppbarColor
import com.thebeastshop.beast.theme.LoginBackground
import com.thebeastshop.beast.utils.TestTags

@Composable
fun MyScreen(darkTheme: Boolean) {
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f)

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(320.dp)
        .background(color = LoginBackground)) {
        if (true) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                val imageModifierSetting = Modifier
                    .height(150.dp)
                    .width(30.dp)
                    .clickable {  }
                    .clip(shape = androidx.compose.material.MaterialTheme.shapes.medium)
                val imageModifierMessages = Modifier
                    .height(150.dp)
                    .width(30.dp)
                    .clickable {  }
                    .clip(shape = androidx.compose.material.MaterialTheme.shapes.medium)
                Image(
                    painter = painterResource(item.imageId),
                    modifier = imageModifierSetting,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Image(
                    painter = painterResource(item.imageId),
                    modifier = imageModifierMessages,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

        } else {

        }
    }
}