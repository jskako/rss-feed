package com.jskako.rssfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.rememberNavHostEngine

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RssFeedTheme {
                RssFeedApp()
            }
        }
    }
}

@Composable
private fun RssFeedApp() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        val navHostEngine = rememberNavHostEngine(
            navHostContentAlignment = Alignment.TopCenter
        )

        DestinationsNavHost(
            modifier = Modifier.padding(paddingValues),
            navGraph = NavGraphs.root,
            dependenciesContainerBuilder = {
                //dependency(viewModel)
            },
            navController = rememberNavController(),
            engine = navHostEngine
        )
    }
}