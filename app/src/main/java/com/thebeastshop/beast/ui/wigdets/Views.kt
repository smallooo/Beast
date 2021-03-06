package com.thebeastshop.beast.ui.wigdets

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*

import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thebeastshop.beast.navigation.BottomNavType
import com.thebeastshop.beast.theme.AppThemeState
import com.thebeastshop.beast.theme.LoginBackground
import com.thebeastshop.beast.theme.SystemUiController
import com.thebeastshop.beast.ui.category.CategoryScreen
import com.thebeastshop.beast.ui.home.HomeScreen
import com.thebeastshop.beast.ui.home.PalletMenu
import com.thebeastshop.beast.ui.my.MyScreen
import com.thebeastshop.beast.ui.shops.ShopsScreen
import com.thebeastshop.beast.ui.social.SocialScreen
import com.thebeastshop.beast.ui.theme.*
import com.thebeastshop.beast.utils.TestTags
import kotlinx.coroutines.launch


@Composable
fun BaseView(
    appThemeState: AppThemeState,
    systemUiController: SystemUiController?,
    content: @Composable () -> Unit
) {
    val color = when (appThemeState.pallet) {
        ColorPallet.GREEN -> green700
        ColorPallet.BLUE -> blue700
        ColorPallet.ORANGE -> orange700
        ColorPallet.PURPLE -> purple700
        else -> green700
    }
    ComposeCookBookMaterial3Theme(
        darkTheme = appThemeState.darkTheme,
        colorPallet = appThemeState.pallet
    ) {
        systemUiController?.setStatusBarColor(color = Color.White, darkIcons = true)
        content()
    }
}

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class)
@Composable
fun MainAppContent(
    appThemeState: MutableState<AppThemeState>,
) {
    val homeScreenState = rememberSaveable { mutableStateOf(BottomNavType.HOME) }
    val bottomNavBarContentDescription = stringResource(id = com.thebeastshop.beast.R.string.a11y_bottom_navigation_bar)
    val chooseColorBottomModalState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = chooseColorBottomModalState,
        sheetContent = {
            PalletMenu(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) { newPalletSelected ->
                appThemeState.value = appThemeState.value.copy(pallet = newPalletSelected)
                coroutineScope.launch {
                    chooseColorBottomModalState.hide()
                }
            }
        }
    ) {
        val config = LocalConfiguration.current
        val orientation = config.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Column {
                HomeScreenContent(
                    homeScreen = homeScreenState.value,
                    appThemeState = appThemeState,
                    chooseColorBottomModalState = chooseColorBottomModalState,
                    modifier = Modifier.weight(1f)
                )
                BottomNavigationContent(
                    modifier = Modifier
                        .semantics { contentDescription = bottomNavBarContentDescription }
                        .testTag(TestTags.BOTTOM_NAV_TEST_TAG),
                    homeScreenState = homeScreenState,

                )
            }
        } else {
            Row(modifier = Modifier.fillMaxSize()) {
                NavigationRailContent(
                    modifier = Modifier
                        .semantics { contentDescription = bottomNavBarContentDescription }
                        .testTag(TestTags.BOTTOM_NAV_TEST_TAG),
                    homeScreenState = homeScreenState
                )
                HomeScreenContent(
                    homeScreen = homeScreenState.value,
                    appThemeState = appThemeState,
                    chooseColorBottomModalState = chooseColorBottomModalState,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun NavigationRailContent(
    modifier: Modifier,
    homeScreenState: MutableState<BottomNavType>,
) {
    var animate by remember { mutableStateOf(false) }
    androidx.compose.material3.NavigationRail(
        modifier = modifier,
    ) {
        androidx.compose.material3.NavigationRailItem(
            icon = {},
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
                animate = false
            },
            label = {
                androidx.compose.material3.Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_home),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
        )
        androidx.compose.material3.NavigationRailItem(
            icon = {},
            selected = homeScreenState.value == BottomNavType.WIDGETS,
            onClick = {
                homeScreenState.value = BottomNavType.WIDGETS
                animate = false
            },
            label = {
                androidx.compose.material3.Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_widgets),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_WIDGETS_TEST_TAG)
        )
        androidx.compose.material3.NavigationRailItem(
            icon = {},
            selected = homeScreenState.value == BottomNavType.ANIMATION,
            onClick = {
                homeScreenState.value = BottomNavType.ANIMATION
                animate = true
            },
            label = {
                androidx.compose.material3.Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_animation),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_ANIM_TEST_TAG)

        )
        androidx.compose.material3.NavigationRailItem(
            icon = {},
            selected = homeScreenState.value == BottomNavType.DEMOUI,
            onClick = {
                homeScreenState.value = BottomNavType.DEMOUI
                animate = false
            },
            label = {
                androidx.compose.material3.Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_demoui),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_DEMO_UI_TEST_TAG)
        )
        androidx.compose.material3.NavigationRailItem(
            icon = {},
            selected = homeScreenState.value == BottomNavType.TEMPLATE,
            onClick = {
                homeScreenState.value = BottomNavType.TEMPLATE
                animate = false
            },
            label = {
                androidx.compose.material3.Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_profile),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_TEMPLATE_TEST_TAG)
        )
    }
}


@Composable
fun BottomNavigationContent(
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }
    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            icon = { Icon(painter = painterResource(com.thebeastshop.beast.R.drawable.icon_tabhost_home_normal), contentDescription = "") },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_home),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(com.thebeastshop.beast.R.drawable.icon_tabhost_category_normal), contentDescription = "") },
            selected = homeScreenState.value == BottomNavType.WIDGETS,
            onClick = {
                homeScreenState.value = BottomNavType.WIDGETS
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_widgets),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_WIDGETS_TEST_TAG)
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(com.thebeastshop.beast.R.drawable.icon_tabhost_content_normal), contentDescription = "") },
            selected = homeScreenState.value == BottomNavType.ANIMATION,
            onClick = {
                homeScreenState.value = BottomNavType.ANIMATION
                animate = true
            },
            label = {
                Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_animation),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_ANIM_TEST_TAG)
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(com.thebeastshop.beast.R.drawable.icon_tabhost_store_normal), contentDescription = "") },
            selected = homeScreenState.value == BottomNavType.DEMOUI,
            onClick = {
                homeScreenState.value = BottomNavType.DEMOUI
                animate = false
            },
            label = {
                Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_demoui),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_DEMO_UI_TEST_TAG)
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(com.thebeastshop.beast.R.drawable.icon_tabhost_account_normal), contentDescription = "") },
            selected = homeScreenState.value == BottomNavType.TEMPLATE,
            onClick = {
                homeScreenState.value = BottomNavType.TEMPLATE
                animate = false

            },
            label = {
                Text(
                    text = stringResource(id = com.thebeastshop.beast.R.string.navigation_item_profile),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_TEMPLATE_TEST_TAG)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    appThemeState: MutableState<AppThemeState>,
    chooseColorBottomModalState: ModalBottomSheetState,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            androidx.compose.material3.Surface(color = MaterialTheme.colorScheme.background) {
                when (screen) {
                    BottomNavType.HOME -> HomeScreen(appThemeState, chooseColorBottomModalState)
                    BottomNavType.WIDGETS -> CategoryScreen(appThemeState.value.darkTheme)
                    BottomNavType.ANIMATION -> SocialScreen(appThemeState.value.darkTheme, viewModel = hiltViewModel())
                    BottomNavType.DEMOUI -> ShopsScreen(appThemeState.value.darkTheme)
                    BottomNavType.TEMPLATE -> MyScreen(appThemeState.value.darkTheme)
                }
            }
        }
    }
}