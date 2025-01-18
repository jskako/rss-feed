package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.api.CheckUrlReachabilityUseCase
import com.jskako.rssfeed.domain.usecase.rss.api.FetchRssFeedUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseItemUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.channel.ChannelExistUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.DeleteRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.GetLastBuildDateUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.GetRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.GetRssChannelsUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.InsertRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.IsNotificationEnabledUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.UpdateNotificationUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.CheckItemExistsUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.GetLastUpdateDateUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.GetRssItemByGuidUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.GetRssItemsUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.GetUnreadItemsCountUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.InsertRssItemUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.IsFavoriteUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.UpdateFavoriteStatusUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.item.UpdateReadStatusUseCase
import org.koin.dsl.module

val useCasesModule = module {

    // Channel UseCases
    factory { GetRssChannelsUseCase(get()) }
    factory { DeleteRssChannelUseCase(get()) }
    factory { GetRssChannelUseCase(get()) }
    factory { InsertRssChannelUseCase(get()) }
    factory { GetLastBuildDateUseCase(get()) }
    factory { ChannelExistUseCase(get()) }
    factory { IsNotificationEnabledUseCase(get()) }
    factory { UpdateNotificationUseCase(get()) }
    factory { DatabaseChannelUseCases(get(), get(), get(), get(), get(), get(), get(), get()) }

    // Item UseCases
    factory { GetRssItemsUseCase(get()) }
    factory { InsertRssItemUseCase(get()) }
    factory { GetRssItemByGuidUseCase(get()) }
    factory { GetLastUpdateDateUseCase(get()) }
    factory { CheckItemExistsUseCase(get()) }
    factory { GetUnreadItemsCountUseCase(get()) }
    factory { UpdateReadStatusUseCase(get()) }
    factory { IsFavoriteUseCase(get()) }
    factory { UpdateFavoriteStatusUseCase(get()) }
    factory {
        DatabaseItemUseCases(get(), get(), get(), get(), get(), get(), get(), get(), get())
    }

    factory { FetchRssFeedUseCase(get()) }
    factory { CheckUrlReachabilityUseCase(get()) }
    factory { ApiUseCases(get(), get()) }
}