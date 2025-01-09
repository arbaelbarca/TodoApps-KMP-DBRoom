package org.arba.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import org.arba.data.model.TaskItemLocal

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    suspend fun getAllTaskDb(): List<TaskItemLocal>

    @Update
    suspend fun updateTaskDb(taskItemLocal: TaskItemLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTaskDb(taskItemLocal: TaskItemLocal)

    @Query("DELETE FROM task WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Long)

    @Query("SELECT * FROM task WHERE id = :taskId")
    suspend fun getTaskById(taskId: Long): TaskItemLocal?
}