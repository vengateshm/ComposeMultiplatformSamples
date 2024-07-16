package dev.vengateshm.compose.multiplatform.samples

import android.app.Application
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import samples.di.koin.initKoinDi

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        initKoinDi {
            androidContext(this@MyApp)
        }
    }
}