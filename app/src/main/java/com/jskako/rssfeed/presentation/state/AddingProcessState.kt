package com.jskako.rssfeed.presentation.state

import androidx.annotation.StringRes
import com.jskako.rssfeed.R

sealed class AddingProcessState(@StringRes val infoMessageResId: Int?) {
    data object NotStarted : AddingProcessState(null)
    data object FetchingData : AddingProcessState(R.string.state_fetch_data)

    data class Done(
        val succeed: Boolean,
        val message: String? = null
    ) : AddingProcessState(if (succeed) R.string.state_done_success else R.string.state_done_failure)
}