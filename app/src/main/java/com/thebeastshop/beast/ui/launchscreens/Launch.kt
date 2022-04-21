package com.thebeastshop.beast.ui.launchscreens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.thebeastshop.beast.theme.AppThemeState
import com.thebeastshop.beast.utils.TestTags

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class)
@Composable
fun Launch(appThemeState: MutableState<AppThemeState>) {
    Scaffold(
        modifier = Modifier.testTag(TestTags.HOME_SCREEN_ROOT),
        content = {
            Text(text = "hello")
            Log.e("hello", "123456789")
        }
    )
}