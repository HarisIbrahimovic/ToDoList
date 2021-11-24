package com.example.todolist.viewmodel;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.todolist.model.Note;
import com.example.todolist.repository.FakeMainRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private MainViewModel viewModel;

    @Before
    public void setUp(){
        viewModel = new MainViewModel();
        viewModel.init(new FakeMainRepository());
    }

    @Test
    public void addNoteEmptyFields(){
        Note note = new Note(1,"","test",true);
        viewModel.addNote(note);
        assertThat(viewModel.getToastMessage().getValue().equals("Fill in the fields."));
    }

    @Test
    public void updateNoteEmptyFields(){
        Note note = new Note(1,"","test",true);
        viewModel.updateNote(note);
        assertThat(viewModel.getToastMessage().getValue().equals("Fill in the fields."));
    }

    @Test
    public void nameTooLong(){
        Note note = new Note(1,"TestTestTestTestTestTestTestTestTestTestTestTest","test",true);
        viewModel.addNote(note);
        assertThat(viewModel.getToastMessage().getValue().equals("Name too long."));
    }

    @Test
    public void nameTooShort(){
        Note note = new Note(1,"Tes","test",true);
        viewModel.addNote(note);
        assertThat(viewModel.getToastMessage().getValue().equals("Name too short."));
    }


}