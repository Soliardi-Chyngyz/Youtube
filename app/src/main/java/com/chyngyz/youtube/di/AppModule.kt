package com.chyngyz.youtube.di

import com.chyngyz.youtube.network.RetrofitService.networkModule

val appModule = listOf(
    networkModule,
    repoModules,
    viewModules,
    databaseModule
)
    /*viewModel { MainListViewModel(get()) }
    viewModel { PlayListViewModel(get()) }*/

    // factory - создается объект каждый раз когда запрашивается объект
    // factory { PlayListItem(0, null, mutableListOf(), null) }
    // single - singleton
    /*single {RetrofitService.getInstance()}
    single { Repository(get(), get()) }
    single { AppDatabase.buildDatabase(androidContext()) }*/


