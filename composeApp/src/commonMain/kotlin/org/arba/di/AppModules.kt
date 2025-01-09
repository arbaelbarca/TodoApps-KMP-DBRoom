package org.arba.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.arba.data.db.AppDatabase
import org.arba.data.db.dao.TaskDao
import org.arba.domain.repository.TaskRepository
import org.arba.utils.getDatabaseBuilder
import org.arba.viewmodel.TaskViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Logger
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

//expect fun provideModulDatabase(): Module

val provideViewModel = module {
    viewModel { TaskViewModel(get()) }
}

val provideReposity = module {
    single { TaskRepository(get<AppDatabase>().getDao()) }
}

val provideDbModule = module {
    single {
        getRoomDatabase(getDatabaseBuilder())
    }
}

fun initializeKoin() {
    startKoin {
        modules(
            provideDbModule,
            provideViewModel,
            provideReposity,
//            provideViewModel,
//            provideRepostiry,
//            provideHttpClient
        )
    }
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}