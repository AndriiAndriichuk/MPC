package com.ciuc.andrii.mymaps;

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

import Modules.DBChildren;
import Modules.DBParents;

public class AutoR_Child extends AppCompatActivity {
    private DBChildren dataBase;
    private SQLiteDatabase sqLiteDatabase;
    EditText edit_c_key;
    EditText edit_email;
    Button btn_c_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autor_child_activity);

        edit_c_key = findViewById(R.id.edit_child_key);
        edit_email = findViewById(R.id.edit_email);
        btn_c_key = findViewById(R.id.btn_c_key);

        dataBase = new DBChildren(AutoR_Child.this);
        sqLiteDatabase = dataBase.getReadableDatabase();

        btn_c_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = dataBase.getReadableDatabase();
                Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Ch WHERE parent_email = \'" + edit_email.getText().toString().trim() + "\' AND " + "password=\'" + edit_c_key.getText().toString().trim() + "\';",null);
                if (!c.moveToFirst()){
                    Toast.makeText(AutoR_Child.this, "Помилка. Перевірте введені дані.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AutoR_Child.this, "Привіт, " + c.getString(c.getColumnIndex("child_name")), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AutoR_Child.this, Maps4Child.class));
                }
                c.close();
                sqLiteDatabase.close();
            }
        });
    }

}
