package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.zero
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBar(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState? = null,
    @StringRes titleResId: Int? = null,
    onNavigationIconClick: (() -> Unit)? = null,
    navigationIconId: ImageVector? = Icons.AutoMirrored.Filled.ArrowBack,
    @StringRes navigationIconContentDescriptionResId: Int? = R.string.back,
    topBar: (@Composable () -> Unit)? = {
        CenterAlignedTopAppBar(
            windowInsets = WindowInsets(zero),
            title = {
                titleResId?.let {
                    Text(
                        text = stringResource(it),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            },
            navigationIcon = {
                navigationIconId?.let {
                    IconButton(
                        modifier = Modifier
                            .padding(horizontal = s),
                        onClick = { onNavigationIconClick?.invoke() },
                        icon = it,
                        iconContentDescriptionResId = navigationIconContentDescriptionResId
                    )
                }
            }
        )
    },
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        snackbarHost = {
            snackbarHostState?.let {
                SnackbarHost(hostState = it)
            }
        },
        modifier = modifier,
        topBar = {
            topBar?.invoke()
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
            titleResId = R.string.back
        )
    }
}