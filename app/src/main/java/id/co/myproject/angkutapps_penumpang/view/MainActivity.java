package id.co.myproject.angkutapps_penumpang.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import id.co.myproject.angkutapps_penumpang.BuildConfig;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.User;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.RetrofitRequest;
import id.co.myproject.angkutapps_penumpang.view.profil.ProfilUser;
import id.co.myproject.angkutapps_penumpang.view.riwayat.AktivitasFragment;
import id.co.myproject.angkutapps_penumpang.view.home.HomeFragment;
import id.co.myproject.angkutapps_penumpang.view.profil.ProfilFragment;
import id.co.myproject.angkutapps_penumpang.view.promo.PromoFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    SharedPreferences sharedPreferences;
    String noHpUser;
    ApiRequest apiRequest;

    FrameLayout frameLayout;
    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dicoba Merge

        sharedPreferences = getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

        frameLayout = findViewById(R.id.frame_home);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_nav) {
                setFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.history_nav) {
                setFragment(new AktivitasFragment());
            } else if (item.getItemId() == R.id.payment_nav) {
                setFragment(new PromoFragment());
            } else if (item.getItemId() == R.id.akun_nav) {
                setFragment(new ProfilFragment());
            }

            return true;
        });

        setFragment(new HomeFragment());

    }

    private void refreshDataProfil() {
        Call<User> userCall = apiRequest.penumpangByIdRequest(noHpUser);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    DatabaseReference dbUser = FirebaseDatabase.getInstance().getReference(Utils.user_passenger_tbl);
                    dbUser.child(noHpUser).setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Barhasil", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(MainActivity.this, "firbase gagal", Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(), fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Tekan back lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
//        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshDataProfil();
    }
}
