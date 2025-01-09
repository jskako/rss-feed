package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
internal fun OutlinedText(
    modifier: Modifier = Modifier,
    text: String,
    @StringRes hintResId: Int,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    onValueChanged: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        readOnly = readOnly,
        enabled = enabled,
        onValueChange = {
            onValueChanged(it)
        },
        trailingIcon = trailingIcon,
        label = {
            HintText(
                hintResId = hintResId
            )
        },
        singleLine = true
    )
}

@PreviewLightDark
@Composable
fun OutlinedTextPreview() {
    RssFeedTheme {
        OutlinedText(
            text = "",
            hintResId = R.string.add,
            onValueChanged = {}
        )
    }
}
