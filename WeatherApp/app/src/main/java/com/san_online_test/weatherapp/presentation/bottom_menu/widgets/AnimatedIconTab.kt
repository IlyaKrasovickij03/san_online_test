package com.san_online_test.weatherapp.presentation.bottom_menu.widgets

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.ui.R

private const val ANIMATION_DURATION = 1000

@Composable
fun AnimatedIconTab(
    modifier: Modifier = Modifier,
    selected: Boolean,
    @DrawableRes
    iconResourceId: Int,
    contentDescription: String,
    selectedColor: Color,
    notSelectedColor: Color,
    onClicked: () -> Unit
) {
    val color = if (selected) selectedColor else notSelectedColor

    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = TweenSpec(
            durationMillis = ANIMATION_DURATION,
            easing = FastOutSlowInEasing
        ), label = ""
    )

    Box(
        modifier = modifier.clickable(
            onClick = onClicked
        )
    ) {
        TabIcon(
            iconResourceId = iconResourceId,
            contentDescription = contentDescription,
            color = animatedColor
        )
    }
}


@Composable
private fun BoxScope.TabIcon(
    iconResourceId: Int,
    contentDescription: String,
    color: Color
) {
    Icon(
        modifier = Modifier
            .align(Alignment.Center)
            .width(24.dp)
            .height(24.dp),
        painter = painterResource(id = iconResourceId),
        contentDescription = contentDescription,
        tint = color,
    )
}

@Preview(showBackground = true)
@Composable
fun AnimatedIconPreview() {
    WeatherAppTheme {
        var selected by remember {
            mutableStateOf(false)
        }
        AnimatedIconTab(
            modifier = Modifier
                .height(80.dp)
                .width(120.dp),
            selected = selected,
            iconResourceId = R.drawable.ic_home,
            contentDescription = "",
            selectedColor = MaterialTheme.colorScheme.primary,
            notSelectedColor = MaterialTheme.colorScheme.onSurface,
        ) {
            selected = !selected
        }
    }
}