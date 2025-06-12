package com.example.todoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp.Todo


@Database(entities = [Todo::class], version = 1)
// declaring these converter in database so that room can understand how to convert date to long and long to date
@TypeConverters(Converters::class)
abstract class TodoDatabase: RoomDatabase() { // extend it with roomdatabase so we can access the methods of roomdatabase

    companion object {             // we make a companion object so we did not need to create an instance of the class to access the properties and methods
        const val NAME = "Todo_DB"
    }

    abstract fun gettodoDao(): TodoDao  // so database will understand how to interact with the TodoDao interface

}