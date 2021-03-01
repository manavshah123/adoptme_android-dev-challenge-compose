package com.manav.adoptme.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.manav.adoptme.model.Pet
import com.manav.adoptme.model.allPets
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.manav.adoptme.ui.utils.LocalSysUiController
import com.manav.adoptme.ui.utils.SystemUiController

class MainActivity : AppCompatActivity() {
    private val navigationViewModel by viewModels<NavigationViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val pets = Pet.allPets(this)
        val appState = AdoptmeAppState(pets ?: listOf())
        setContent {
            val systemUiController = remember { SystemUiController(window) }
            CompositionLocalProvider(LocalSysUiController provides systemUiController) {
                AdoptmeApp(appState, navigationViewModel)
            }
        }
    }

    override fun onBackPressed() {
        if (!navigationViewModel.onBack()) {
            super.onBackPressed()
        }
    }
}

