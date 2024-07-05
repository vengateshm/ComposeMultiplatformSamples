package samples.di.koin

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val myPlatformModule: Module = module {
    singleOf(::DBClient)
    viewModelOf(::MyViewModel)
}