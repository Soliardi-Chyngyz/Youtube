package com.chyngyz.youtube.di

import com.chyngyz.youtube.ui.list_activity.PlayListViewModel
import com.chyngyz.youtube.ui.main_activity.MainListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules : Module = module {
    viewModel { MainListViewModel(get()) }
    viewModel { PlayListViewModel(get()) }
}