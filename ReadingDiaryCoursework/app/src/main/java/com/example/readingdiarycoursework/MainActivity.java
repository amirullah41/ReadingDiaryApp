package com.example.readingdiarycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    myDbAdapter helper;
    TextView entries;
    EditText editId;
    String bookTitle, date, page, time, childCom, parentCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entries = findViewById(R.id.Entries);
        editId = findViewById(R.id.edit_id);
        helper = new myDbAdapter(this);
    }

    public void addNew(View view) {
        Intent addNewIntent = new Intent(this, NewEntry.class);
        startActivityForResult(addNewIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void viewSelectedEntries (View view) {
        String data = helper.getData();

        entries.setText(data);
    }


    public void deleteEntries(View view) {
        String id = editId.getText().toString();
        if (id.length() != 0) {
            helper.delete(id);
        }else {
            Toast error = Toast.makeText(this, "Enter an entry ID please! ", Toast.LENGTH_LONG);
            error.show();
        }
    }

    public void editEntries(View view) {
        Intent editIntent = new Intent(this, EditEntries.class);
        String id = editId.getText().toString();
        if (id.length() != 0) {
            editIntent.putExtra("id", id);
            startActivity(editIntent);
        }else {
            Toast error = Toast.makeText(this, "Enter an entry ID please! ", Toast.LENGTH_LONG);
            error.show();
        }

    }

    public void sendEmail(View view) { //Only works on Pixel 3A API 30 as it uses G-Mail.

        String id = editId.getText().toString();
        List<String> returnedInfo = new ArrayList<String>();
       // int idInt = Integer.parseInt(id);

        if (id.length() != 0) {
            int idInt = Integer.parseInt(id);
            returnedInfo = helper.getRowById(idInt);
            String newBookTitle = returnedInfo.get(0);
            String newChildComments = returnedInfo.get(1);
            String newTeacherComments = returnedInfo.get(2);
            String newDateReturned = returnedInfo.get(3);
            String newTimesReturned = returnedInfo.get(4);
            String newPagesReturned = returnedInfo.get(5);

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            String subject = "Book Entry ID : " + idInt;
            String body = "BookName: " + newBookTitle +  "\n" + "Child Comments: " + newChildComments + "\n" + "Teahcers Comments: " + newTeacherComments + "\n" + "Date Read: " + newDateReturned + "\n" + "Time Read: " + newTimesReturned + "\n" + "Pages Read: " + newPagesReturned+ "\n";
            String uriText = "mailto:youremail@gmail.com" + "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body);

            Uri uri = Uri.parse(uriText);
            emailIntent.setData(uri);
            startActivity(Intent.createChooser(emailIntent, "Send email"));
        }else {
            Toast emailError = Toast.makeText(this, "Enter an entry ID please! ", Toast.LENGTH_LONG);
            emailError.show();
        }
    }
}