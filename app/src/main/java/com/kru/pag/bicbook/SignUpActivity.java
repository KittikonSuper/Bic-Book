package com.kru.pag.bicbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText idCardEditText, passEditText;
    private String idCardString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Bind Widget
        bindWidget();
    }// Main Method

    private void bindWidget() {
        idCardEditText =(EditText) findViewById(R.id.editText);
        passEditText = (EditText) findViewById(R.id.editText2);

    }

    public void  clickSignUpSign(View view) {
        idCardString = idCardEditText.getText().toString().trim();
        passwordString = passEditText.getText().toString().trim();

        //check Space
        if (idCardString.equals("")|| passwordString.equals("")) {
            //Have Space
            myToast("กรุณากรอกให้ครบ ค่ะ");
        }else {
            //No Space

        }
    }// clickSignup

    private void myToast(String strToase) {
        Toast.makeText(SignUpActivity.this, strToase, Toast.LENGTH_SHORT).show();

    }
}// Main Class
