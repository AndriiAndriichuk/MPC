package com.ciuc.andrii.mymaps;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Maps4Parent extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps4_parent);
        Toast.makeText(Maps4Parent.this, "Батько", Toast.LENGTH_SHORT).show();

        //Створюєсо ссесію
        sharedPreferences = getSharedPreferences("State",MODE_PRIVATE);
        //створюємо едітор для запису тексту
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //записуємо в нього значення по ключу
        editor.putString("KEY_SAVE","1");
        //зберугаємо
        editor.apply();

        /*SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("USER", "2");
        editor.commit();*/


    }
}
