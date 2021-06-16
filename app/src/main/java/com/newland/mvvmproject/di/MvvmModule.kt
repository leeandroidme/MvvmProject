package com.newland.mvvmproject.di

import com.newland.mvvmproject.module.register.RegisterRepository
import com.newland.mvvmproject.module.register.RegisterViewModel
import com.newland.mvvmproject.network.RetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegisterViewModel(get()) }
}
val repositoryModule = module {
    single { RegisterRepository(get()) }
}
val apiModule = module {
    single { RetrofitService.apiService }
}