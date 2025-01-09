package org.arba.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
class TaskItemLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titleTask: String? = "",
    val descTask: String? = "",
    val dateTask: String? = "",
    var isSelectedCheck: Boolean = false
)

//@Entity(tableName = "task_parent")
//class TaskItemParentLocal(
//    val titleParentTime: String? = "",
//    var listTaskChild: MutableList<TaskItemLocal> = mutableListOf()
//)

val listDummyTask = listOf(
    TaskItemLocal(
        0,
        "Standup meeting",
        "13.30",
        "13.30",
        false
    ),

    TaskItemLocal(
        0,
        "Bug fix ui add",
        "13.30",
        "13.30",
        false
    ),

    TaskItemLocal(
        0,
        "Checking UI",
        "13.30",
        "13.30",
        false
    )
)

//val listDummyTaskParent = listOf(
//    TaskItemParentLocal(
//        "Today (15 AGustus 2025)",
//        listDummyTask.toMutableList()
//    ),
//    TaskItemParentLocal(
//        "Yesterday (15 AGustus 2025)",
//        listDummyTask.toMutableList()
//    ),
//)