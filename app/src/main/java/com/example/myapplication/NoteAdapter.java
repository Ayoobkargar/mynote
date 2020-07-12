package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViweHolder> {
    List<Note> noteList=new ArrayList<>();
    OnUiCalBack onUiCalBack;

    public NoteAdapter(OnUiCalBack onUiCalBack) {
        this.onUiCalBack = onUiCalBack;
    }

    @NonNull
    @Override
    public NoteViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViweHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViweHolder holder, int position) {
holder.onBindNote(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
    public void addNotes(List<Note> notes){
      this.noteList.addAll(notes);
        notifyDataSetChanged();
    }
    public void addNote(Note note){
       this.noteList.add(0,note);
        notifyItemInserted(0);
    }
    public void deleteNote(int position){
        noteList.remove(position);
        notifyItemRemoved(position);

    }
    public void  updateNote(Note  note){
        for (int i = 0; i <noteList.size() ; i++) {
          if(noteList.get(i).getId()==note.getId())  {
              noteList.set(i,note);
              notifyItemChanged(i);
          }
        }
    }

    public class NoteViweHolder extends  RecyclerView.ViewHolder {
       TextView titleEt;
        TextView descEt;
        Button deleteBtn;
        Button editBtn;
        public NoteViweHolder(@NonNull View itemView) {

            super(itemView);
            titleEt=itemView.findViewById(R.id.et_note_title);
            descEt=itemView.findViewById(R.id.et_note_desc);
            deleteBtn=itemView.findViewById(R.id.btn_note_delete);
            editBtn=itemView.findViewById(R.id.btn_note_edit);
        }
        public void onBindNote(final Note note){
            titleEt.setText(note.getTitle());
            descEt.setText(note.getDescription());
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUiCalBack.onDeleteNote(note,getAdapterPosition());

                }
            });
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUiCalBack.onEditNote(note);

                }
            });
        }
    }
    interface  OnUiCalBack{
        void onDeleteNote(Note note,int position);
        void onEditNote(Note note);
    }

}
