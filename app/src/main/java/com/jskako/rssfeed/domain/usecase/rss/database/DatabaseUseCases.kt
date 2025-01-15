package com.jskako.rssfeed.domain.usecase.rss.database

data class DatabaseUseCases(
    val deleteRssChannel: DeleteRssChannelUseCase,
    val getLastBuildDate: GetLastBuildDateUseCase,
    val getRssChannels: GetRssChannelsUseCase,
    val getRssChannel: GetRssChannelUseCase,
    val insertRssChannelUseCase: InsertRssChannelUseCase
)