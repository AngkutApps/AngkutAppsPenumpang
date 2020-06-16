package id.co.myproject.angkutapps_penumpang.view.tracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.KeberangkatanListener;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.Driver;
import id.co.myproject.angkutapps_penumpang.model.data_object.Value;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KeberangkatanActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, KeberangkatanListener {


    private GoogleMap mMap;
    private static final int MY_PERMISSION_REQUEST_CODE = 7192;
    private static final int PLAY_SERVICE_REQUEST_CODE = 300193;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    boolean tracking = false, search = false;

    private static int UPDATE_INTERVAL = 5000;
    private static int FATEST_INTERVAL = 3000;
    private static int DISPLACEMENT = 10;

    DatabaseReference drivers;
    GeoFire geoFire;
    Marker mUserMarker;
    SharedPreferences sharedPreferences;
    ApiRequest apiRequest;
    String idPenumpang;
    boolean isDriverFound = false;
    FloatingActionButton fbBack;
    String driverId = "";
    String tujuan = "";
    int radius = 1;
    int distance = 1;
    public static final int LIMIT = 3;

    LinearLayout lvPenjemputan, lvDriver, lvInputTujuan, lvPayment;

    RelativeLayout rvSearch;
    EditText etTujuan;
    TextView tvDriver, tvMerkMobil, tvPlat, tvMencari, tvTujuan;
    Button btnTelpon, btnNext, btnBookNow, btnCancel;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keberangkatan);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        sharedPreferences = getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        idPenumpang = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
        drivers = FirebaseDatabase.getInstance().getReference("Jemput");
        geoFire = new GeoFire(drivers);

        lvPenjemputan = findViewById(R.id.lv_penjemputan);
        lvPayment = findViewById(R.id.lv_payment);
        lvInputTujuan = findViewById(R.id.lv_input_tujuan);
        rvSearch = findViewById(R.id.rl_search);
        btnCancel = findViewById(R.id.btn_cancel);
        tvTujuan = findViewById(R.id.tv_tujuan);
        btnNext = findViewById(R.id.btn_next);
        btnBookNow = findViewById(R.id.btn_book_now);
        etTujuan = findViewById(R.id.et_tujuan);
        lvDriver = findViewById(R.id.lv_driver);
        tvDriver = findViewById(R.id.tv_driver);
        tvMerkMobil = findViewById(R.id.tv_merk_mobil);
        tvPlat = findViewById(R.id.tv_plat);
        tvMencari = findViewById(R.id.tv_mencari);
        btnTelpon = findViewById(R.id.btn_telepon);
        progressBar = findViewById(R.id.progres);
        fbBack = findViewById(R.id.fb_back);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                InputJumlahFragment inputJumlahFragment = new InputJumlahFragment("",etTujuan.getText().toString(), (KeberangkatanActivity.this::onFinishedPengisian));
                inputJumlahFragment.show(fm, "");
            }
        });

        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvPayment.setVisibility(View.GONE);
                rvSearch.setVisibility(View.VISIBLE);
                search = true;
                setUpLocation();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvPayment.setVisibility(View.VISIBLE);
                rvSearch.setVisibility(View.GONE);
                search = false;
                setUpLocation();
            }
        });

        fbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setUpLocation();

    }

    private void setUpLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, MY_PERMISSION_REQUEST_CODE);
        }else {
            if (checkPlayServices()){
                buildGoogleApiClient();
                createLocationRequest();
                displayLocation();
            }
        }
    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null){
            final double latitude = mLastLocation.getLatitude();
            final double longitude = mLastLocation.getLongitude();
            geoFire.setLocation(idPenumpang, new GeoLocation(-5.152917, 119.444976), new GeoFire.CompletionListener() {
                @Override
                public void onComplete(String key, DatabaseError error) {
                    if (mUserMarker != null) {
                        mUserMarker.remove();
                    }
                    if (tracking) {
                        mUserMarker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(-5.152917, 119.444976))
                                .title("Your Location"));
                    }
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15.0f));
//                    rotateMarker(mCurrent, -360, mMap);
                    if (search) {
                        findDriver();
                        loadPenjemputan();
                    }
                }
            });
        }else {
            Log.d("ERROR", "displayLocation: Cannot get your location");
        }
    }

    private void findDriver() {
        DatabaseReference drivers = FirebaseDatabase.getInstance().getReference("Jemput").child(idPenumpang);
        GeoFire gfDriver = new GeoFire(drivers);

        GeoQuery geoQuery = gfDriver.queryAtLocation(new GeoLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude()), radius);
        geoQuery.removeAllListeners();
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                if (!isDriverFound) {
                    isDriverFound = true;
                    driverId = key;
//                    btnPickupRequest.setText("CALL DRIVER");
                    Toast.makeText(KeberangkatanActivity.this, "" + key, Toast.LENGTH_SHORT).show();
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
                if (!isDriverFound) {
                    radius++;
                    findDriver();
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
    }

    private void loadPenjemputan() {
        DatabaseReference driverLocation = FirebaseDatabase.getInstance().getReference("Angkut").child(idPenumpang);
        GeoFire gf = new GeoFire(driverLocation);
        GeoQuery geoQuery = gf.queryAtLocation(new GeoLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude()), distance);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                Call<Driver> callDriver = apiRequest.driverByIdRequest(key);
                callDriver.enqueue(new Callback<Driver>() {
                    @Override
                    public void onResponse(Call<Driver> call, Response<Driver> response) {
                        if (response.isSuccessful()){
                            rvSearch.setVisibility(View.GONE);
                            lvPenjemputan.setVisibility(View.VISIBLE);
                            lvDriver.setVisibility(View.VISIBLE);
                            tvMencari.setText("Penjemputan ...");
                            Driver driver = response.body();
                            tvDriver.setText("Driver : "+driver.getNama());
                            tvMerkMobil.setText("Merk Mobil : "+driver.getMerkMobil());
                            tvPlat.setText("Plat : "+driver.getPlat());
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(location.latitude, location.longitude))
                                    .flat(true)
                                    .title(driver.getNama())
                                    .snippet(driver.getNoHp())
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
                            btnTelpon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });

                            LatLng latLngDriver = new LatLng(mUserMarker.getPosition().latitude, mUserMarker.getPosition().longitude);
                            LatLng latLngUser = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                            if (latLngDriver.equals(latLngUser)){
//                                ProgressDialog progressDialog = new ProgressDialog(KeberangkatanActivity.this);
//                                progressDialog.setMessage("Proses ...");
//                                progressDialog.show();
//                                Toast.makeText(KeberangkatanActivity.this, "Sudah sampai", Toast.LENGTH_SHORT).show();
//                                DatabaseReference driverRef = FirebaseDatabase.getInstance().getReference("Angkut").child(driver.getIdUser());
//                                driverRef.removeValue();
//                                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Angkut").child(idPenumpang);
//                                userRef.removeValue();
//                                progressDialog.dismiss();
//                                Intent intent = new Intent(KeberangkatanActivity.this, PembayaranActivity.class);
//                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Driver> call, Throwable t) {
                        Toast.makeText(KeberangkatanActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {
                if(distance <= LIMIT){
                    distance++;
                    loadPenjemputan();
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.setInfoWindowAdapter(new CustomInfoWindow(this));
//        mMap.setInfoWindowAdapter(new CustomInfoWindow(this));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation();
        startLocationUpdates();
    }
    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        displayLocation();
    }
    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS){
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICE_REQUEST_CODE);
            }else {
                Toast.makeText(this, "This device is not supported", Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onFinishedPengisian(String jumlahDewasa, String jumlahAnak, String barang) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Proses ...");
        progressDialog.show();
        Call<Value> callKeberangkatan = apiRequest.inputKeberangkatanRequest(
                idPenumpang,
                tujuan,
                jumlahDewasa,
                barang
        );
        callKeberangkatan.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                if (response.isSuccessful()){
                    Toast.makeText(KeberangkatanActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().getValue() == 1){
                        lvInputTujuan.setVisibility(View.GONE);
                        lvPayment.setVisibility(View.VISIBLE);
                        tvTujuan.setText(tujuan);
                        tracking = true;
                        setUpLocation();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KeberangkatanActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
