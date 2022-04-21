package com.thebeastshop.beast

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.thebeastshop.beast.theme.AppThemeState
import com.thebeastshop.beast.theme.SystemUiController
import com.thebeastshop.beast.ui.theme.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.thebeastshop.beast.navigation.NavGraph
import com.thebeastshop.beast.ui.wigdets.BaseView
import com.thebeastshop.beast.ui.wigdets.MainAppContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val PREFERENCES_NAME_USER = "sample_datastore_prefs"
    private val Context.prefsDataStore by preferencesDataStore(
        name = PREFERENCES_NAME_USER
    )


    @SuppressLint("RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeastTheme {
                val systemUiController = remember { SystemUiController(window) }
                val appTheme = remember { mutableStateOf(AppThemeState()) }
                val navController = rememberNavController()

                androidx.compose.material.Scaffold(
                    backgroundColor = MaterialTheme.colors.primarySurface,
                    bottomBar = {  }
                ) { innerPaddingModifier ->
                    NavGraph(
                        systemUiController = systemUiController,
                        navController = navController,

                    )
                }
            }
        }
    }
}
