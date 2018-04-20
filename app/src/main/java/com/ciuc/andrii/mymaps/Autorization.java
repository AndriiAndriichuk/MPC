package com.ciuc.andrii.mymaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Autorization extends AppCompatActivity {
    Button btn_start;
    private RadioButton radio_parent, radio_child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autor_activity);

        radio_parent = findViewById(R.id.radio_I_am_parent);
        radio_child = findViewById(R.id.radio_I_am_child);

        btn_start = findViewById(R.id.btn_go);

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
