package org.arba.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.arba.data.db.dao.TaskDao
import org.arba.data.model.TaskItemLocal

@Database(entities = [TaskItemLocal::class], version = 2, exportSchema = false)
@ConstructedBy(TaskDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun getDao(): TaskDao
    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables(): Unit {}
}

