package org.arba.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.arba.data.model.TaskItemLocal
import org.arba.data.model.listDummyTask
import org.arba.domain.repository.TaskRepository
import org.arba.ui.state.UiState

class TaskViewModel(
    val taskRepository: TaskRepository
) : ViewModel() {
    val mutableStateTask = MutableStateFlow<UiState<List<TaskItemLocal>>>(UiState.Idle)
    val stateTask: StateFlow<UiState<List<TaskItemLocal>>> = mutableStateTask

    val mutableStateInsertTask = MutableStateFlow(TaskItemLocal())
    val stateInsertTask: StateFlow<TaskItemLocal> = mutableStateInsertTask


    fun getTaskList() {
        mutableStateTask.value = UiState.Loading
        viewModelScope.launch {
            delay(1500)
            try {
                val listTask = taskRepository.getAllTask().reversed()
                if (listTask.isNotEmpty())
                    mutableStateTask.emit(UiState.Success(listTask))
                else mutableStateTask.emit(UiState.Error("Empty data"))
            } catch (e: Exception) {
                e.printStackTrace()
                mutableStateTask.emit(UiState.Error("Error Exception data"))
            }
        }
    }

    fun addTaskItem(taskItemLocal: TaskItemLocal) {
        viewModelScope.launch {
            try {
                taskRepository.insertTask(taskItemLocal)
                getTaskList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteTaskItem(idTask: Long) {
        viewModelScope.launch {
            try {
                taskRepository.deleteTask(idTask)
                getTaskList()
            } catch (e: Exception) {
            }
        }
    }


//    fun addTaskItem(taskItemLocal: TaskItemLocal) {
//        getTaskList()
//    }
}