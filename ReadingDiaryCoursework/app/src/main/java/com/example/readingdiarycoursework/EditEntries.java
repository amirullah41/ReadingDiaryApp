package com.example.readingdiarycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

public class EditEntries extends AppCompatActivity {

    EditText nameInput;
    EditText childInput;
    EditText teacherInput;
    EditText dateInput;
    EditText timeInput;
    EditText pageInput;
    TextView check;
    myDbAdapter helper;

    List<String> returnedInfo = new ArrayList<String>();

    Button updateButton;

    String bookName, childComment, teacherComment, date, time, pageNo;
    String bookTitle, childComments, teacherComments, dateReturned, timesReturned, pagesReturned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entries);
        nameInput = (EditText) findViewById(R.id.book_title2);
        childInput = (EditText) findViewById(R.id.child_Coms);
        teacherInput = (EditText) findViewById(R.id.teacher_Comments);
        dateInput = (EditText) findViewById(R.id.date_Read);
        timeInput = (EditText) findViewById(R.id.time_Read);
        pageInput = (EditText) findViewById(R.id.page_Read);

        updateButton = findViewById(R.id.updateButton);

        helper = new myDbAdapter(this);

        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        int idInt = Integer.parseInt(id);


        returnedInfo = helper.getRowById(idInt);
        bookTitle = returnedInfo.get(0);
        childComments = returnedInfo.get(1);
        teacherComments = returnedInfo.get(2);
        dateReturned = returnedInfo.get(3);
        timesReturned = returnedInfo.get(4);
        pagesReturned = returnedInfo.get(5);
    }

    public void submitUpdate(View view) {
        bookName = nameInput.getText().toString();
        childComment = childInput.getText().toString();
        teacherComment = teacherInput.getText().toString();
        date = dateInput.getText().toString();
        time = timeInput.getText().toString();
        pageNo = pageInput.getText().toString();

        String newBookTitle = bookName;
        String newChildComment = childComment;
        String newTeacherComment = teacherComment;
        String newDate = date;
        String newTime = time;
        String newPage = pageNo;

        helper.updateBookTitle(bookTitle, bookName);
        helper.updateChildCom(childComments, newChildComment);
        helper.updateParentCom(teacherComments, newTeacherComment);
        helper.updateDate(dateReturned, newDate);
        helper.updateTime(timesReturned, newTime);
        helper.updatePageNo(timesReturned, newPage);
        finish();


    }
}