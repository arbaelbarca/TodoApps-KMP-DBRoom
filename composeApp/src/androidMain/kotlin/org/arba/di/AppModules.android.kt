package org.arba.di

import org.arba.data.db.AppDatabase
import org.arba.utils.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module
//
//actual fun provideModulDatabase(): Module {
//    single<AppDatabase> { getDatabaseBuilder() }
//}