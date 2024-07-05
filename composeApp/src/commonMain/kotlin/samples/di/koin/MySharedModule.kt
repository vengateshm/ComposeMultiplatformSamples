package samples.di.koin

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mySharedModule = module {
//    single { MyRepositoryImpl(get()) }.bind()
    singleOf(::MyRepositoryImpl).bind<MyRepository>()
    viewModelOf(::MyViewModel)
}