package com.alisakralaraby.feeconnectdemo2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    private ListView notesListView;
    static ArrayList<String> notesList = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static Set<String> set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences1 = this.getSharedPreferences("com.alisakralaraby.feeconnectdemo2", Context.MODE_PRIVATE);

        set = sharedPreferences1.getStringSet("notes", null);
        notesList.clear();
        if (set != null){

            notesList.addAll(set);
        }else {

            notesList.add("example note");
            set = new HashSet<String>();
            set.addAll(notesList);
            sharedPreferences1.edit().putStringSet("notes", set).apply();
        }

        notesListView = (ListView)findViewById(R.id.notesListView);

        arrayAdapter = new ArrayAdapter(AddNoteActivity.this, android.R.layout.simple_list_item_1, notesList);

        notesListView.setAdapter(arrayAdapter);
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), EditYourNoteActivity.class);
                intent.putExtra("noteId", position);
                startActivity(intent);
            }
        });

        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(AddNoteActivity.this)
                        .setMessage("delete note ??")
                        .setTitle("are you sure")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                notesList.remove(position);
                                SharedPreferences sharedPreferences3 = AddNoteActivity.this.getSharedPreferences("com.alisakralaraby.feeconnectdemo2", Context.MODE_PRIVATE);
                                if (set == null){

                                    set = new HashSet<String>();
                                }else {

                                    set.clear();
                                }
                                set.addAll(notesList);
                                sharedPreferences3.edit().putStringSet("notes", set).apply();
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("no", null)
                        .show();

                return true;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences2 = AddNoteActivity.this.getSharedPreferences("com.alisakralaraby.feeconnectdemo2", Context.MODE_PRIVATE);
                notesList.add("");
                if (set == null){

                    set = new HashSet<String>();
                }else {

                    set.clear();
                }
                set.addAll(notesList);
                sharedPreferences2.edit().putStringSet("notes", set).apply();

                Intent intent = new Intent(getApplicationContext(), EditYourNoteActivity.class);
                intent.putExtra("noteId", notesList.size() - 1);
                startActivity(intent);
            }
        });
    }

}
