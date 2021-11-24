package com.example.todolist.model;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.todolist.db.Db;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;


@RunWith(AndroidJUnit4.class)
public class NoteDaoTest {


    private NoteDao noteDao;
    private Db db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, Db.class).build();
        noteDao = db.noteDao();
    }


    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertNote(){
        Note note = new Note(1,"Haris","test",true);
        noteDao.insertNote(note);
        List<Note> notes = noteDao.getAllNotesNonLiveData();
        assertThat(notes.contains(note));
    }

    @Test
    public void deleteNote(){
        Note note = new Note(1,"Haris","test",true);
        noteDao.insertNote(note);
        List<Note> notes = noteDao.getAllNotesNonLiveData();
        noteDao.deleteNote(note);
        assertThat(!notes.contains(note));
    }

    @Test
    public void updateNote(){
        Note note = new Note(1,"Haris","test",true);
        noteDao.insertNote(note);
        note.setCompleted(false);
        List<Note> notes = noteDao.getAllNotesNonLiveData();
        assertThat(!notes.get(0).isCompleted());
    }








}
