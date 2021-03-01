package com.manav.adoptme.ui.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.manav.adoptme.model.Pet
import com.manav.adoptme.model.icon
import com.manav.adoptme.model.image
import com.manav.adoptme.ui.AdoptmeAppState
import com.manav.adoptme.ui.components.AdoptmeScaffold
import com.manav.adoptme.ui.components.Chip
import com.manav.adoptme.ui.components.UpButton
import com.manav.adoptme.ui.theme.AdoptmeTheme
import com.manav.adoptme.ui.theme.AlphaNearTransparent
import com.manav.adoptme.ui.theme.padding
import com.manav.adoptme.ui.utils.LocalSysUiController
import java.util.*

@Composable
fun PetDetail(
    petIndex: Int,
    upPress: () -> Unit,
    appState: AdoptmeAppState
) {
    LocalSysUiController.current.setStatusBarColor(
        AdoptmeTheme.colors.uiBackground.copy(
            AlphaNearTransparent
        )
    )
    val pet = appState.pets[petIndex]
    AdoptmeScaffold {
        Box {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Box(modifier = Modifier
                    .wrapContentHeight()
                    .padding(bottom = 32.dp)) {
                    Image(
                        painter = painterResource(id = pet.image(LocalContext.current)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(296.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
                Body(pet = pet, onLikeClick = { appState.like(pet) })
                Spacer(modifier = Modifier.height(32.dp))
            }
            UpButton(upPress)
        }
    }
}

@Composable
private fun Body(pet: Pet, modifier: Modifier = Modifier, onLikeClick: (Pet) -> Unit) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(horizontal = padding)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(pet.name, style = MaterialTheme.typography.h4)
                Text(pet.location, style = MaterialTheme.typography.caption)
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = padding)
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
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
                Chip(content = {
                    Text(
                        if (pet.isMale) "Male" else "Female",
                        color = AdoptmeTheme.colors.primary
                    )
                })
            }
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
        Text(
            pet.desc,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(horizontal = padding)
                .padding(top = 32.dp)
        )
    }
}

