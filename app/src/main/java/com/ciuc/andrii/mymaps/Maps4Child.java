package com.ciuc.andrii.mymaps;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

import Modules.GPSTracker;
import Modules.ResourseClass;
import Modules.RouteManager;
public class Maps4Child extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap mMap;
    private Button btn_find;
    private Button btn_type;
    Button btn_start;
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
        setContentView(R.layout.activity_maps);

        /*sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ISFIRST", "1").commit();
        editor.putString("USER","CHILD").commit();*/

       /* SharedPreferences sharedPref = Maps4Child.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score), newHighScore);
        editor.commit();*/

        //Створюєсо ссесію
        sharedPreferences = getSharedPreferences("State",MODE_PRIVATE);
        //створюємо едітор для запису тексту
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //записуємо в нього значення по ключу
        editor.putString("KEY_SAVE","2");
        //зберугаємо
        editor.apply();

        //sharedPreferences = getPreferences(MODE_PRIVATE);
        //загружаємо дані по ключу
        // edit.setText();
       // Toast.makeText(Maps4Child.this, sharedPreferences.getString("KEY_SAVE",""), Toast.LENGTH_SHORT).show();
       /// Toast.makeText(MainActivity.this, "Save", Toast.LENGTH_SHORT).show();



        Toast.makeText(Maps4Child.this, "Дитина", Toast.LENGTH_SHORT).show();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Мої координати
        gpsTracker = new GPSTracker(Maps4Child.this);

        double myLat = 0;
        double myLong = 0;

        if (!gpsTracker.canGetLocation()) {
            gpsTracker.showSettingsAlert();
        } else {
            myLat = gpsTracker.getLatitude();
            myLong = gpsTracker.getLongitude();
        }
        myLocation = new LatLng(myLat, myLong);

        btn_find = findViewById(R.id.btn_find);
        btn_type = findViewById(R.id.btn_type);
        edit_find = findViewById(R.id.edit_Find);


        // Шукати адресу
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("State",MODE_PRIVATE);
                //створюємо едітор для запису тексту
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //записуємо в нього значення по ключу
                editor.putString("KEY_SAVE","-1");
                //зберугаємо
                editor.apply();
                //SharedPreferences.Editor editor = sharedPreferences.edit();
                //editor.putString("USER", "").commit();
                /*String location = edit_find.getText().toString();
                List<Address> addressList = null;
                if (!location.isEmpty()) {
                    Geocoder geocoder = new Geocoder(Maps4Child.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    /*

                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
                    InputMethodManager imm = (InputMethodManager) Maps4Child.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
*/
              //  }
            }
        });

        // Змінити тип карти
        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                else
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);*/

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
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



/*
    @Override
    public void onMapReady(GoogleMap googleMap) {
       /* mMap = googleMap;
        routeManager = new RouteManager(StartActivity.this,progressDialog,mMap);

        //LatLng Maruska = new LatLng(48.394710, 25.952522);
        LatLng MyHome = new LatLng(48.289231,25.932362);
        LatLng BusStation = new LatLng(48.372625,25.930097);
        // Створення та малювання маршруту між двома точками
        routeManager.createroute(BusStation,MyHome);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        googleMap.setMyLocationEnabled(true);
        mMap.addMarker(new MarkerOptions().position(myLocation).title("myLocation").icon(BitmapDescriptorFactory.fromResource(R.drawable.mark)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,13));*/
    //  }

}