package com.example.todolist.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todolist.model.Note;

import java.util.ArrayList;
import java.util.List;

public class FakeMainRepository implements MainRepository {

    private MutableLiveData<List<Note>> notes = new MutableLiveData<>();
    private ArrayList<Note> listOfNotes = new ArrayList<>();
    @Override
    public void addNote(Note note) {
        listOfNotes.add(note);
        notes.setValue(listOfNotes);
    }

    @Override
    public void deleteNote(Note note) {
        for(Note current : listOfNotes){
            if(current==note){
                listOfNotes.remove(current);
            }
        }
        notes.setValue(listOfNotes);
    }

    @Override
    public void updateNote(Note note) {
        for(int i = 0; i<listOfNotes.size();i++){
            Note current = listOfNotes.get(i);
            if(current.getId()==note.getId()){
                listOfNotes.set(i, note);
            }
        }
        notes.setValue(listOfNotes);
    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        return notes;
    }
}
