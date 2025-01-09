package com.jskako.rssfeed.presentation.ui.sections.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.components.GridDrawer
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun HomeSection(
    drawerList: List<String>,
    gridList: List<String>
) {
    GridDrawer(
        modifier = Modifier.fillMaxSize(),
        onNavigationContentSearch = {},
        drawerItems = {
            items(drawerList) { item ->
                Text(item)
            }
        },
        onGridContentSearch = {},
        gridItems = {
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
        },
        drawerTrailingIcon = Icons.Default.Edit,
        drawerTrailingIconContentDesc = R.string.add_content_desc,
        onDrawerTrailingIconClick = {

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