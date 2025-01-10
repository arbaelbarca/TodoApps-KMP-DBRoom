package org.arba.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.arba.data.model.TaskItemLocal
import org.arba.navigation.route.ObjectRouteScreen
import org.arba.theme.Blue
import org.arba.theme.xMediumText
import org.arba.ui.screen.item.ShimmerEffect
import org.arba.ui.screen.item.TaskItem
import org.arba.utils.Type
import org.arba.utils.getTypePlatform
import org.arba.viewmodel.TaskViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(navController: NavController) {

    val taskViewModel = koinViewModel<TaskViewModel>()
    val collectTaskViewModel = taskViewModel.stateTask.collectAsState()

    taskViewModel.getTaskList()

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text("To Do List")
                },
                actions = {
                    NewTaskButton(onClick = {
                        navController.navigate(ObjectRouteScreen.AddNewTask.route)
                    })
                }
            )
        }
    ) { paddingValues ->
        collectTaskViewModel.value.DisplayResult(
            onIdle = {
                ShimmerEffect()
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { itemList ->
                TaskList(taskViewModel, listTaskItem = itemList.toMutableList())
            },
            onError = {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = "Search Data Articles"
                    )
                }
            }
        )
    }

}

@Composable
fun TaskList(
    taskViewModel: TaskViewModel,
    listTaskItem: MutableList<TaskItemLocal>
) {

//    val isCheckItem by remember {
//        mutableStateOf(false)
//    }

    val isDekstop = remember {
        getTypePlatform() == Type.Dekstop
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isDekstop) 3 else 1),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(listTaskItem) { itemsChild ->
            TaskItem(
                taskItemLocal = itemsChild,
                onChecked = itemsChild.isSelectedCheck,
                onDeleteItem = {
                    taskViewModel.deleteTaskItem(itemsChild.id.toLong())
                }
            )
        }

    }

//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(10.dp),
//        verticalArrangement = Arrangement.spacedBy(10.dp)
//    ) {
//        items(listTaskItem.chunked(if (isDekstop) 3 else 1)) { rowItems ->
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(10.dp)
//            ) {
//                rowItems.forEach { itemsChild ->
//                    TaskItem(
//                        taskItemLocal = itemsChild,
//                        onChecked = itemsChild.isSelectedCheck,
//                        onDeleteItem = {
//                            taskViewModel.deleteTaskItem(itemsChild.id.toLong())
//                        }
//                    )
//                }
//
//            }
//        }
//    }
}

@Composable
fun NewTaskButton(
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(Blue),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "New Task",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = xMediumText
            )
        }
    }
}

