package com.vadymmy.ricktionary.ui.core.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private const val LABEL = "shimmer"
private const val DURATION_MILLIS = 1000

private const val INITIAL_VALUE = 0f
private const val TARGET_VALUE = 500f
private const val OFFSET = 100f

private const val FROM_ALPHA = 0.2f
private const val TO_ALPHA = 0.5f

@Composable
fun Modifier.shimmerLoading(durationMillis: Int = DURATION_MILLIS): Modifier {
    val transition = rememberInfiniteTransition(label = LABEL)
    val translateAnimation by transition.animateFloat(
        initialValue = INITIAL_VALUE,
        targetValue = TARGET_VALUE,
        label = LABEL,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    return drawBehind {
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.LightGray.copy(alpha = FROM_ALPHA),
                    Color.LightGray.copy(alpha = TO_ALPHA),
                    Color.LightGray.copy(alpha = FROM_ALPHA),
                ),
                start = Offset(x = translateAnimation, y = translateAnimation),
                end = Offset(x = translateAnimation + OFFSET, y = translateAnimation + OFFSET),
            )
        )
    }
}
