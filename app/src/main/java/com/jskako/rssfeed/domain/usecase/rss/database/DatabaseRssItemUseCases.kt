package com.jskako.rssfeed.domain.usecase.rss.database

import com.jskako.rssfeed.domain.usecase.rss.database.item.CheckItemExistsUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.GetLastUpdateDateUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.GetRssItemByGuidUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.GetRssItemsUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.GetUnreadItemsCountUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.InsertRssItemUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.IsFavoriteUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.UpdateFavoriteStatusUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.UpdateReadStatusUseCase

data class DatabaseRssItemUseCases(
    val insertRssItem: InsertRssItemUseCase,
    val getRssItems: GetRssItemsUseCase,
    val getRssItemByGuid: GetRssItemByGuidUseCase,
    val getLastUpdateDate: GetLastUpdateDateUseCase,
    val checkItemExists: CheckItemExistsUseCase,
    val getUnreadItemsCount: GetUnreadItemsCountUseCase,
    val updateReadStatus: UpdateReadStatusUseCase,
    val isFavorite: IsFavoriteUseCase,
    val updateFavoriteStatus: UpdateFavoriteStatusUseCase
)