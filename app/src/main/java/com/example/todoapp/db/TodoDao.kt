package com.example.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.Todo

@Dao
interface TodoDao {        // this will be an interface that will interact with the database


    // you can access this Todo only if you have annotated it with @Entity
    @Query("SELECT * FROM Todo ORDER BY createdAt DESC")   // you need to write query to get data from the Todo
    fun getAllTodo(): LiveData<List<Todo>>

    @Insert                        // either you can annotate with the insert or write query for the same.
    fun addTodo(todo: Todo)

    @Query("DELETE FROM Todo WHERE id = :id")  // here :id is the parameter that you will pass while calling this function
    fun deleteTodo(id: Int)

}