package com.kru.pag.bicbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        //Delete SQLite
        deleteSQLite();

//        myManage.addUser("test1", "test", "test", "test");
//        myManage.addService("test2", "test", "test", "test");

        //Syn Json to SQLite
        synJSON();
    }   // Main Method

    private void synJSON() {

        //Connected Http
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intIndex = 0;
        while (intIndex <= 1) {

            //1 Create InputStream
            InputStream inputStream = null;
            String[] urlJSON = {"http://swiftcodingthai.com/bang/php_get_user.php",
                    "http://swiftcodingthai.com/bang/php_get_service.php"};
            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlJSON[intIndex]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();


            } catch (Exception e) {
                Log.d("31 March", "Input ==>" + e.toString());
            }


            //2Create JSON String

            String strJSON = null;

            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String strLine = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((strLine = bufferedReader.readLine()) !=null) {
                    stringBuilder.append(strLine);
                }
                inputStream.close();
                strJSON = stringBuilder.toString();


            } catch (Exception e) {
                Log.d("31 March", "strJSON ==>" + e.toString());
            }

            //3 Update To SQLite

            try {
                JSONArray jsonArray = new JSONArray(strJSON);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    switch (intIndex) {
                        case 0:
                            //for userTABLE
                            String strUser = jsonObject.getString(MyManage.column_User);
                            String strPassword = jsonObject.getString(MyManage.column_Password);
                            String strName= jsonObject.getString(MyManage.column_Name);
                            String strIDcard= jsonObject.getString(MyManage.column_IDcard);

                            myManage.addUser(strUser, strPassword, strName, strIDcard);
                            break;
                        case 1:
                            //for serviceTABLE
                            String strIDcar = jsonObject.getString(MyManage.column_IDcar);
                            String strImage = jsonObject.getString(MyManage.column_Image);
                            String strDescription = jsonObject.getString(MyManage.column_Description);
                            String strPrice = jsonObject.getString(MyManage.column_Price);

                            myManage.addService(strIDcar, strImage, strDescription, strPrice);


                            break;

                    }
                }

            } catch (Exception e) {
                Log.d("31 March", "Update ==>" + e.toString());
            }

            intIndex += 1;

        }

    }//synJSON

    private void deleteSQLite() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpneHelper.databese_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
        sqLiteDatabase.delete(MyManage.service_table, null, null);

    }

    public void clickSignInMain(View view) {

        userString = userEditText.getText().toString().trim();
        PasswordString = passEditText.getText().toString().trim();
        //Check Space
        if (userString.equals("") || passEditText.equals("")) {
            //Have Space
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this,"มีช่องว่าง", "กรุณากรอกทุกช่องค่ะ");
        } else {
            checkUser();


        }
    }// clickSingIn

    private void checkUser() {

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpneHelper.databese_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE User = " + "'" + userString + "'", null);
            cursor.moveToFirst();
            String[] resultStrings = new String[cursor.getColumnCount()];
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                resultStrings[i] = cursor.getString(i);
            }
            cursor.close();

            //Check Password
            if (PasswordString.equals(resultStrings[2])) {
                //Password True
                Toast.makeText(this,
                        "ยินดีต้อนรับ " + resultStrings[3],
                        Toast.LENGTH_SHORT).show();

            } else {
                //Password False
                MyAlertDialog myAlertDialog = new MyAlertDialog();
                myAlertDialog.myDialog(this, "Password ผิด", "กรุณาพิมพ์ใหม่ Password ผิด");
            }



        } catch (Exception e) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, "User ผิด", "ไม่มี User นี่ในฐานข้อมูลของเรา");
        }

    }   // checkUser

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText5);
        passEditText = (EditText) findViewById(R.id.editText6);

    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   // Main Class
