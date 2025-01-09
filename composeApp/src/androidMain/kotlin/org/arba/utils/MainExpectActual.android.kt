package org.arba.utils

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import org.arba.data.db.AppDatabase
import org.koin.mp.KoinPlatform

actual fun getTypePlatform(): Type {
    return Type.Mobile
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext = KoinPlatform.getKoin().get<Application>()
    val dbFile = appContext.getDatabasePath(DB_NAME)
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}