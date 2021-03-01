package com.manav.adoptme.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.manav.adoptme.ui.theme.AdoptmeTheme

@Composable
fun AdoptmeProgressBar(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
    color: Color = AdoptmeTheme.colors.primaryVariant,
    borderColor: Color = AdoptmeTheme.colors.primary
) {
    val progressBarShape = RoundedCornerShape(percent = 50)
    Column(modifier = modifier) {
        Box {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .border(2.dp, borderColor, progressBarShape)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(16.dp)
                    .clip(progressBarShape)
                    .background(
                        color, progressBarShape.copy(
                            topEnd = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp)
                        )
                    )
            )
        }
    }
}