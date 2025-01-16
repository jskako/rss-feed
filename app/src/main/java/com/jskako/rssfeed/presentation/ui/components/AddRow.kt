package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.m
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xs
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun AddRow(
    modifier: Modifier = Modifier,
    input: String,
    onInputChanged: (String) -> Unit,
    @StringRes hintResId: Int,
    icon: ImageVector = Icons.Default.Add,
    enabled: Boolean = true,
    isRunning: Boolean = false,
    onIconClick: (String) -> Unit
) {

    OutlinedText(
        modifier = modifier,
        text = input,
        enabled = enabled && !isRunning,
        hintResId = hintResId,
        onValueChanged = { changedValue ->
            onInputChanged(changedValue)
        },
        trailingIcon = {
            if (!isRunning) {
                IconButton(
                    modifier = Modifier.padding(end = s),
                    isEnabled = enabled && input.isNotBlank(),
                    tint = if (enabled && input.isNotBlank()) LocalContentColor.current else Color.LightGray,
                    icon = icon,
                    onClick = {
                        onIconClick(input)
                    }
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.padding(end = m),
                    color = LocalContentColor.current,
                    strokeWidth = xs
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
fun AddRowPreview() {
    RssFeedTheme {
        AddRow(
            input = "",
            onInputChanged = {},
            onIconClick = {},
            hintResId = R.string.add
        )
    }
}