package org.arba.utils

import androidx.room.Room
import androidx.room.RoomDatabase
import org.arba.data.db.AppDatabase
import platform.Foundation.NSHomeDirectory


actual fun getTypePlatform(): Type {
    return Type.Mobile
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_NAME"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
//        factory = { AppDatabase::class::instantiateImpl() }
    )
}