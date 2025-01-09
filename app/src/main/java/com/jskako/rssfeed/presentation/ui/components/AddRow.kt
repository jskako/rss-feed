package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun AddRow(
    modifier: Modifier = Modifier,
    @StringRes hintResId: Int,
    icon: ImageVector = Icons.Default.Add,
    enabled: Boolean = true,
    onIconClick: (String) -> Unit
) {

    var input by remember { mutableStateOf("") }

    OutlinedText(
        modifier = modifier,
        text = input,
        enabled = enabled,
        hintResId = hintResId,
        onValueChanged = { changedValue ->
            input = changedValue
        },
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(end = s),
                isEnabled = enabled,
                tint = if (enabled) LocalContentColor.current else Color.LightGray,
                icon = icon,
                onClick = {
                    onIconClick(input)
                    input = ""
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
fun AddRowPreview() {
    RssFeedTheme {
        AddRow(
            onIconClick = {},
            hintResId = R.string.add
        )
    }
}