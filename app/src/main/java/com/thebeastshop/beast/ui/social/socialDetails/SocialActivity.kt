package com.thebeastshop.beast.ui.social.socialDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thebeastshop.beast.data.DemoDataProvider
import com.thebeastshop.beast.data.StoryItem
import com.thebeastshop.beast.data.StoryList
import com.thebeastshop.beast.ui.components.VerticalGrid
import com.thebeastshop.beast.ui.home.lists.*
import com.thebeastshop.beast.ui.settings.SettingsActivity
import java.util.*

class SocialActivity : ComponentActivity() {

    private val listType: String by lazy {
        intent?.getStringExtra(TYPE) ?: ListViewType.VERTICAL.name
    }

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseView(isDarkTheme) {
                ListViewContent(listType) {
                    onBackPressed()
                }
            }
        }
    }

    companion object {
        const val TYPE = "type"
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, SocialActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListViewContent(listType: String, onback: () -> Unit) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Text(text = "ListView")
                        Text(
                            text = listType.lowercase(Locale.getDefault()),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onback) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "hello",
                        )
                    }
                },
            )
        },
        content = {
            when (listType) {
                ListViewType.VERTICAL.name -> {
                    VerticalListView()
                }
                ListViewType.HORIZONTAL.name -> {
                    HorizontalListView()
                }
                ListViewType.GRID.name -> {
                    GridListView()
                }
                ListViewType.MIX.name -> {
                    HorizontalListView()
                }
            }
        }
    )
}

@Composable
fun VerticalListView() {
    val list = remember { DemoDataProvider.itemList }
    LazyColumn {
        items(
            items = list,
            itemContent = { item ->
                if ((item.id % 3) == 0) {
                    VerticalListItemSmall(item = item)
                } else {
                    VerticalListItem(item = item)
                }
                ListItemDivider()
            })
    }
}

@Composable
fun HorizontalListView() {
    val list = remember { DemoDataProvider.itemList }
    val profiles = remember { DemoDataProvider.tweetList }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Good Food",
            style = MaterialTheme.typography.labelMedium
        )
        LazyRow(
            modifier = Modifier.padding(end = 16.dp)
        ) {
            items(
                items = list,
                itemContent = {
                    HorizontalListItem(
                        it,
                        Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                })
        }
        ListItemDivider()
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Stories",
            style = MaterialTheme.typography.labelMedium
        )
        StoryList(
            profiles = profiles,
            onProfileClicked = {}
        )
    }
}

@Composable
fun GridListView() {
    val list = remember { DemoDataProvider.itemList.take(4) }
    val posts = remember { DemoDataProvider.tweetList }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        VerticalGrid(columns = 2) {
            list.forEach {
                GridListItem(item = it)
            }
        }
        VerticalGrid(columns = 4) {
            posts.forEach {
                StoryItem(
                    profileImageId = it.authorImageId,
                    profileName = it.author,
                    isMe = it.id == 1,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}