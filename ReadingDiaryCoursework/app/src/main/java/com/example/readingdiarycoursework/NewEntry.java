package com.example.readingdiarycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewEntry extends AppCompatActivity {

    EditText nameInput;
    EditText childInput;
    EditText teacherInput;
    EditText dateInput;
    EditText timeInput;
    EditText pageInput;
    myDbAdapter helper;

    String bookName, childComment, teacherComment, date, time, pageNo;

    TextView entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        helper = new myDbAdapter(this);

        nameInput = (EditText) findViewById(R.id.book_title);
        childInput = (EditText) findViewById(R.id.Child_Coms);
        teacherInput = (EditText) findViewById(R.id.Teacher_Comments);
        dateInput = (EditText) findViewById(R.id.Date_Read);
        timeInput = (EditText) findViewById(R.id.Time_Read);
        pageInput = (EditText) findViewById(R.id.Page_Read);

        entries = findViewById(R.id.Entries);
    }

    public void submitEntry(View view) {
        bookName = nameInput.getText().toString();
        childComment = childInput.getText().toString();
        teacherComment = teacherInput.getText().toString();
        date = dateInput.getText().toString();
        time = timeInput.getText().toString();
        pageNo = pageInput.getText().toString();

        if (bookName != null  && childComment != null && teacherComment != null && date != null && time != null & pageNo != null) {
            Intent replyIntent = new Intent();
            replyIntent.putExtra("book", bookName);
            replyIntent.putExtra("childCom", childComment);
            replyIntent.putExtra("teacherCom", teacherComment);
            replyIntent.putExtra("date", date);
            replyIntent.putExtra("time", time);
            replyIntent.putExtra("pageNo", pageNo);
            setResult(RESULT_OK, replyIntent);
            addEntry(view);
            finish();
        }
        else {
            Toast emailError = Toast.makeText(this, "Entry not added! Please fill in all fields.", Toast.LENGTH_LONG);
            emailError.show();
        }

    }

    public void addEntry(View view){
        String p1 = bookName;
        String p2 = childComment;
        String p3 = teacherComment;
        String p4 = date;
        String p5 = time;
        String p6 = pageNo;

        if(p1.isEmpty() || p2.isEmpty() || p3.isEmpty() || p4.isEmpty() || p5.isEmpty() || p6.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Missing Details");
        }
        else {
            long id = helper.insertData(p1, p2, p3, p4, p5, p6);
            if(id <=0) {
                Message.message(getApplicationContext(),"Insertion Successful");
                //entries.setText("");
            }
        }
    }

}