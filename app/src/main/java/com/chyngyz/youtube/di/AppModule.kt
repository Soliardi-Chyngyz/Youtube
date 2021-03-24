package com.chyngyz.youtube.di

import com.chyngyz.youtube.data.repository.Repository
import com.chyngyz.youtube.data.room.AppDatabase
import com.chyngyz.youtube.network.ApiInterface
import com.chyngyz.youtube.network.RetrofitService
import com.chyngyz.youtube.ui.list_activity.PlayListViewModel
import com.chyngyz.youtube.ui.main_activity.MainListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainListViewModel(get()) }
    viewModel { PlayListViewModel(get()) }

    // factory - создается объект каждый раз когда запрашивается объект
    // factory { PlayListItem(0, null, mutableListOf(), null) }
    // single - singleton
    single {RetrofitService.getInstance()}
    single { Repository(get(), get()) }
    single { AppDatabase.buildDatabase(androidContext()) }
}

