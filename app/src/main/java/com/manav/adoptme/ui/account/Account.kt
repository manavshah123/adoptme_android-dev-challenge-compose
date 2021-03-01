package com.manav.adoptme.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manav.adoptme.R
import com.manav.adoptme.model.User
import com.manav.adoptme.model.fake
import com.manav.adoptme.ui.components.AdoptmeScaffold
import com.manav.adoptme.ui.components.AdoptmeSurface
import com.manav.adoptme.ui.components.CircleImage
import com.manav.adoptme.ui.components.UpButton
import com.manav.adoptme.ui.theme.AdoptmeTheme
import com.manav.adoptme.ui.theme.AlphaNearTransparent
import com.manav.adoptme.ui.theme.padding
import com.manav.adoptme.ui.utils.LocalSysUiController

@Composable
fun Account(upPress: () -> Unit) {
    LocalSysUiController.current.setStatusBarColor(
        AdoptmeTheme.colors.uiBackground.copy(
            AlphaNearTransparent
        )
    )
    val user = User.fake
    AdoptmeScaffold { innerPadding ->
        Box {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Header(user)
                Body(Modifier.padding(innerPadding), user)
            }
            UpButton(upPress)
        }
    }
}

@Composable
private fun Body(modifier: Modifier = Modifier, user: User) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = padding)) {
        Text(
            user.fullName,
            style = MaterialTheme.typography.h4,
            color = AdoptmeTheme.colors.textPrimary
        )
        Text(
            user.email,
            style = MaterialTheme.typography.h6,
            color = AdoptmeTheme.colors.textPrimary
        )

        Spacer(modifier = Modifier.height(16.dp).padding(bottom = 10.dp))
        Text(
            "Contact Info",
            style = MaterialTheme.typography.h4,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Email:",
            style = MaterialTheme.typography.h6,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp).padding(bottom = 10.dp))
        Text(
            user.email,
            style = MaterialTheme.typography.body1,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Mobile Number:",
            style = MaterialTheme.typography.h6,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "+91 7016544516",
            style = MaterialTheme.typography.body1,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.dog),
            modifier = Modifier.size(200.dp).offset(x=60.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun Header(
    user: User
) {
    Box(modifier = Modifier
        .wrapContentHeight()
        .padding(bottom = 35.dp)) {

        CircleImage(
            asset = painterResource(id = user.photo),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)

        )
    }
}

@Preview
@Composable
fun BodyLightPreview() {
    AdoptmeTheme {
        AdoptmeSurface {
            Body(user = User.fake)
        }
    }
}

@Preview
@Composable
fun BodyDarkPreview() {
    AdoptmeTheme(darkTheme = true) {
        AdoptmeSurface {
            Body(user = User.fake)
        }
    }
}

@Preview
@Composable
fun HeaderPreview() {
    AdoptmeTheme {
        Header(user = User.fake)
    }
}

@Preview
@Composable
fun AccountPreview() {
    Account {}
}