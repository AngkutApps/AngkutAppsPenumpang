package id.co.myproject.angkutapps_penumpang.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.view.riwayat.AktivitasFragment;
import id.co.myproject.angkutapps_penumpang.view.home.HomeFragment;
import id.co.myproject.angkutapps_penumpang.view.payment.PaymentFragment;
import id.co.myproject.angkutapps_penumpang.view.profil.ProfilFragment;
import id.co.myproject.angkutapps_penumpang.view.promo.PromoFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    FrameLayout frameLayout;
    public static BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dicoba Merge

        frameLayout = findViewById(R.id.frame_home);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_nav){
                    setFragment(new HomeFragment());
                }else if (item.getItemId() == R.id.history_nav){
                    setFragment(new AktivitasFragment());
                }else if (item.getItemId() == R.id.barcode_nav){
                    setFragment(new PaymentFragment());
                }else if (item.getItemId() == R.id.payment_nav){
                    setFragment(new PromoFragment());
                }else if (item.getItemId() == R.id.akun_nav){
                    setFragment(new ProfilFragment());
                }

                return true;
            }
        });

        setFragment(new HomeFragment());

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(), fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (back_pressed+TIME_DELAY > System.currentTimeMillis()){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Tekan back lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
//        super.onBackPressed();
    }
}
