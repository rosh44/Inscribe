package com.example.roshnisoni.inscribe;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayContentActivity extends AppCompatActivity {
    private EditText ti,c;
    private Button edit,delete,share;
    DatabaseHelper helper;
String title,con;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_content);
         title=getIntent().getExtras().getString("title");
         con =getIntent().getExtras().getString("content");
        ti = (EditText) findViewById(R.id.title);
        c = (EditText) findViewById(R.id.content);
        edit = (Button) findViewById(R.id.update);
        delete= (Button) findViewById(R.id.delete);
        share= (Button) findViewById(R.id.share);
        helper=new DatabaseHelper(this);
            ti.setText(title);
           c.setText(con);
          id=helper.getid(title,con);

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
           String newtitle=ti.getText().toString();
           String newcontent=c.getText().toString();
                boolean update=helper.updateNotes(id,title,con,newtitle,newcontent);
                if(update){
                    Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Not updated",Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(DisplayContentActivity.this, display.class);
                startActivity(i);

            }

        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean delete=helper.deleteNotes(id);
                if(delete){
                    Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Not deleted",Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(DisplayContentActivity.this, display.class);
                startActivity(i);

            }

        });

        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String title=ti.getText().toString();
                String content=c.getText().toString();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Note from Inscribe");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Note from Inscribe!\n" +
                        "Title: " + title +
                        " \nContent: " + content );
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }

        });

    }


}

