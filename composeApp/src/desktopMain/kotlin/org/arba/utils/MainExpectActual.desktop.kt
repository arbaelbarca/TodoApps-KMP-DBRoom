package org.arba.utils

import androidx.room.Room
import androidx.room.RoomDatabase
import org.arba.data.db.AppDatabase
import java.io.File

actual fun getTypePlatform(): Type {
    return Type.Dekstop
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_NAME)
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
}