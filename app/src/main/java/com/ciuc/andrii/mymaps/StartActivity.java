package com.ciuc.andrii.mymaps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class StartActivity extends AppCompatActivity{
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("State",MODE_PRIVATE);

        Toast.makeText(StartActivity.this, sharedPreferences.getString("KEY_SAVE","-1"), Toast.LENGTH_SHORT).show();
        switch (sharedPreferences.getString("KEY_SAVE","-1")){
            case "1":
                startActivity(new Intent(StartActivity.this, Maps4Parent.class));
                break;
            case "2":
                startActivity(new Intent(StartActivity.this, Maps4Child.class));
                break;
            case "-1":
                startActivity(new Intent(StartActivity.this, Autorization.class));
                break;
        }
    }
}
