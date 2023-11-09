package com.example.roshnisoni.inscribe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterPasswordActivity extends AppCompatActivity {
    DatabaseHelper helper;
    EditText pwd;
    Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        helper = new DatabaseHelper(this);
            pwd=(EditText)findViewById(R.id.editText);
            go=(Button)findViewById(R.id.button3);
        go.setOnClickListener(new View.OnClickListener(){

                                    @Override
                                    public void onClick(View view) {
                                        String pwd1=pwd.getText().toString();
                                        if(pwd1.length()<4){
                                            Toast.makeText(getApplicationContext(),"Password must have 4 numbers", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            String cpwd=helper.getCorrectPassword();
                                            if(pwd1.equals(cpwd)){
                                                Intent i = new Intent(EnterPasswordActivity.this, display.class);
                                                startActivity(i);
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(),"Wrong Password!!", Toast.LENGTH_LONG).show();

                                            }

                                        }
                                    }
                                }

        );


    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                //  .setIcon(android.R.drawable.logo2.png)
                .setTitle("Exit Inscribe")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //super.onBackPressed();
                        //Or used finish();
                        moveTaskToBack(true);
                        EnterPasswordActivity.this.finish();
                        //finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();

    }
}
