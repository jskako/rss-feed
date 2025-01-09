package com.jskako.rssfeed.presentation.ui.components

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.l
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun EditDialog(
    text: String = "",
    onConfirmRequest: (String) -> Unit,
    onDismissRequest: () -> Unit,
    singleLine: Boolean = true,
) {

    var editText by remember { mutableStateOf(text) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(s),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(l)
            ) {

                Column {
                    Text(
                        modifier = Modifier.padding(bottom = s),
                        text = stringResource(R.string.edit),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    TextField(
                        value = editText,
                        singleLine = singleLine,
                        onValueChange = {
                            editText = it
                        },
                        placeholder = {
                            Text(stringResource(R.string.edit))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = s)
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
                        onClick = { onConfirmRequest(editText) },
                        enabled = editText.trim().isNotEmpty()
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun EditDialogPreview() {
    RssFeedTheme {
        EditDialog(
            onConfirmRequest = {},
            onDismissRequest = {}
        )
    }
}