package id.co.myproject.angkutapps_penumpang.view.tracking;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.BookingListener;
import id.co.myproject.angkutapps_penumpang.helper.KeberangkatanListener;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.Driver;
import id.co.myproject.angkutapps_penumpang.model.data_object.Token;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.GoogleMapApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.maps.android.SphericalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TrackingActivity extends FragmentActivity implements OnMapReadyCallback , KeberangkatanListener, BookingListener {

    private static final int MY_PERMISSION_REQUEST_CODE = 732;
    private GoogleMap mMap;
    public static final int UPDATE_INTERVAL = 5900;
    public static final int FASTEST_INTERVAL = 3000;
    public static final int DISPLACEMENT = 10;

    SharedPreferences sharedPreferences;
    String noHpUser, jumlahDewasa, jumlahAnak, jumlahBarang, idDestinasi;
    ApiRequest apiRequest;

    private LocationRequest mLocationReqeust;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;
    private AutocompleteSupportFragment placeTo, placeFrom;

    private String destination, city, from;
    boolean tracking = false, search = false;

    DatabaseReference tb_passenger, tb_destinasi_passenger;
    GeoFire geoFire;

    Marker mCurrentMarker;
    int radius = 1;
    int distance = 1;
    public static final int LIMIT = 3;

    LinearLayout lvPenjemputan, lvDriver, lvInputTujuan, lvPayment, lvActionCall, lvActionShare;
    FloatingActionButton fbBack;
    RelativeLayout rvSearch;
    TextView tvDriver, tvMerkMobil, tvPlat, tvMencari, tvTujuan, tvAsal, tvDistance, tvOnkos, tvWaktu, tvNamaDriver, tvPlatDriver;
    Button btnTelpon, btnNext, btnBookNow, btnCancel;
    EditText etPlaceFrom;
    ProgressBar progressBar;

    private BroadcastReceiver mArrivedRceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvMencari.setText("Driver anda telah sampai di lokasi anda");
            progressBar.setVisibility(View.GONE);
        }
    };

    private BroadcastReceiver mAngkutReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            lvActionCall.setVisibility(View.GONE);
            lvActionShare.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.GONE);
            tvMencari.setVisibility(View.GONE);
            displayLocation();
        }
    };

    private BroadcastReceiver mCancelAngkut = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            lvPenjemputan.setVisibility(View.GONE);
            Utils.driverId = "";
            Utils.isDrivenFound = false;
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setText("Cari Driver");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mArrivedRceiver, new IntentFilter(Utils.ARRIVED_BRADCAST));

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mAngkutReceiver, new IntentFilter(Utils.ANGKUT_BRADCAST));

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mCancelAngkut, new IntentFilter(Utils.CANCEL_ANGKUT_BRADCAST));


        sharedPreferences = getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
        apiRequest = GoogleMapApi.getClient(Utils.mapsApiUrl).create(ApiRequest.class);

        init();

        tb_destinasi_passenger = FirebaseDatabase.getInstance().getReference(Utils.passenger_destination_tbl).child(noHpUser);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        buildLocationCallback();
        buildLocationRequest();

        tb_passenger = FirebaseDatabase.getInstance().getReference(Utils.passenger_tbl);
        geoFire = new GeoFire(tb_passenger);
        displayLocation();

        setUpLocation();

        updateFirebaseToken();

        if (!Places.isInitialized()) {
            Places.initialize(this, getString(R.string.google_api_key));
        }

        placeTo = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.place_to);
        placeTo.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG));
        placeTo.setTypeFilter(TypeFilter.ADDRESS);
        placeTo.setCountry("IDN");
        EditText etPlace = (EditText) placeTo.getView().findViewById(R.id.places_autocomplete_search_input);
        etPlace.setTextSize(12.0f);
        etPlace.setText("Tujuan");

        placeFrom = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.place_from);
        placeFrom.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG));
        placeFrom.setTypeFilter(TypeFilter.ADDRESS);
        placeFrom.setCountry("IDN");
        etPlaceFrom = (EditText) placeFrom.getView().findViewById(R.id.places_autocomplete_search_input);
        etPlaceFrom.setTextSize(12.0f);

//        placeFrom.getView().findViewById(R.id.place_autocomplete_search_button).setVisibility(View.GONE);
//        placeTo.getView().findViewById(R.id.place_autocomplete_search_button).setVisibility(View.GONE);

        placeTo.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                destination = place.getAddress();
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(TrackingActivity.this, Locale.getDefault());
                try {
                    LatLng latLng = place.getLatLng();
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    city = addresses.get(0).getLocality();
                    placeTo.setHint(destination);
                    etPlace.setText(destination);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });

        placeFrom.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                from = place.getAddress();
                etPlaceFrom.setText(from);
                placeFrom.setHint(from);
                mMap.clear();
                mCurrentMarker = mMap.addMarker(new MarkerOptions().position(place.getLatLng())
                        .title("Pickup Here"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15.0f));
            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnNext.getText().equals("NEXT")) {
                    FragmentManager fm = getSupportFragmentManager();
                    InputJumlahFragment inputJumlahFragment = new InputJumlahFragment(destination, city, (TrackingActivity.this::onFinishedPengisian));
                    inputJumlahFragment.show(fm, "");
                }else if (btnNext.getText().equals("Cari Driver")){
                    rvSearch.setVisibility(View.VISIBLE);
                    if (!Utils.isDrivenFound){
                        cariDriver(noHpUser);
                    }
                }else if(btnNext.getText().equals("Cancel")){
                    rvSearch.setVisibility(View.GONE);
                    btnNext.setText("Cari Driver");
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvPenjemputan.getVisibility() == View.VISIBLE) {
                    lvPenjemputan.setVisibility(View.GONE);
                }
                Utils.driverId = "";
                Utils.isDrivenFound = false;
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setText("Cari Driver");
            }
        });

        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataPerjalanan();
            }
        });



    }

    private void updateFirebaseToken() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference tokens = db.getReference(Utils.token_tbl);

        Token token = new Token(FirebaseInstanceId.getInstance().getToken());
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            tokens.child(noHpUser)
                    .setValue(token);
        }
    }

    private void cariDriver(String noHpUser) {
        DatabaseReference dbRequest = FirebaseDatabase.getInstance().getReference(Utils.pickup_request_tbl);
        GeoFire geoFireRequest = new GeoFire(dbRequest);
        geoFireRequest.setLocation(noHpUser, new GeoLocation(Utils.mLastLocation.getLatitude(), Utils.mLastLocation.getLongitude()), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {

            }
        });

        if (mCurrentMarker.isVisible()){
            mCurrentMarker.remove();
        }

        mCurrentMarker = mMap.addMarker(new MarkerOptions()
                    .title("Jemput disini")
                    .snippet("")
                    .position(new LatLng(Utils.mLastLocation.getLatitude(), Utils.mLastLocation.getLongitude()))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mCurrentMarker.showInfoWindow();
        btnNext.setText("Mendapatkan driver ...");

        findDriver();

    }

    private void findDriver() {
        List<String> kodeDriver = loadKodeDriver();
        DatabaseReference driverLocation = FirebaseDatabase.getInstance().getReference(Utils.driver_tbl);
        GeoFire gf = new GeoFire(driverLocation);
        GeoQuery geoQuery = gf.queryAtLocation(new GeoLocation(Utils.mLastLocation.getLatitude(), Utils.mLastLocation.getLongitude()), radius);
        geoQuery.removeAllListeners();
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                if (!Utils.isDrivenFound){
                    if (kodeDriver.contains(key)) {
                        DatabaseReference dbDriverInfo = FirebaseDatabase.getInstance().getReference(Utils.user_driver_tbl);
                        dbDriverInfo.child(key)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()){
                                            Driver driver = dataSnapshot.getValue(Driver.class);
                                            if(driver.getStatus().equals("0")) {
                                                rvSearch.setVisibility(View.GONE);
                                                Utils.isDrivenFound = true;
                                                Utils.driverId = key;
                                                btnNext.setText("Cancel");
                                                FragmentManager fm = getSupportFragmentManager();
                                                DetailDriverDialogFragment detailDriverDialogFragment = new DetailDriverDialogFragment(driver, key, idDestinasi, noHpUser, Utils.mLastLocation, TrackingActivity.this::onFinishedBooking);
                                                detailDriverDialogFragment.show(fm, "");
                                            }else {
                                                rvSearch.setVisibility(View.GONE);
                                                btnNext.setText("Cari Driver");
                                                Toast.makeText(TrackingActivity.this, "Tidak ada", Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            rvSearch.setVisibility(View.GONE);
                                            btnNext.setText("Cari Driver");
                                            Toast.makeText(TrackingActivity.this, "Tidak ada", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                    }
                }
            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {
                if (!Utils.isDrivenFound && radius < LIMIT){
                    radius++;
                    findDriver();
                }else {
                    if (!Utils.isDrivenFound){
                        Toast.makeText(TrackingActivity.this, "Tidak ada driver di dekat anda", Toast.LENGTH_SHORT).show();
                        geoQuery.removeAllListeners();
                    }
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
    }

    private void init(){
        lvPenjemputan = findViewById(R.id.lv_penjemputan);
        lvPayment = findViewById(R.id.lv_payment);
        lvInputTujuan = findViewById(R.id.lv_input_tujuan);
        lvActionCall = findViewById(R.id.lv_action_call);
        lvActionShare = findViewById(R.id.lv_action_share);
        rvSearch = findViewById(R.id.rl_search);
        btnCancel = findViewById(R.id.btn_cancel);
        tvTujuan = findViewById(R.id.tv_tujuan);
        btnNext = findViewById(R.id.btn_next);
        btnBookNow = findViewById(R.id.btn_book_now);
        lvDriver = findViewById(R.id.lv_driver);
        tvDriver = findViewById(R.id.tv_driver);
        tvMerkMobil = findViewById(R.id.tv_merk_mobil);
        tvPlat = findViewById(R.id.tv_plat);
        tvMencari = findViewById(R.id.tv_mencari);
        btnTelpon = findViewById(R.id.btn_telepon);
        progressBar = findViewById(R.id.progres);
        fbBack = findViewById(R.id.fb_back);
        tvAsal = findViewById(R.id.tv_asal);
        tvDistance = findViewById(R.id.tv_distance);
        tvWaktu = findViewById(R.id.tv_waktu);
        tvOnkos = findViewById(R.id.tv_ongkos);
        tvNamaDriver = findViewById(R.id.tv_nama_driver);
        tvPlatDriver = findViewById(R.id.tv_plat_driver);

    }

    private void setUpLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, MY_PERMISSION_REQUEST_CODE);
        }else {
            buildLocationCallback();
            buildLocationRequest();
            tb_passenger = FirebaseDatabase.getInstance().getReference(Utils.passenger_tbl);
        }
    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Utils.mLastLocation = location;
                        if (Utils.mLastLocation != null){
                            final double latitude = Utils.mLastLocation.getLatitude();
                            final double longitude = Utils.mLastLocation.getLongitude();

                            Geocoder geocoder;
                            List<Address> addresses;
                            geocoder = new Geocoder(TrackingActivity.this, Locale.getDefault());
                            try {
                                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                placeFrom.setHint(addresses.get(0).getAddressLine(0));
                                from = addresses.get(0).getAddressLine(0);
                                etPlaceFrom.setText(from);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            LatLng center = new LatLng(Utils.mLastLocation.getLatitude(), Utils.mLastLocation.getLongitude());
                            LatLng northSide = SphericalUtil.computeOffset(center, 500, 0);
                            LatLng southSide = SphericalUtil.computeOffset(center, 500, 180);

                            LatLngBounds bounds = LatLngBounds.builder()
                                    .include(northSide)
                                    .include(southSide)
                                    .build();

                            placeTo.setLocationBias(RectangularBounds.newInstance(bounds));
                            placeFrom.setLocationBias(RectangularBounds.newInstance(bounds));

                            geoFire.setLocation(noHpUser, new GeoLocation(latitude, longitude), new GeoFire.CompletionListener() {
                                @Override
                                public void onComplete(String key, DatabaseError error) {
                                    if (mCurrentMarker != null){
                                        mCurrentMarker.remove();
                                    }

                                    mMap.clear();

                                    mCurrentMarker = mMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(latitude, longitude))
                                            .title("Your Location"));

                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15.0f));

                                }
                            });


//            Presense System
//                            driversAvailable = FirebaseDatabase.getInstance().getReference(Common.driver_tbl).child(isUberX?"UberX":"Uber Black");
//                            driversAvailable.addValueEventListener(HomeFragment.this)
                        }else {
                            Log.d("ERROR", "displayLocation: Cannot get your location");
                        }
                    }
                });
    }

    private void loadAllAvailableDriver(LatLng latLng) {
        mMap.clear();
        rvSearch.setVisibility(View.VISIBLE);
        mCurrentMarker = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("You"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));

        List<String> kodeDriver = loadKodeDriver();
        DatabaseReference driverLocation = FirebaseDatabase.getInstance().getReference(Utils.driver_tbl);
        GeoFire geoFireDriver = new GeoFire(driverLocation);
        GeoQuery geoQuery = geoFireDriver.queryAtLocation(new GeoLocation(latLng.latitude, latLng.longitude), distance);
        geoQuery.removeAllListeners();
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                if (kodeDriver.contains(key)){
                    FirebaseDatabase.getInstance().getReference(Utils.user_driver_tbl)
                            .child(key)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        Driver driver = dataSnapshot.getValue(Driver.class);
                                        if (driver.getStatus().equals("0")) {
                                            mMap.addMarker(new MarkerOptions()
                                                    .position(new LatLng(location.latitude, location.longitude))
                                                    .flat(true)
                                                    .title(driver.getNama())
                                                    .snippet(dataSnapshot.getKey())
                                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
                                            if (rvSearch.getVisibility() == View.VISIBLE) {
                                                rvSearch.setVisibility(View.GONE);
                                            }

                                            if (btnNext.getVisibility() == View.GONE) {
                                                btnNext.setVisibility(View.VISIBLE);
                                                btnNext.setText("Cari Driver");
                                            }
                                        }else {
                                            rvSearch.setVisibility(View.GONE);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                }
            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {
                if (distance <= LIMIT){
                    distance++;
                    loadAllAvailableDriver(latLng);
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
    }

    private List<String> loadKodeDriver() {
        List<String> kodeDriverList = new ArrayList<>();
        DatabaseReference tb_driver_destination = FirebaseDatabase.getInstance().getReference(Utils.destination_tbl);
        tb_driver_destination.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        String parent = snapshot.getKey();
                        tb_driver_destination.child(parent).orderByChild("city").equalTo(city).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    String kodeDriver = dataSnapshot.getKey();
                                    kodeDriverList.add(kodeDriver);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(TrackingActivity.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }else {
                    Toast.makeText(TrackingActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return kodeDriverList;
    }

    private void buildLocationRequest() {
        mLocationReqeust = new LocationRequest();
        mLocationReqeust.setInterval(UPDATE_INTERVAL);
        mLocationReqeust.setFastestInterval(FASTEST_INTERVAL);
        mLocationReqeust.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationReqeust.setSmallestDisplacement(DISPLACEMENT);
    }

    private void buildLocationCallback() {
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()){
                    Utils.mLastLocation = location;
                }
                displayLocation();
            }
        };
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(false);
        mMap.setIndoorEnabled(false);
        mMap.setBuildingsEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        buildLocationCallback();
        buildLocationRequest();
        fusedLocationProviderClient.requestLocationUpdates(mLocationReqeust, locationCallback, Looper.myLooper());
    }

    @Override
    public void onFinishedPengisian(String jumlahDewasa, String jumlahAnak, String barang) {
        this.jumlahDewasa = jumlahDewasa;
        this.jumlahAnak = jumlahAnak;
        this.jumlahBarang = barang;
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Proses ...");
        progressDialog.show();
        String origin = from.replace(" ", "+");
        String dest = destination.replace(" ", "+");
        String url = "https://maps.googleapis.com/maps/api/directions/json?"+
                "mode=driving&"+
                "transit_routing_preference=less_driving&"+
                "origin="+origin+"&"+
                "destination="+dest+"&"+
                "key="+getResources().getString(R.string.browser_api_key);
        Log.e("Error Sontoloyo : ", url);
        Log.d("Error Sontoloyo : ", url);
        apiRequest.getPath(url)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body());
                                JSONArray routes = jsonObject.getJSONArray("routes");
                                JSONObject object = routes.getJSONObject(0);
                                JSONArray legs = object.getJSONArray("legs");
                                JSONObject objectLegs = legs.getJSONObject(0);

                                JSONObject distance = objectLegs.getJSONObject("distance");
                                String distanceText = distance.getString("text");

                                JSONObject time = objectLegs.getJSONObject("duration");
                                String timeText = time.getString("text");

                                lvInputTujuan.setVisibility(View.GONE);
                                btnNext.setVisibility(View.GONE);
                                lvPayment.setVisibility(View.VISIBLE);
                                tvAsal.setText(from);
                                tvTujuan.setText(destination);
                                tvDistance.setText(distanceText);
                                tvWaktu.setText(timeText);
                                tracking = true;
                                setUpLocation();
                                progressDialog.dismiss();

                            } catch (JSONException e) {
                                Toast.makeText(TrackingActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(TrackingActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }


    private void saveDataPerjalanan() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Proses ...");
        progressDialog.show();
        Map<String, Object> destinationPassanger = new HashMap<>();
        Map<String, Object> jumlahOrang = new HashMap<>();
        jumlahOrang.put("jumlahDewasa", jumlahDewasa);
        jumlahOrang.put("jumlahAnak", jumlahAnak);
        idDestinasi = tb_destinasi_passenger.push().getKey();
        destinationPassanger.put("idDestinasi", idDestinasi);
        destinationPassanger.put("address", destination);
        destinationPassanger.put("city", city);
        destinationPassanger.put("fromLocation", from);
        destinationPassanger.put("biaya", tvOnkos.getText().toString());
        destinationPassanger.put("jumlahOrang", jumlahOrang);
        destinationPassanger.put("jumlahBarang", jumlahBarang);
        tb_destinasi_passenger.child(idDestinasi).setValue(destinationPassanger)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            lvPayment.setVisibility(View.GONE);
                            if (mCurrentMarker != null){
                                mCurrentMarker.remove();
                            }

                            loadAllAvailableDriver(new LatLng(Utils.mLastLocation.getLatitude(), Utils.mLastLocation.getLongitude()));


                        }else {
                            Toast.makeText(TrackingActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TrackingActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFinishedBooking(String driverToken, String kodeDriver) {
        DatabaseReference db_driver = FirebaseDatabase.getInstance().getReference(Utils.user_driver_tbl);
        db_driver.child(kodeDriver).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Driver driverData = dataSnapshot.getValue(Driver.class);
                    tvNamaDriver.setText(driverData.getNama());
                    tvPlatDriver.setText(driverData.getPlat());
                    lvPenjemputan.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.GONE);
                    btnCancel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mArrivedRceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mAngkutReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mCancelAngkut);
    }
}