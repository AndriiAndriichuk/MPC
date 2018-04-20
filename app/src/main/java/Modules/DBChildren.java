package Modules;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBChildren extends SQLiteOpenHelper {

    public DBChildren(Context context) {
        super(context, "Ch.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Ch(id integer primary key autoincrement, child_name text,parent_name text,parent_email,password text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
