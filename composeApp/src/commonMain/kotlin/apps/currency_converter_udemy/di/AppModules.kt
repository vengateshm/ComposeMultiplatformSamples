package apps.currency_converter_udemy.di

import apps.currency_converter_udemy.data.local.MongoLocalDbRepositoryImpl
import apps.currency_converter_udemy.data.local.PreferencesRepositoryImpl
import apps.currency_converter_udemy.data.remote.api.CurrencyApiServiceImpl
import apps.currency_converter_udemy.domain.repository.CurrencyApiService
import apps.currency_converter_udemy.domain.repository.LocalDbRepository
import apps.currency_converter_udemy.domain.repository.PreferencesRepository
import apps.currency_converter_udemy.presentation.screens.HomeViewModel
import com.russhwolf.settings.Settings
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { Settings() }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
    single<CurrencyApiService> { CurrencyApiServiceImpl(get()) }
    single<LocalDbRepository> { MongoLocalDbRepositoryImpl() }
    factory {
        HomeViewModel(
            preferencesRepository = get(),
            currencyApiService = get(),
            localDbRepository = get()
        )
    }
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}