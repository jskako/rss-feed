package com.jskako.rssfeed.presentation.state

sealed class AddingProcessState {
    data object NotStarted : AddingProcessState()
    data object FetchingData : AddingProcessState()

    data class Done(
        val result: Result<String?>
    ) : AddingProcessState()
}