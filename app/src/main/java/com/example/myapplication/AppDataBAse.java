package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Note.class},version = 1,exportSchema = true)
public abstract class AppDataBAse extends RoomDatabase {
 public static    AppDataBAse appDataBAse;
 public abstract NoteDao noteDao();
 public static AppDataBAse getInstance(Context context){
     if (appDataBAse==null){
        appDataBAse= Room.databaseBuilder(context,AppDataBAse.class,"db_note").allowMainThreadQueries().build();
     }
     return  appDataBAse;
 }

}
