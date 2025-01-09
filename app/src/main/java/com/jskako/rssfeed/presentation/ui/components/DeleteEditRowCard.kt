package com.jskako.rssfeed.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xs
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun DeleteEditRowCard(
    text: String,
    onEditConfirmed: (String) -> Unit,
    onDeleteConfirmed: () -> Unit
) {

    var editDialogVisible by remember { mutableStateOf(false) }
    var deleteDialogVisible by remember { mutableStateOf(false) }

    if (editDialogVisible) {
        EditDialog(
            text = text,
            onConfirmRequest = {
                editDialogVisible = false
                onEditConfirmed(it)
            },
            onDismissRequest = {
                editDialogVisible = false
            }
        )
    }

    if (deleteDialogVisible) {
        TextDialog(
            titleResId = R.string.delete,
            descriptionResId = R.string.delete_desc,
            onConfirmRequest = {
                deleteDialogVisible = false
                onDeleteConfirmed()
            },
            onDismissRequest = {
                deleteDialogVisible = false
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(xs),
        elevation = CardDefaults.cardElevation(xs)
    ) {
        Column(
            modifier = Modifier.padding(s)
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(xs)
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        icon = Icons.Default.Edit,
                        onClick = {
                            editDialogVisible = true
                        }
                    )

                    IconButton(
                        icon = Icons.Default.Delete,
                        onClick = {
                            deleteDialogVisible = true
                        }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DeleteEditRowCardPreview() {
    RssFeedTheme {
        DeleteEditRowCard(
            text = "",
            onDeleteConfirmed = {},
            onEditConfirmed = {}
        )
    }
}