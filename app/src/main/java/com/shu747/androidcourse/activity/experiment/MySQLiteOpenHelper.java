package com.shu747.androidcourse.activity.experiment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @Author: shuo747
 * @Date: 2019/5/10 15:48
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_STUDENT = "create table Student ("
            + "id integer primary key, "
            + "name text,"
            + "age integer,"
            + "city text)";

    private Context context;
    private SQLiteDatabase db;
    private static MySQLiteOpenHelper instance;
    private static final String DATABASE_NAME = "StudentDB.db";
    private static final int DATABASE_VERSION = 1;

    public static MySQLiteOpenHelper getInstance(Context context) {
        if (null == instance) {
            synchronized (MySQLiteOpenHelper.class) {
                if (null == instance) {
                    instance = new MySQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
                }
            }
        }
        return instance;
    }

    public void insertStu(int id, String name, int age, String city) {
        db.execSQL("INSERT INTO TABLE_NAME (id , name , age , city)"
                + "VALUES ("
                + id
                + name
                + age
                + city
                + ");");
    }

    public void insertStu2(String id, String name, String age, String city) {
        this.db = getWritableDatabase();
        db.execSQL("INSERT INTO Student (id , name , age , city)"
                + "VALUES ("
                + id + ","
                + name + ","
                + age + ","
                + city
                + ");");
    }

    public void insertStu(String id, String name, String age, String city) {
        this.db = getWritableDatabase();
        db.execSQL("INSERT INTO Student (id , name , age , city)"
                + "VALUES (?,?,?,?)", new String[]{id, name, age, city});
        db.close();
    }


    public ArrayList<String>[] queryStu() {
        this.db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Student", null);
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String>[] lists = new ArrayList[4];
        for(int i = 0; i<lists.length;i++) lists[i] = new ArrayList();
        lists[0].add("id");
        lists[1].add("name");
        lists[2].add("age");
        lists[3].add("city");
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                String city = cursor.getString(cursor.getColumnIndex("city"));
                //stringBuffer.append(id+"\t"+name+"\t"+age+"\t"+city+"\n");
                lists[0].add(String.valueOf(id));
                lists[1].add(name);
                lists[2].add(String.valueOf(age));
                lists[3].add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lists;
    }

    private MySQLiteOpenHelper(Context context, String name,
                               SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT);
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Student");
        onCreate(db);
    }
}
