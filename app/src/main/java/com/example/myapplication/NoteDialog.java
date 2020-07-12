package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NoteDialog extends DialogFragment {
    OnNoteEdit onNoteEdit;
    private Note note;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
        note=getArguments().getParcelable("note");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnNoteEdit)
            onNoteEdit= (OnNoteEdit) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.item_notedialog,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        final EditText titleEt=view.findViewById(R.id.et_notedialog_title) ;
      final EditText  descEt=view.findViewById(R.id.et_notedialog_desc);
        Button saveBtn=view.findViewById(R.id.btn_noteDialog_save);
        if(note!=null)
        {
            titleEt.setText(note.getTitle());
            descEt.setText(note.getDescription());
        }
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titleEt.length()>0&& descEt.length()>0){

                    if(note==null){
                        Note note=new Note();
                        note.setTitle(titleEt.getText().toString());
                        note.setDescription(descEt.getText().toString());
                        onNoteEdit.OnSave(note);
                        dismiss();

                    }else {

                        note.setTitle(titleEt.getText().toString());
                        note.setDescription(descEt.getText().toString());
                        onNoteEdit.onEdit(note);
                        dismiss();
                    }



                }


            }
        });
        builder.setView(view);
        return builder.create();
    }

    public static NoteDialog newInstance(Note note) {

        Bundle args = new Bundle();
        args.putParcelable("note",note);
        NoteDialog fragment = new NoteDialog();
        fragment.setArguments(args);
        return fragment;
    }
    interface OnNoteEdit{
        void OnSave(Note note);
        void onEdit(Note note);
    }

}
