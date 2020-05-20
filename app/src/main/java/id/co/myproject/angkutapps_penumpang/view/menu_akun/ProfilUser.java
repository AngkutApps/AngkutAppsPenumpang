package id.co.myproject.angkutapps_penumpang.view.menu_akun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import id.co.myproject.angkutapps_penumpang.R;

public class ProfilUser extends AppCompatActivity {

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_user);

        btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_back :
                    finish();
                    break;
            }
        }
    };
}
