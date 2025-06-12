package com.example.todoapp

import android.app.Application
import androidx.room.Room
import com.example.todoapp.db.TodoDatabase


// this MainApplication we need to add in the manifest file as android:name=".MainApplication" inside the application tag so that
// it will be called in start of the application when the application is created.

class MainApplication: Application() { // extend application class to access the application context

    companion object{
        lateinit var todoDatabase: TodoDatabase // lateinit is used to initialize the variable later
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(
            context = applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.NAME
        ).build()
    }
}