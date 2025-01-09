package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    @StringRes iconContentDescriptionResId: Int? = null,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    IconButton(
        enabled = isEnabled,
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescriptionResId?.let {
                stringResource(it)
            },
            tint = tint
        )
    }
}

@PreviewLightDark
@Composable
fun IconButtonPreview() {
    RssFeedTheme {
        IconButton(
            onClick = {},
            content = {}
        )
    }
}
