package com.thebeastshop.beast.ui.social

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.thebeastshop.beast.data.model.MainViewModel
import com.thebeastshop.beast.data.model.Poster
import com.thebeastshop.beast.ui.settings.SettingsActivity

@Composable
fun SocialScreen(
    darkTheme: Boolean,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val posters: List<Poster> by viewModel.posterList.collectAsState(initial = listOf())
        PosterDetailsBody(context, posters)
}

@Composable
fun PosterDetailsBody(context : Context, posters : List<Poster>) {
    Column(modifier = Modifier.fillMaxSize()) {

        Row() {
            Column( modifier = Modifier.weight(1f)) {

                Text(text = "热门话题", modifier = Modifier.align(Alignment.Start).padding(12.dp,0.dp,0.dp,0.dp))
            }

            Column( modifier = Modifier.weight(1f)) {
                Text("全部话题",
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.align(Alignment.End).padding(0.dp,0.dp,12.dp,0.dp)
                        .clickable{
                            context.startActivity(SettingsActivity.newIntent(context, false)
                            )
                        })
            }

        }


//        posters.forEach { poster ->
//            Text(text = poster.name)
//        }
//
//        Text(text = "123")
    }
}
