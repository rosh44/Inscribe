package com.example.roshnisoni.inscribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetPasswordActivity extends AppCompatActivity {
    DatabaseHelper helper;
    EditText pwd,cpwd;
    Button next,start;
    TextView confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(this);
        if(helper.getPassword()==0){
            setContentView(R.layout.activity_set_password);
            pwd=(EditText)findViewById(R.id.editText);
            next=(Button)findViewById(R.id.button3);
            cpwd=(EditText)findViewById(R.id.editText2);
            start=(Button)findViewById(R.id.button4);
            confirm=(TextView)findViewById(R.id.confirm);
            next.setOnClickListener(new View.OnClickListener(){

                                        @Override
                                        public void onClick(View view) {
                                            String pwd1=pwd.getText().toString();
                                            if(pwd1.length()<4){
                                                Toast.makeText(getApplicationContext(),"Password must have 4 numbers", Toast.LENGTH_LONG).show();
                                            }
                                            else{
                                                confirm.setVisibility(View.VISIBLE);
                                                cpwd.setVisibility(View.VISIBLE);
                                                start.setVisibility(View.VISIBLE);
                                                cpwd.requestFocus();
                                            }
                                        }
                                    }

            );

            start.setOnClickListener(new View.OnClickListener(){

                                         @Override
                                         public void onClick(View view) {
                                             String pwd1=pwd.getText().toString();
                                             String cpwd1=cpwd.getText().toString();
                                             if(cpwd1.equals("")){
                                                 Toast.makeText(getApplicationContext(),"Confirm password cannot be empty", Toast.LENGTH_LONG).show();
                                             }
                                             else if(!pwd1.equals(cpwd1)){
                                                 Toast.makeText(getApplicationContext(),"Passwords does not match", Toast.LENGTH_LONG).show();

                                             }
                                             else{
                                                 helper.insertPassword(pwd1);
                                                 Intent i = new Intent(SetPasswordActivity.this, display.class);
                                                 startActivity(i);
                                             }
                                         }
                                     }

            );
        }
        else {
            Intent i = new Intent(SetPasswordActivity.this, EnterPasswordActivity.class);
            startActivity(i);
        }


    }
}
