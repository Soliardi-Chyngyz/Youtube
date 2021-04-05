package com.chyngyz.youtube.di

import com.chyngyz.youtube.data.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules : Module = module {
    single { Repository(get(), get()) }
}