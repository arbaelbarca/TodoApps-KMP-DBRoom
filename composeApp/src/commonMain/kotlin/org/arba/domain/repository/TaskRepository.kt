package org.arba.domain.repository

import org.arba.data.db.dao.TaskDao
import org.arba.data.model.TaskItemLocal

class TaskRepository(
    val taskDao: TaskDao
) {
    suspend fun insertTask(task: TaskItemLocal) {
        taskDao.createTaskDb(task)
    }

    suspend fun getAllTask(): List<TaskItemLocal> {
        return taskDao.getAllTaskDb()
    }

    suspend fun deleteTask(idTask: Long) {
        taskDao.deleteTaskById(idTask)
    }

    suspend fun updateTask(task: TaskItemLocal) {
        taskDao.updateTaskDb(task)
    }

}