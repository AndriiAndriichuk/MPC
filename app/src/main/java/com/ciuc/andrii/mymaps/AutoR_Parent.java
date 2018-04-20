package com.ciuc.andrii.mymaps;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import Modules.DBChildren;
import Modules.DBParents;

import static org.bitbucket.dollar.Dollar.*;

public class AutoR_Parent extends AppCompatActivity {
    private DBParents dbParents;
    private DBChildren dbChildren;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteDatabase sqLiteDatabase1;
    private ContentValues values = null;
    Button btn_add_child,btn_next;
    Button btn_sing_in_parent,btn_register_par;
    EditText edit_child_name;
    EditText edit_par_name;
    EditText edit_par_email;
    EditText edit_par_passw;

    ArrayList<String> temp = new ArrayList<>();
    ArrayList<String> child_key = new ArrayList<>();
    LinearLayout linearLayout;
    LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    String validCharacters = $('0', '9').join() + $('A', 'Z').join() /*+ $('a', 'z').join()*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autor_parent_activity);

        btn_add_child = findViewById(R.id.btn_add_child);
        btn_next = findViewById(R.id.btn_next);
        btn_sing_in_parent = findViewById(R.id.btn_sing_in_par);
        btn_register_par = findViewById(R.id.btn_register_par);

        edit_child_name = findViewById(R.id.edit_add_c_name);
        edit_par_name = findViewById(R.id.edit_par_name);
        edit_par_email = findViewById(R.id.edit_par_email);
        edit_par_passw = findViewById(R.id.edit_par_passw);

        linearLayout = findViewById(R.id.layout_add_child);

        dbParents = new DBParents(AutoR_Parent.this);
        dbChildren = new DBChildren(AutoR_Parent.this);
        values = new ContentValues();

        sqLiteDatabase = dbParents.getWritableDatabase();
        sqLiteDatabase1 = dbParents.getReadableDatabase();

        btn_add_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp.add(0,edit_child_name.getText().toString());
                child_key.add(0,randomString(9));

                sqLiteDatabase1 = dbChildren.getWritableDatabase();
                values.clear();
                values.put("child_name",temp.get(0));
                values.put("parent_name",edit_par_name.getText().toString());
                values.put("parent_email",edit_par_email.getText().toString());
                values.put("password",child_key.get(0));
                sqLiteDatabase1.insert("Ch", null, values);

                lParams.gravity = Gravity.CENTER;
                Button btnNew = new Button(AutoR_Parent.this);
                btnNew.setText (temp.get(0) + '\n' + child_key.get(0));
                btnNew.setAllCaps(false);
                linearLayout.addView(btnNew, lParams);
                edit_child_name.setText("");
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AutoR_Parent.this, "Поїхали", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AutoR_Parent.this, Maps4Parent.class));
            }
        });

        btn_sing_in_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase1 = dbParents.getReadableDatabase();
                Cursor c = sqLiteDatabase1.rawQuery("SELECT * FROM Par WHERE name = \'" + edit_par_name.getText().toString() + "\' AND " + "email=\'" + edit_par_email.getText().toString() + "\' AND " + "password=\'" + edit_par_passw.getText().toString() + "\';",null);
                if (!c.moveToFirst()){
                    Toast.makeText(AutoR_Parent.this, "Помилка.", Toast.LENGTH_SHORT).show();
                }
                c.close();
                sqLiteDatabase1.close();

                findViewById(R.id.layout_autor_parent).setVisibility(View.INVISIBLE);
                findViewById(R.id.layout_add_child).setVisibility(View.VISIBLE);
            }
        });

        btn_register_par.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase1 = dbParents.getReadableDatabase();
                Cursor c = sqLiteDatabase1.rawQuery("SELECT * FROM Par WHERE email = \'" + edit_par_email.getText().toString() + "\'",null);
                if (c.moveToFirst()){
                    Toast.makeText(AutoR_Parent.this, "Такий email вже існує.", Toast.LENGTH_SHORT).show();
                }else{
                    values.clear();
                    values.put("name",edit_par_name.getText().toString());
                    values.put("email",edit_par_email.getText().toString());
                    values.put("password",edit_par_passw.getText().toString());
                    sqLiteDatabase1.insert("Par", null, values);
                    Toast.makeText(AutoR_Parent.this, "Зареєстровано", Toast.LENGTH_SHORT).show();

                }
                c.close();
                sqLiteDatabase1.close();
            }
        });
    }

    String randomString(int length) {
        return $(validCharacters).shuffle().slice(length).toString();
    }
}
