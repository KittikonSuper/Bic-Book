package com.kru.pag.bicbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ShowserviceActivity extends AppCompatActivity {


    //Explicit
    private TextView showNameTextView;
    private ListView serviceListView;
    private String nameString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showservice);




        bindWidget();


    }// Main Method

    private void bindWidget() {

    }


    public void clickReadOrder(View view) {

    }// clickReadOrder
}// Main Class
