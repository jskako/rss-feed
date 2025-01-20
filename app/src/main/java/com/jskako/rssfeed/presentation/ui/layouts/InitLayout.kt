package com.jskako.rssfeed.presentation.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jskako.rssfeed.presentation.ui.theme.Padding.l
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xs
import com.jskako.rssfeed.presentation.ui.theme.Padding.xxl
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun InitConnectedLayout(
    currentStep: Int,
    totalSteps: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(xxl),
            color = MaterialTheme.colorScheme.background,
            strokeWidth = xs
        )
        Spacer(modifier = Modifier.height(l))
        Text(
            text = "$currentStep/$totalSteps",
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(s))
        Text(
            text = "Fetching new data",
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@PreviewLightDark
@Composable
fun InitLayoutPreview() {
    RssFeedTheme {
        InitConnectedLayout(
            currentStep = 2,
            totalSteps = 7
        )
    }
}