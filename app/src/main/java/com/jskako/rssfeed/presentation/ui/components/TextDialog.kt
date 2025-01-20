package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun TextDialog(
    @StringRes titleResId: Int,
    @StringRes descriptionResId: Int,
    onConfirmRequest: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(s)
            ) {
                Text(
                    text = stringResource(titleResId),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = stringResource(descriptionResId),
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 16.sp
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(s)
                ) {
                    Button(
                        onClick = { onDismissRequest() }
                    ) {
                        Text(stringResource(R.string.cancel))
                    }

                    Button(
                        onClick = { onConfirmRequest() }
                    ) {
                        Text(stringResource(R.string.confirm))
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun TextDialogPreview() {
    RssFeedTheme {
        TextDialog(
            titleResId = R.string.add,
            descriptionResId = R.string.add,
            onConfirmRequest = {},
            onDismissRequest = {}
        )
    }
}