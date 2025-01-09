package com.jskako.rssfeed.presentation.ui.sections.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jskako.rssfeed.presentation.ui.components.GridDrawer
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun HomeSection(
    drawerList: List<String>,
    gridList: List<String>
) {
    GridDrawer(
        drawerContent = {
            items(drawerList) { item ->
                Text(item)
            }
        },
        gridContent = {
            items(gridList) { item ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = item,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
fun HomeSectionPreview() {
    RssFeedTheme {
        HomeSection(
            drawerList = emptyList(),
            gridList = emptyList()
        )
    }
}