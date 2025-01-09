package org.arba

import android.app.Application
import org.arba.di.initializeKoin
import org.arba.di.provideDbModule
import org.arba.di.provideReposity
import org.arba.di.provideViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            modules(
                provideDbModule,
                provideReposity,
                provideViewModel
            )
        }
    }
}