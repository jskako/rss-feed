package com.jskako.rssfeed.domain.usecase.rss.database

import com.jskako.rssfeed.domain.usecase.rss.database.channel.ChannelExistUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.DeleteRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.GetLastBuildDateUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.GetRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.GetRssChannelsUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.InsertRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.info.CheckNotifications
import com.jskako.rssfeed.domain.usecase.rss.database.channel.info.DisableNotifications
import com.jskako.rssfeed.domain.usecase.rss.database.channel.info.EnableNotifications

data class DatabaseChannelUseCases(
    val deleteRssChannel: DeleteRssChannelUseCase,
    val getLastBuildDate: GetLastBuildDateUseCase,
    val getRssChannels: GetRssChannelsUseCase,
    val getRssChannel: GetRssChannelUseCase,
    val insertRssChannel: InsertRssChannelUseCase,
    val channelExist: ChannelExistUseCase,
    val enableNotifications: EnableNotifications,
    val disableNotifications: DisableNotifications,
    val checkNotifications: CheckNotifications
)