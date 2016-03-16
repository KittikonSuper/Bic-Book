package com.kru.pag.bicbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

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

    public  void clickSingInMain(View view){

    }//clickSignInMain

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
            checkIDcard();

        }
    }// clickSignup

    private void checkIDcard() {
        if (idCardString.length() == 13){
            //id card True
        confirmData(idCardString, passwordString);
        }else {
            //id card False
            myToast("รหัสบัตรไม่ถูกต้อง");
        }

    }// chekcIDcard

    private void confirmData(String idCardString, String passwordString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_myaccount);
        builder.setTitle("โปรดตรวจสอบข้อมูล");
        builder.setMessage("รหัสบัตรประชาชน  = " + idCardString + "/n" +
                "Password = " + passwordString);
        builder.setCancelable(false);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                updateDataToServer();
                dialogInterface.dismiss();

            }// onClick
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }// onClick

        });
        builder.show();
    }//confirmData

    private void updateDataToServer() {
        //Connected Http
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        try {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            nameValuePairs.add(new BasicNameValuePair("ID_Card", idCardString));
            nameValuePairs.add(new BasicNameValuePair("Password", passwordString));

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.swiftcodingthai.com/bic/php_add_user_master.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpClient.equals(httpPost);
            myToast("อัพเดทข้อมูล บน Server แล้วว");
            finish();
        }catch (Exception e){
            myToast("ไม่สามารถเชื่อมต่อ Server ได้");

        }

    }// updateDataToServer

    private void myToast(String strToase) {
        Toast.makeText(SignUpActivity.this, strToase, Toast.LENGTH_SHORT).show();

    }
}// Main Class
