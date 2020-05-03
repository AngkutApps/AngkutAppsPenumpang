package id.co.myproject.angkutapps_penumpang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import id.co.myproject.angkutapps_penumpang.view.AktivitasFragment;
import id.co.myproject.angkutapps_penumpang.view.HomeFragment;
import id.co.myproject.angkutapps_penumpang.view.PaymentFragment;
import id.co.myproject.angkutapps_penumpang.view.ProfilFragment;
import id.co.myproject.angkutapps_penumpang.view.PromoFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    public static BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
