package com.kru.pag.bicbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    //Explicit
    private TextView descripTextView, priceTextView;
    private ImageView imageView;
    private String[] resultStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Bindget
        bindWidget();

        //Get Value from Intent
        getValueFromIntent();

        //ShowView
        showView();

    } //Main Method

    public void clickOrder(View view) {
        Intent intent = new Intent(DetailActivity.this, OrderActivity.class);
        startActivity(intent);
    }//

    public void clickBackDetail(View view) {
        finish();

    }// clickOrder

    private void showView() {
        descripTextView.setText(resultStrings[3]);
        priceTextView.setText(resultStrings[4]);

        Picasso.with(this)
                .load(resultStrings[2])
                .resize(200,200)
                .into(imageView);
    }//ShowView

    private void getValueFromIntent() {

        String strID = getIntent().getStringExtra("id");

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpneHelper.databese_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM serviceTABLE WHERE _id = " + "'" + strID + "'", null);
        cursor.moveToFirst();
        resultStrings = new String[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            resultStrings[i] = cursor.getString(i);
        }//for
        cursor.close();


    }//getValue

    private void bindWidget() {
        descripTextView = (TextView) findViewById(R.id.textView11);
        priceTextView = (TextView) findViewById(R.id.textView13);
        imageView = (ImageView) findViewById(R.id.imageView3);

    }
} // Main Class
