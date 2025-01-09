package org.arba.utils

import androidx.room.RoomDatabase
import org.arba.data.db.AppDatabase
import org.arba.project.Platform
import org.koin.core.module.Module

expect fun getTypePlatform() : Type

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>

//expect fun platformModule(): Module
