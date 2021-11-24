package com.example.todolist.repository;

import androidx.lifecycle.LiveData;

import com.example.todolist.model.Note;

import java.util.List;

public interface MainRepository {

    void addNote(Note note);
    void deleteNote(Note note);
    void updateNote(Note note);
    LiveData<List<Note>> getAllNotes();

}
