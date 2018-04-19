package com.ciuc.andrii.mymaps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AutoR_Child extends AppCompatActivity {
    private DataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;
    EditText edit_c_key;
    Button btn_c_key;
    HashMap<String,String> children = new HashMap<>();
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_key);


        dataBase = new DataBase(AutoR_Child.this);
        sqLiteDatabase = dataBase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("C", null, null, null, null, null, null);

        edit_c_key = findViewById(R.id.edit_child_key);
        btn_c_key = findViewById(R.id.btn_c_key);

        if(cursor.moveToFirst()) {
            do {
                children.put(cursor.getString(cursor.getColumnIndex("child_key")),cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        }
        btn_c_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (children.get(edit_c_key.getText().toString()) != null) {
                    //Toast.makeText(AutoR_Child.this, "Привіт, " + children.get(edit_c_key.getText().toString()), Toast.LENGTH_SHORT).show();

                   /* SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("user", "1");
                    editor.commit();

                    Toast.makeText(AutoR_Child.this, sharedPref.getString("user",""), Toast.LENGTH_SHORT).show();

*/


                    startActivity(new Intent(AutoR_Child.this, Maps4Child.class));
                }else{
                    Toast.makeText(AutoR_Child.this, "Помилка доступу", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
