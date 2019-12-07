package com.liveinbits.mvvmactivity.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.liveinbits.mvvmactivity.converter.DateConverter;

@Database(entities = {NoteModel.class},version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "notedatabase.db";
    public static volatile AppDatabase instance=null; //access from main memory only ---one thread at time can access

    public abstract NoteDao noteDao();

    public static final Object lock=new Object();
    public static AppDatabase getInstance(Context context){
        if(instance==null){
            synchronized (lock){
                if(instance==null){
                    instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }


}

