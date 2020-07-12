package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteDialog.OnNoteEdit , NoteAdapter.OnUiCalBack {

Button addBtn;
NoteDao noteDao;
AppDataBAse appDataBAse;
NoteAdapter noteAdapter;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDataBAse=AppDataBAse.getInstance(this);
        noteDao=appDataBAse.noteDao();

        noteAdapter=new NoteAdapter(this);
        List<Note> notes=noteDao.getAll();
        noteAdapter.addNotes(notes);
        recyclerView=findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(noteAdapter);
       addBtn=findViewById(R.id.btn_main_add);
 addBtn.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         NoteDialog noteDialog=new NoteDialog();

         noteDialog.show(getSupportFragmentManager(),null);
     }
 });
    }

    @Override
    public void OnSave(Note note) {
noteDao.save(note);
noteAdapter.addNote(note);
recyclerView.smoothScrollToPosition(0);

    }

    @Override
    public void onEdit(Note note) {
        noteDao.update(note);
        noteAdapter.updateNote(note);
    }

    @Override
    public void onDeleteNote(Note note, int position) {

        noteDao.delete(note);
        noteAdapter.deleteNote(position);
    }

    @Override
    public void onEditNote(Note note) {
NoteDialog noteDialog=NoteDialog.newInstance(note);
        noteDialog.show(getSupportFragmentManager(),null);

    }
}