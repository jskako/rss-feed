package com.jskako.rssfeed.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.network.NetworkMonitor
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NetworkViewModel(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _isConnected = mutableStateOf(true)
    val isConnected: StateFlow<Boolean> get() = networkMonitor.isConnected

    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collectLatest { status ->
                _isConnected.value = status
            }
        }
    }
}