package com.jskako.rssfeed.presentation.ui.layouts

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.jskako.rssfeed.presentation.ui.theme.Padding.s

@Composable
fun MessageLayout(
    @StringRes messageResId: Int
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(s),
            text = stringResource(messageResId),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}