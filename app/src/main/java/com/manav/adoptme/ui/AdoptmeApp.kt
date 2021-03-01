
package com.manav.adoptme.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.manav.adoptme.ui.account.Account
import com.manav.adoptme.ui.detail.PetDetail
import com.manav.adoptme.ui.home.Home
import com.manav.adoptme.ui.theme.AdoptmeTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets


@Composable
fun AdoptmeApp(appState: AdoptmeAppState, navigationViewModel: NavigationViewModel) {
    ProvideWindowInsets {
        AdoptmeTheme {
            Crossfade(navigationViewModel.currentScreen) { destination ->
                when (destination) {
                    is Screen.Home -> {
                        Home(
                            navigationViewModel::navigateTo, appState
                        )
                    }
                    is Screen.Detail -> PetDetail(
                        petIndex = destination.petIndex,
                        upPress = { navigationViewModel.onBack() },
                        appState = appState
                    )
                    is Screen.Account -> Account(upPress = { navigationViewModel.onBack() })
                }
            }
        }
    }
}