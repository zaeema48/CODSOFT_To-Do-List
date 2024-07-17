package com.example.to_dolist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities={ToDoListModel.class}, version=1)    //version of table (before adding any column
public abstract class RoomDB extends RoomDatabase {
        private static RoomDB database;
        private static String databaseName="toDoListDB";

        //creating the database
        public synchronized static RoomDB
        getInstance(Context context){
            if(database==null){
                database= Room.databaseBuilder(context.getApplicationContext(),
                                RoomDB.class,databaseName)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return database;
        }

        public abstract ToDoListDao toDoListDao();
}
