package com.example.roshnisoni.inscribe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class TextNotesActivity extends AppCompatActivity {
    private EditText ti, c;
    private Button add, view;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_notes);
        ti = (EditText) findViewById(R.id.title);
        c = (EditText) findViewById(R.id.content);
        add = (Button) findViewById(R.id.button);
        //   view = (Button) findViewById(R.id.button2);
        helper = new DatabaseHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String title = ti.getText().toString();
                String content = c.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Title cannot be empty", Toast.LENGTH_LONG).show();
                } else if (content.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Content cannot be empty", Toast.LENGTH_LONG).show();
                } else {
                    addData(title, content);
                }
            }
        });
       /* view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(TextNotesActivity.this, display.class);
                startActivity(i);
            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.attachment_menu, menu);
        return true;
    }



    public void addData(String title, String content) {
        boolean add = helper.saveNotes(title, content);
        if (add) {
            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Not added", Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(TextNotesActivity.this, display.class);
        startActivity(i);

    }


}
