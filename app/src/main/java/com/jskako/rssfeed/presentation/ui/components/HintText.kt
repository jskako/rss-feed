package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun HintText(
    modifier: Modifier = Modifier
        .padding(s)
        .alpha(0.5f),
    @StringRes hintResId: Int
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = stringResource(hintResId),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@PreviewLightDark
@Composable
fun HintTextPreview() {
    RssFeedTheme {
        HintText(
            hintResId = R.string.add
        )
    }
}