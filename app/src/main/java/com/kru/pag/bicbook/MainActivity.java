package com.kru.pag.bicbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //Explicit
    private EditText userEditText, passEditText;
    private String userString, PasswordString;
    private MyManage myManage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();
        myManage = new MyManage(this);
    }   // Main Method
    public void clickSignInMain(View view) {

        userString = userEditText.getText().toString().trim();
        PasswordString = passEditText.getText().toString().trim();
        //Check Space
        if (userString.equals("") || passEditText.equals("")) {
            //Have Space
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this,"มีช่องว่าง", "กรุณากรอกทุกช่องค่ะ");
        } else {

        }
    }// clickSingIn

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText5);
        passEditText = (EditText) findViewById(R.id.editText6);

    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   // Main Class
