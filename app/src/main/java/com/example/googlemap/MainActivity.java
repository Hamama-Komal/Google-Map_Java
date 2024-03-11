package com.example.googlemap;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private Spinner mapModeSpinner;

    private int GOOGLE_MAP_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        // To change the map mode
        //googleMap.setMapType(GOOGLE_MAP_TYPE);

        // OVERLAYS ON MAP
        LatLng location = new LatLng(31.5204, 74.3587);

        // To set a Marker on Map
        googleMap.addMarker(new MarkerOptions().position(location).title("Lahore"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));

        // To draw a circle
        googleMap.addCircle(new CircleOptions().center(location).radius(3000).fillColor(Color.LTGRAY).strokeColor(Color.DKGRAY));


        // To draw a polygon
        /*googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(31.5204, 74.3587), new LatLng(24.5204, 74.3587), new LatLng(42.5204, 74.3587),new LatLng(55.5204, 74.3587))
                .strokeColor(Color.YELLOW));
        */


        // To set a image om map
        /*googleMap.addGroundOverlay(new GroundOverlayOptions()
                .position(location, 1000f,1000f)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.img))
                .clickable(true));
        */


        googleMap.setOnMapClickListener(latLng -> {


            // show marker on clicked location
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Here"));

            // To get the address of selected locations
            Geocoder geocoder = new Geocoder(this);
            ArrayList<Address> addresses;
            try {

                addresses = (ArrayList<Address>) geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
                //Log.d("Addresses", addresses.get(0).getAddressLine(0));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });


       /* // To change the types of map
        mapModeSpinner = findViewById(R.id.map_mode_spinner);
        mapModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               // updateMapMode(position);
                switch (position) {
                    case 1:
                        GOOGLE_MAP_TYPE = GoogleMap.MAP_TYPE_SATELLITE;
                        break;
                    case 2:
                        GOOGLE_MAP_TYPE = GoogleMap.MAP_TYPE_HYBRID;
                        break;
                    case 3:
                        GOOGLE_MAP_TYPE = GoogleMap.MAP_TYPE_TERRAIN;
                        break;
                    default:
                        GOOGLE_MAP_TYPE = GoogleMap.MAP_TYPE_NORMAL;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
*/

    }
}