package com.example.todolist.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.todolist.model.Note;
import com.example.todolist.repository.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MainRepository repository;
    private final MutableLiveData<String > toastMessage= new MutableLiveData<>();

    public void init(MainRepository mainRepository){
        this.repository=mainRepository;
    }

    public void addNote(Note note){
        if(note.getName().equals("")||note.getDescription().equals("")) {
            toastMessage.setValue("Fill in the fields.");
            return;
        }
        if(note.getName().length()<4) {
            toastMessage.setValue("Name too short.");
            return;
        }
        if(note.getName().length()>30) {
            toastMessage.setValue("Name too long.");
            return;
        }
        repository.addNote(note);
        toastMessage.setValue("Note created.");
    }

    public void deleteNote(Note note){
        repository.deleteNote(note);
        toastMessage.setValue("Note removed.");
    }

    public void updateNote(Note note){
        if(note.getName().equals("")||note.getDescription().equals("")) {
            toastMessage.setValue("Fill in the fields.");
            return;
        }
        if(note.getName().length()<4) {
            toastMessage.setValue("Name too short.");
            return;
        }
        if(note.getName().length()>30) {
            toastMessage.setValue("Name too long.");
            return;
        }
        repository.updateNote(note);
        toastMessage.setValue("Note updated.");
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public LiveData<List<Note>> getListOfNotes() {
        LiveData<List<Note>> listOfNotes = repository.getAllNotes();
        return listOfNotes;
    }
}
