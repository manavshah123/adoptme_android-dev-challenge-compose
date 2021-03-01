package com.manav.adoptme.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.manav.adoptme.ui.theme.AdoptmeTheme
import kotlin.math.ln

@Composable
fun AdoptmeSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = AdoptmeTheme.colors.uiBackground,
    contentColor: Color = AdoptmeTheme.colors.textSecondary,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    val elevationPx = with(LocalDensity.current) { elevation.toPx() }
    val elevationOverlay = LocalElevationOverlay.current
    val absoluteElevation = LocalAbsoluteElevation.current + elevation
    val backgroundColor = if (color == MaterialTheme.colors.surface && elevationOverlay != null) {
        elevationOverlay.apply(color, absoluteElevation)
    } else {
        color
    }
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteElevation provides absoluteElevation
    ) {
        Box(
            modifier.graphicsLayer(shadowElevation = elevationPx, shape = shape)
                .then(if (border != null) Modifier.border(border, shape) else Modifier)
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .clip(shape),
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Composable
private fun getBackgroundColorForElevation(color: Color, elevation: Dp): Color {
    return if (elevation > 0.dp // && https://issuetracker.google.com/issues/161429530
    // AdoptmeTheme.colors.isDark //&&
    // color == AdoptmeTheme.colors.uiBackground
    ) {
        color.withElevation(elevation)
    } else {
        color
    }
}

/**
 * Applies a [Color.White] overlay to this color based on the [elevation]. This increases visibility
 * of elevation for surfaces in a dark theme.
 */
private fun Color.withElevation(elevation: Dp): Color {
    val foreground = calculateForeground(elevation)
    return foreground.compositeOver(this)
}

// TODO: b/145802792 - clarify this algorithm
/**
 * @return the alpha-modified [Color.White] to overlay on top of the surface color to produce
 * the resultant color.
 */
private fun calculateForeground(elevation: Dp): Color {
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    return Color.White.copy(alpha = alpha)
}