package com.example.readingdiarycoursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }
    public long insertData(String bookTitle, String childCom, String parentCom, String date, String time, String page)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.BOOKTITLE, bookTitle);
        contentValues.put(myDbHelper.CHILDCOM, childCom);
        contentValues.put(myDbHelper.PARENTCOM, parentCom);
        contentValues.put(myDbHelper.DATE, date);
        contentValues.put(myDbHelper.TIME, time);
        contentValues.put(myDbHelper.PAGES, page);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }
    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.BOOKTITLE,myDbHelper.CHILDCOM, myDbHelper.PARENTCOM, myDbHelper.DATE, myDbHelper.TIME ,myDbHelper.PAGES};
        Cursor cursor
                =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndexOrThrow(myDbHelper.UID));
            String bookTitle
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.BOOKTITLE));
            String  childCom
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.CHILDCOM));
            String teacherCom
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENTCOM));
            String date
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.DATE));
            String time
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TIME));
            String pages
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PAGES));
            buffer.append(cid+ "  Book Title: " + bookTitle + "  Childs Comments: " + childCom + "  Teachers Comments: " + teacherCom+ "  Date Read: " + date + "  Time Read: " + time + "  Pages Read: " + pages +  " \n");
        }
        return buffer.toString();
    }

    public List<String> getRowById(int ID) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.BOOKTITLE,myDbHelper.CHILDCOM, myDbHelper.PARENTCOM, myDbHelper.DATE, myDbHelper.TIME ,myDbHelper.PAGES};
        Cursor cursor
                =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        List<String> returnInfo = new ArrayList<String>();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndexOrThrow(myDbHelper.UID));
            String bookTitle
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.BOOKTITLE));
            String  childCom
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.CHILDCOM));
            String teacherCom
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENTCOM));
            String date
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.DATE));
            String time
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TIME));
            String pages
                    =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PAGES));
            if (cid == ID) {
                buffer.append(cid+ "  Book Title: " + bookTitle + "  Childs Comments: " + childCom + "  Teachers Comments: " + teacherCom+ "  Date Read: " + date + "  Time Read: " + time + "  Pages Read: " + pages +  " \n");
                returnInfo.add(bookTitle);
                returnInfo.add(childCom);
                returnInfo.add(teacherCom);
                returnInfo.add(date);
                returnInfo.add(time);
                returnInfo.add(pages);
            }

        }
        return returnInfo;
    }

    public  int delete(String id)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={id};
        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.UID+" = ?",whereArgs);
        return  count;
    }

    public int updateBookTitle(String oldBookTitle, String newBookTitle)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.BOOKTITLE,newBookTitle);
        String[] whereArgs= {oldBookTitle};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.BOOKTITLE+" = ?",whereArgs);
        return count;
    }

    public int updateChildCom(String oldChildCom, String newChildCom)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.CHILDCOM,newChildCom);
        String[] whereArgs= {oldChildCom};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.CHILDCOM+" = ?",whereArgs);
        return count;
    }

    public int updateParentCom(String oldParentCom, String newParentCom)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.PARENTCOM,newParentCom);
        String[] whereArgs= {oldParentCom};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.PARENTCOM+" = ?",whereArgs);
        return count;
    }

    public int updateDate(String oldDate, String newDate)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DATE,newDate);
        String[] whereArgs= {oldDate};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.DATE+" = ?",whereArgs);
        return count;
    }

    public int updateTime(String oldTime, String newTime)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TIME,newTime);
        String[] whereArgs= {oldTime};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.TIME+" = ?",whereArgs);
        return count;
    }

    public int updatePageNo(String oldPage, String newPage)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.PAGES,newPage);
        String[] whereArgs= {oldPage};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.PAGES+" = ?",whereArgs);
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String BOOKTITLE = "BookTitle";    //Column II
        private static final String CHILDCOM= "ChildCom";    // Column III
        private static final String PARENTCOM= "ParentCom";
        private static final String DATE= "Date";
        private static final String TIME= "Time";
        private static final String PAGES= "Pages";

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+BOOKTITLE+" VARCHAR(255),"+CHILDCOM+" VARCHAR(225), "+PARENTCOM+" VARCHAR(255),"+DATE+" VARCHAR(255), "+TIME+" VARCHAR(255), "+PAGES+" VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;
        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}