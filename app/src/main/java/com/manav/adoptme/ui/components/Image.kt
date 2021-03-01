package com.manav.adoptme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun CircleImage(modifier: Modifier = Modifier, asset: Painter) {
    AdoptmeSurface(
        color = Color.LightGray,
        shape = RoundedCornerShape(10),
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = asset,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}
