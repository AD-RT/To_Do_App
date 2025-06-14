package com.example.todoapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun TodoListpPage(viewModel: TodoViewModel){

    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 50.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier= Modifier.weight(1f),
                value = inputText,
                onValueChange = {
                    inputText = it
                },
                label = { Text("Enter your task") }
            )
            IconButton(onClick = {
                viewModel.addTodo(inputText)
                inputText = ""
            }) {
                Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add",
                    modifier = Modifier
                        .size(30.dp))
            }
        }

        todoList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it){index: Int, item: Todo ->
                        TodoItem(item = item, onDelete = {
                            viewModel.deleteTodo(item.id)
                        })
                    }
                }
            )
        }?:
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { Text(text = "No Tasks !",
            fontSize = 15.sp,) }

    }

}

@Composable
fun TodoItem(item : Todo,onDelete : ()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm aa, dd/mm//yyyy", Locale.ENGLISH).format(item.createdAt),
                fontSize = 10.sp,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = item.title,
                fontSize = 17.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}
















