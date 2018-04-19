package com.ciuc.andrii.mymaps;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static org.bitbucket.dollar.Dollar.*;

public class AutoR_Parent extends AppCompatActivity {
    ListView listView;
    private DataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;
    private ContentValues values = null;
    Button btn_add_child,btn_next;
    EditText edit_child_name;


    private SharedPreferences sharedPreferences;
    ArrayList<String> temp = new ArrayList<>();
    ArrayList<String> child_key = new ArrayList<>();
    LinearLayout linearLayout;
    LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_child);

        btn_add_child = findViewById(R.id.btn_add_child);
        btn_next = findViewById(R.id.btn_next);
        edit_child_name = findViewById(R.id.edit_add_c_name);
        linearLayout = findViewById(R.id.linear_create_c);

        dataBase = new DataBase(AutoR_Parent.this);
        values = new ContentValues();
        sqLiteDatabase = dataBase.getWritableDatabase();

        btn_add_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp.add(0,edit_child_name.getText().toString());
                child_key.add(0,randomString(3));

                values.clear();
                values.put("name",temp.get(0));
                values.put("child_key",child_key.get(0));
                Toast.makeText(AutoR_Parent.this, "Привіт, " + child_key.get(0), Toast.LENGTH_SHORT).show();

                sqLiteDatabase.insert("C", null, values);

                lParams.gravity = Gravity.CENTER;
                Button btnNew = new Button(AutoR_Parent.this);
                btnNew.setText (temp.get(0) + '\n' + child_key.get(0));
                linearLayout.addView(btnNew, lParams);
                edit_child_name.setText("");
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AutoR_Parent.this, "Поїхали", Toast.LENGTH_SHORT).show();
                /*sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USER","PARENT").commit();*/

/*
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("user", "2");
                editor.commit();*/
                startActivity(new Intent(AutoR_Parent.this, Maps4Parent.class));
            }
        });
    }



    String validCharacters = $('0', '9').join() + $('A', 'Z').join()/* + $('a', 'z').join()*/;

    String randomString(int length) {
        return $(validCharacters).shuffle().slice(length).toString();
    }
}
