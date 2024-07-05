package samples.di.koin

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoinDi(koinAppDeclaration: KoinAppDeclaration? = null) {
    startKoin {
        koinAppDeclaration?.invoke(this)
        modules(mySharedModule, myPlatformModule)
    }
}