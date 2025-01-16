package com.jskako.rssfeed.presentation.utils

import androidx.compose.material3.SnackbarHostState

suspend fun String?.showSnackbarMessage(snackbarHostState: SnackbarHostState) {
    this?.let {
        snackbarHostState.showSnackbar(it)
    }
}