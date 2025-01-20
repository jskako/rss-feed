package com.jskako.rssfeed.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jskako.rssfeed.presentation.event.RssEvent
import com.jskako.rssfeed.presentation.ui.layouts.InitConnectedLayout
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.jskako.rssfeed.presentation.viewmodel.InitViewModel
import com.jskako.rssfeed.presentation.viewmodel.NetworkViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>(start = true)
@Composable
fun InitScreen(
    navigator: DestinationsNavigator,
    viewModel: InitViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel()
) {

    val isConnected by networkViewModel.isConnected.collectAsState()
    val currentStep by viewModel.currentStep.collectAsState()
    val totalSteps by viewModel.totalSteps.collectAsState()

    LaunchedEffect(Unit) {
        if (isConnected) {
            viewModel.onRssEvent(
                RssEvent.FetchRssFeeds(
                    onDone = {
                        navigator.navigate(HomeScreenDestination())
                    }
                )
            )
        } else {
            navigator.navigate(HomeScreenDestination())
        }
    }

    if (isConnected) {
        InitConnectedLayout(
            currentStep = currentStep,
            totalSteps = totalSteps
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