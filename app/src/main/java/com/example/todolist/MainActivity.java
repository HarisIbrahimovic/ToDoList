package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.adapter.NoteAdapter;
import com.example.todolist.db.Db;
import com.example.todolist.model.Note;
import com.example.todolist.repository.DefaultMainRepository;
import com.example.todolist.viewmodel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements NoteAdapter.ClickListener {

    private FloatingActionButton addButton;
    private NoteAdapter adapter;
    private MainViewModel viewModel;
    private AlertDialog dialog;
    private View myView;
    private EditText noteName;
    private EditText noteDesc;
    private Button createNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Db.setInstance(getApplication());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init(new DefaultMainRepository());
        setUpViews();
        onClicks();
        observe();
    }

    private void observe() {
        viewModel.getToastMessage().observe(this, s ->
                Toast.makeText(MainActivity.this, s,Toast.LENGTH_SHORT).show()
        );
        viewModel.getListOfNotes().observe(this,list-> adapter.setListOfNotes(list));


    }

    private void onClicks() {
        addButton.setOnClickListener(v -> {
            createDialog();
            setUpDialog();
            createNote.setOnClickListener(view -> {
                viewModel.addNote(new Note(noteName.getText().toString(),noteDesc.getText().toString(),false));
                dialog.dismiss();
            });
        });
    }

    private void setUpDialog() {
        noteName = myView.findViewById(R.id.noteNameAddNote);
        noteDesc = myView.findViewById(R.id.noteDescAddNote);
        createNote = myView.findViewById(R.id.createNote);

    }

    private void createDialog() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(MainActivity.this);
        myView = View.inflate(MainActivity.this,R.layout.add_note_view,null);
        myDialog.setView(myView);
        dialog = myDialog.create();
        dialog.show();
    }

    private void setUpViews() {
        addButton = findViewById(R.id.addNoteButton);
        RecyclerView recyclerView = findViewById(R.id.notesRecyclerView);
        adapter = new NoteAdapter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void checkBoxClicked(Note note) {
        note.setCompleted(!note.isCompleted());
        viewModel.updateNote(note);
    }

    @Override
    public void noteClicked(Note note) {
        createDialog();
        setUpDialog();
        noteName.setText(note.getName());
        noteDesc.setText(note.getDescription());
        createNote.setText(R.string.update);
        createNote.setOnClickListener(view -> {
            note.setName(noteName.getText().toString());
            note.setDescription(noteDesc.getText().toString());
            viewModel.updateNote(note);
        });
    }

    @Override
    public void deleteNote(Note note) {
        viewModel.deleteNote(note);
    }
}