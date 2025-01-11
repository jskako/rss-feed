package com.jskako.rssfeed.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            color = Color.Unspecified
        )
    ) {
        Text(text = text)
    }
}

@PreviewLightDark
@Composable
fun OutlinedButtonPreview() {
    RssFeedTheme {
        Button(
            text = stringResource(R.string.add),
            onClick = {}
        )
    }
}