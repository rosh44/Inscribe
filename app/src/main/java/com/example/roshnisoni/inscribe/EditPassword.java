package com.example.roshnisoni.inscribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditPassword extends AppCompatActivity {
TextView newpwd,confirm;
Button continuebut, confirmbut,newbut;
EditText oldpwd,newpwdtext,cpwdtext;
DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        helper = new DatabaseHelper(this);
        oldpwd=(EditText)findViewById(R.id.editText);
        continuebut=(Button)findViewById(R.id.button3);

        newpwd=(TextView)findViewById(R.id.newpd);
        newpwdtext=(EditText)findViewById(R.id.editText2);
        newbut=(Button)findViewById(R.id.button4);

        confirm=(TextView)findViewById(R.id.confirm);
        cpwdtext=(EditText)findViewById(R.id.editText3);
        confirmbut=(Button)findViewById(R.id.button5);

        continuebut.setOnClickListener(new View.OnClickListener(){

                                  @Override
                                  public void onClick(View view) {
                                      String pwd1=oldpwd.getText().toString();
                                      if(pwd1.length()<4){
                                          Toast.makeText(getApplicationContext(),"Password must have 4 numbers", Toast.LENGTH_LONG).show();
                                      }
                                      else{
                                          String cpwd=helper.getCorrectPassword();
                                          if(pwd1.equals(cpwd)){
                                              newpwd.setVisibility(View.VISIBLE);
                                              newbut.setVisibility(View.VISIBLE);
                                              newpwdtext.setVisibility(View.VISIBLE);
                                              newpwdtext.requestFocus();
                                          }
                                          else{
                                              Toast.makeText(getApplicationContext(),"Wrong Password!!", Toast.LENGTH_LONG).show();

                                          }

                                      }
                                  }
                              }

        );

        newbut.setOnClickListener(new View.OnClickListener(){

                                    @Override
                                    public void onClick(View view) {
                                        String pwd1=newpwdtext.getText().toString();
                                        if(pwd1.length()<4){
                                            Toast.makeText(getApplicationContext(),"Password must have 4 numbers", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            confirmbut.setVisibility(View.VISIBLE);
                                            cpwdtext.setVisibility(View.VISIBLE);
                                            confirm.setVisibility(View.VISIBLE);
                                            cpwdtext.requestFocus();
                                        }
                                    }
                                }

        );

        confirmbut.setOnClickListener(new View.OnClickListener(){

                                     @Override
                                     public void onClick(View view) {
                                         String pwd=oldpwd.getText().toString();
                                         String pwd1=newpwdtext.getText().toString();
                                         String cpwd1=cpwdtext.getText().toString();
                                         if(cpwd1.equals("")){
                                             Toast.makeText(getApplicationContext(),"Confirm password cannot be empty", Toast.LENGTH_LONG).show();
                                         }
                                         else if(!pwd1.equals(cpwd1)){
                                             Toast.makeText(getApplicationContext(),"Passwords does not match", Toast.LENGTH_LONG).show();

                                         }
                                         else{
                                            boolean update= helper.updatePassword(cpwd1,pwd);
                                             if(update){
                                                 Toast.makeText(getApplicationContext(),"Password Updated",Toast.LENGTH_LONG).show();
                                             }else{
                                                 Toast.makeText(getApplicationContext(),"Password not updated",Toast.LENGTH_LONG).show();
                                             }
                                             Intent i = new Intent(EditPassword.this, EnterPasswordActivity.class);
                                             startActivity(i);
                                         }
                                     }
                                 }

        );

    }
}
