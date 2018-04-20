package com.ciuc.andrii.mymaps;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modules.GPSTracker;
import Modules.RouteManager;
public class Maps4Child extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap mMap;
    private FloatingActionButton btn_find_m;
    private FloatingActionButton btn_type;
    private FloatingActionButton btn_exit;
    Button btn_find;
    private RadioButton radio_parent, radio_child;
    private EditText edit_find;
    private ProgressDialog progressDialog;
    private RouteManager routeManager;
    private GPSTracker gpsTracker;
    private LatLng myLocation;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_4_child_activity);

        sharedPreferences = getSharedPreferences("State",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("KEY_SAVE","2");
        editor.apply();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


       // Toast.makeText(Maps4Child.this,String.valueOf(emailValidator("andriuchuk77@gmail.com")),Toast.LENGTH_LONG).show();

        btn_find_m = findViewById(R.id.btn_find_m);
        btn_type = findViewById(R.id.btn_type);
        edit_find = findViewById(R.id.edit_Find);
        btn_exit = findViewById(R.id.btn_exit);
        btn_find = findViewById(R.id.btn_find);


        btn_find_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.linear_search).setVisibility(View.VISIBLE);
            }
        });

        // Шукати адресу
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String location = edit_find.getText().toString();
                List<Address> addressList = null;
                if (!location.isEmpty()) {
                    Geocoder geocoder = new Geocoder(Maps4Child.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
                    InputMethodManager imm = (InputMethodManager) Maps4Child.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    findViewById(R.id.linear_search).setVisibility(View.INVISIBLE);
                }
            }
        });

        // Змінити тип карти
        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                else
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            }
        });

        //Вихід з облікового запису
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("State",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("KEY_SAVE","-1");
                editor.apply();
                startActivity(new Intent(Maps4Child.this, Autorization.class));
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        googleMap.setMyLocationEnabled(true);
       }

    public static boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}