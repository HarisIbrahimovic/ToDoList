package com.example.todolist.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todolist.model.Note;
import com.example.todolist.model.NoteDao;

@Database(entities = {Note.class},version = 1, exportSchema = false)
abstract public class Db extends RoomDatabase {
    public abstract NoteDao noteDao();
    public static Db instance;

    public static synchronized void setInstance(Application application){
        if(instance==null){
            instance = Room.databaseBuilder(application.getApplicationContext(), Db.class, "my_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
    }

    public static Db getInstance() {
        return instance;
    }

    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
        }
    };
}
