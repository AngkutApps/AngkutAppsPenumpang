package id.co.myproject.angkutapps_penumpang.view.profil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.co.myproject.angkutapps_penumpang.R;

public class PemilihanBahasa extends AppCompatActivity {

    LinearLayout bhsInggris, bhsIndonesia;
//    Toolbar toolbar;
    ImageButton appbar_button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_bahasa);

        bhsIndonesia = findViewById(R.id.bhsInggris);
        bhsInggris = findViewById(R.id.bhsIndonesia);
//        toolbar = findViewById(R.id.toolbarBahasa);
//        setSupportActionBar(toolbar);

        appbar_button_back = findViewById(R.id.appbar_button_back);

        appbar_button_back.setOnClickListener(clickListener);

        bhsInggris.setOnClickListener(clickListener);
        bhsIndonesia.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bhsIndonesia :
                    Toast.makeText(PemilihanBahasa.this, "Bahasa Indonesia", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bhsInggris :
                    Toast.makeText(PemilihanBahasa.this, "Bahasa Inggris", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.appbar_button_back :
                    finish();
                    break;
            }
        }
    };
}
