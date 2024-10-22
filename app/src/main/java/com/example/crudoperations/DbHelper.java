package com.example.crudoperations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    //1.DataBase and Version
    private static  final String DATABASE_NAME = "student_db";
    private static final int DATABASE_VERSION = 1;

    //2.Table and Column names
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_COURSE = "course";

    //3.Constructor
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //4.Query to Create table
        //CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT,
        // name TEXT,age INTEGER,course TEXT);

        String query = "CREATE TABLE "+TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_NAME +" TEXT,"
                + COLUMN_AGE + " INTEGER,"
                + COLUMN_COURSE + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertStudent(StudentModel studentModel){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(COLUMN_NAME,studentModel.getName());
        values.put(COLUMN_AGE,studentModel.getAge());
        values.put(COLUMN_COURSE,studentModel.getCourse());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int updateStudent(StudentModel studentModel){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(COLUMN_NAME,studentModel.getName());
        values.put(COLUMN_AGE,studentModel.getAge());
        values.put(COLUMN_COURSE,studentModel.getCourse());

        return db.update(TABLE_NAME,values,COLUMN_ID+ "=? ",new String[]{String.valueOf(studentModel.getId())});
    }

    public int deleteStudent(int id){

        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(TABLE_NAME,COLUMN_ID+ "=?", new String[]{String.valueOf(id)});
    }

    public List<StudentModel> readStudents(){
        List<StudentModel> studentModelList= new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM " +TABLE_NAME,null);

        if (cursor.moveToFirst()) {
            do{
                StudentModel studentModel=new StudentModel(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE))
                );
                studentModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));

                studentModelList.add(studentModel);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentModelList;
    }
}
