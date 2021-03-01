package com.manav.adoptme.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manav.adoptme.R
import com.manav.adoptme.model.Pet
import com.manav.adoptme.model.fake
import com.manav.adoptme.model.icon
import com.manav.adoptme.model.image
import com.manav.adoptme.ui.AdoptmeAppState
import com.manav.adoptme.ui.Screen
import com.manav.adoptme.ui.components.*
import com.manav.adoptme.ui.theme.AdoptmeTheme
import com.manav.adoptme.ui.theme.AlphaNearTransparent
import com.manav.adoptme.ui.theme.padding
import com.manav.adoptme.ui.utils.LocalSysUiController
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import java.util.*

@Composable
fun Home(
    navigateTo: (Screen) -> Unit, appState: AdoptmeAppState
) {
    LocalSysUiController.current.setStatusBarColor(
        AdoptmeTheme.colors.uiBackground.copy(
            AlphaNearTransparent
        )
    )
    AdoptmeScaffold { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        val scrollState = rememberScrollState()
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .padding(horizontal = padding)
        ) {
            Spacer(
                modifier = Modifier
                    .statusBarsPadding()
                    .height(16.dp)
            )
            AccountSection(name = "Manav", modifier) { navigateTo(Screen.Account) }
            FindSection(modifier = modifier)
            Spacer(modifier = Modifier.height(16.dp))
            appState.pets.forEach {
                PetCard(pet = it,
                    onLikeClick = { pet -> appState.like(pet) },
                    onPetClick = { pet ->  navigateTo(Screen.Detail(appState.pets.indexOf(pet)))}
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
private fun AccountSection(
    name: String,
    modifier: Modifier = Modifier,
    onAccountClicked: () -> Unit
) {
    Column {
        Row(modifier = modifier.wrapContentHeight(Alignment.CenterVertically)) {
            Column(Modifier.weight(1F)) {
                Text(
                    name,
                    style = MaterialTheme.typography.h4,
                    color = AdoptmeTheme.colors.textPrimary
                )
                Text(
                    "Bio:",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "I am from Visnagar, Gujarat, I want to adopt a dog.",
                    modifier.padding(bottom = 20.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                AdoptmeButton(onClick = onAccountClicked) {
                    Row {
                        ProvideTextStyle(value = MaterialTheme.typography.body1) {
                            Text("Profile info")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Rounded.ChevronRight,
                            tint = AdoptmeTheme.colors.btnContent,
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(Modifier.width(16.dp))
            CircleImage(modifier = Modifier.size(50.dp), painterResource(id = R.drawable.manav))
        }
    }
}


@Composable
private fun FindSection(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        AdoptmeSurface(
            color = Color.Transparent,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.dog), modifier = Modifier.width(116.dp), contentDescription = null)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight()) {
            Text(
                "I am finding", style = MaterialTheme.typography.h4, color = AdoptmeTheme.colors.textPrimary
            )
            Text(
                "Hello everyone i am manav shah i am finding one Labrador Retriever contact me.",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
private fun PetCard(
    pet: Pet,
    modifier: Modifier = Modifier,
    onPetClick: (Pet) -> Unit,
    onLikeClick: (Pet) -> Unit
) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(116.dp)
            .clickable(onClick = { onPetClick(pet) })
    ) {
        AdoptmeSurface(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.size(116.dp),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                bottomStart = 16.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            )
        ) {
            Image(
                painter = painterResource(id = pet.image(LocalContext.current)),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    pet.name,
                    style = MaterialTheme.typography.h6,
                    color = AdoptmeTheme.colors.textPrimary
                )
                Text(pet.location, style = MaterialTheme.typography.body2)
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(
                    start = {
                        Icon(
                            painter = painterResource(
                                id = pet.icon()
                            ),
                            tint = AdoptmeTheme.colors.primary,
                            modifier = Modifier.size(16.dp),
                            contentDescription = null
                        )
                    },
                    content = {
                        Text(
                            pet.type.name.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT),
                            color = AdoptmeTheme.colors.primary
                        )
                    }
                )
                IconButton(
                    onClick = { onLikeClick(pet) }
                ) {
                    Icon(
                        imageVector = if (pet.isLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        tint = AdoptmeTheme.colors.btnLike,
                        contentDescription = null
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Divider(
        modifier = Modifier.padding(start = 124.dp),
        color = AdoptmeTheme.colors.primaryVariant.copy(
            AlphaNearTransparent
        )
    )
}

@Preview
@Composable
fun AccountSectionLightPreview() {
    AdoptmeTheme {
        AccountSection(name = "Manav") {}
    }
}

@Preview
@Composable
fun AccountSectionDarkPreview() {
    AdoptmeTheme(darkTheme = true) {
        AdoptmeSurface {
            AccountSection(name = "Manav") {}
        }
    }
}

@Preview
@Composable
fun FindSectionLightPreview() {
    AdoptmeTheme(darkTheme = false) {
        FindSection(modifier = Modifier.size(116.dp))
    }
}

@Preview
@Composable
fun FindSectionDarkPreview() {
    AdoptmeTheme(darkTheme = true) {
        AdoptmeSurface {
            FindSection(modifier = Modifier.size(116.dp))
        }
    }
}

@Preview
@Composable
fun PetCardLightPreview() {
    AdoptmeTheme {
        PetCard(Pet.fake, onPetClick = {}, onLikeClick = {})
    }
}

@Preview
@Composable
fun PetCardDarkPreview() {
    AdoptmeTheme(darkTheme = true) {
        AdoptmeSurface {
            PetCard(Pet.fake, onPetClick = {}, onLikeClick = {})
        }
    }
}