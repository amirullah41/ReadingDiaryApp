package com.example.readingdiarycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchedInfo extends AppCompatActivity {

    myDbAdapter helper;

    EditText searchBox;
    TextView searchedInfo;

    List<String> returnedInfo = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_info);
        helper = new myDbAdapter(this);

        searchBox = findViewById(R.id.searchBox);
        searchedInfo = findViewById(R.id.searchedInfoOnScreen);
    }

    public void searched(View view) {

        String searchInfo = searchBox.getText().toString();
       /// returnedInfo = helper.searchForEntry(searchInfo);
       // searchedInfo.setText(returnedInfo.toString());
    }
}