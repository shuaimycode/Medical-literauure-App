package com.he.yiliao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;

import com.he.yiliao.bean.LiteratureObj;

import java.util.ArrayList;

public class LiteratureSQLite extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public LiteratureSQLite(Context context) {
        super(context, "db_literature", null, 1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS literature(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "topic TEXT," +
                "time TEXT," +
                "author TEXT)");
        db.execSQL("INSERT INTO literature(topic,time,author)VALUES('心力衰竭的过去，现在和未来','2017-09-27','中华心血管病杂志')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void add(String topic,String time,String author){
        db.execSQL("INSERT INTO literature(topic,time,author)VALUES(?,?,?)",new Object[]{topic,time,author});
    }
    public ArrayList<LiteratureObj> getAllDATA(){
        ArrayList<LiteratureObj> list = new ArrayList<LiteratureObj>();
        Cursor cursor = db.query("literature",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String topic = cursor.getString(cursor.getColumnIndexOrThrow("topic"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
            list.add(new LiteratureObj(topic,author,time));
        }
        return list;
    }

}
