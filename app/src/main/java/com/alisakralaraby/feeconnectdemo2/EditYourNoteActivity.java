package com.alisakralaraby.feeconnectdemo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class EditYourNoteActivity extends AppCompatActivity implements TextWatcher {

    private EditText editNoteEditText;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_your_note);

        editNoteEditText = (EditText)findViewById(R.id.edit_note_edittext);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1){

            editNoteEditText.setText(AddNoteActivity.notesList.get(noteId));
        }

        editNoteEditText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        AddNoteActivity.notesList.set(noteId, String.valueOf(s));
        AddNoteActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.alisakralaraby.feeconnectdemo2", Context.MODE_PRIVATE);

        if (AddNoteActivity.set == null){

            AddNoteActivity.set = new HashSet<String>();
        }else {

            AddNoteActivity.set.clear();
        }
        AddNoteActivity.set.addAll(AddNoteActivity.notesList);
        sharedPreferences.edit().putStringSet("notes", AddNoteActivity.set).apply();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
