package com.jskako.rssfeed.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.jskako.rssfeed.presentation.ui.theme.Padding.m
import com.jskako.rssfeed.presentation.ui.theme.Padding.xxs
import com.jskako.rssfeed.presentation.ui.theme.Padding.xxxl
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSlider(
    imageUrls: List<String>
) {
    HorizontalUncontainedCarousel(
        state = rememberCarouselState {
            imageUrls.count()
        },
        itemWidth = xxxl,
        itemSpacing = m,
        contentPadding = PaddingValues(start = m),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = m)
    ) {
        imageUrls.forEach {
            AsyncImage(
                model = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(xxxl)
                    .fillMaxWidth()
                    .maskClip(MaterialTheme.shapes.extraLarge)
                    .maskBorder(
                        BorderStroke(xxs, MaterialTheme.colorScheme.secondary),
                        MaterialTheme.shapes.extraLarge
                    )
            )
        }
    }
}

@PreviewLightDark
@Composable
fun ImageSliderPreview() {
    RssFeedTheme {
        ImageSlider(
            imageUrls = emptyList()
        )
    }
}