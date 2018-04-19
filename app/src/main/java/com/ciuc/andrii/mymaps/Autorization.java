package com.ciuc.andrii.mymaps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Autorization extends AppCompatActivity {
    Button btn_start;
    private RadioButton radio_parent, radio_child;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        radio_parent = findViewById(R.id.radio_I_am_parent);
        radio_child = findViewById(R.id.radio_I_am_child);

        btn_start = findViewById(R.id.btn_go);
        //Toast.makeText(Autorization.this, "Autorization", Toast.LENGTH_SHORT).show();

        //sharedPreferences = getPreferences(MODE_PRIVATE);
        //загружаємо дані по ключу
        // edit.setText();
        //Toast.makeText(Autorization.this, sharedPreferences.getString("KEY_SAVE",""), Toast.LENGTH_SHORT).show();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radio_parent.isChecked()) {
                    Intent intent = new Intent(Autorization.this, AutoR_Parent.class);
                    startActivity(intent);
                } else if (radio_child.isChecked()) {
                    Intent intent = new Intent(Autorization.this, AutoR_Child.class);
                    startActivity(intent);
                }
            }
        });
    }
}
