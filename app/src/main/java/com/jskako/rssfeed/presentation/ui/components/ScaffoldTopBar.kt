package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.zero
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    onNavigationIconClick: () -> Unit,
    navigationIconId: ImageVector? = Icons.AutoMirrored.Filled.ArrowBack,
    @StringRes navigationIconContentDescription: Int? = R.string.back,
    topBar: @Composable () -> Unit = {
        CenterAlignedTopAppBar(
            windowInsets = WindowInsets(zero),
            title = {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                navigationIconId?.let {
                    IconButton(
                        onClick = onNavigationIconClick,
                        modifier = Modifier
                            .padding(horizontal = s)
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = it,
                            contentDescription = navigationIconContentDescription?.let {
                                stringResource(it)
                            }
                        )
                    }
                }
            }
        )
    },
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            topBar.invoke()
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}

@PreviewLightDark
@Composable
fun ScaffoldTopBarPreview() {
    RssFeedTheme {
        ScaffoldTopBar(
            content = {},
            onNavigationIconClick = {},
            title = R.string.back
        )
    }
}