package com.chyngyz.youtube.di

import com.chyngyz.youtube.data.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule : Module = module {
    single { AppDatabase.buildDatabase(androidContext()) }
}