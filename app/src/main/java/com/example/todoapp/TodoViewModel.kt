package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.db.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel : ViewModel() {

//    private var _todoList = MutableLiveData<List<Todo>>()
//    val todoList : LiveData<List<Todo>> = _todoList
    // we need to create instance of database so below we can use it so make another file called MainApplication and create instance of database there
     val todoDao = MainApplication.todoDatabase.gettodoDao()
    val todoList : LiveData<List<Todo>> = todoDao.getAllTodo()

//
//    fun getAllTodo(){
//        _todoList.value = TodoManager.getAllTodo().reversed()
//    }

    fun addTodo(title : String){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(Todo(title = title, createdAt = Date.from(Instant.now())))
        }

        // now database does not know how to save the date field in the database so we need to convert it to long and then save it in the database
        // creating another class of type converter
        // now we need to annotate the Todo class with @TypeConverters(DateConverter::class)
    }

    fun deleteTodo(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }


}