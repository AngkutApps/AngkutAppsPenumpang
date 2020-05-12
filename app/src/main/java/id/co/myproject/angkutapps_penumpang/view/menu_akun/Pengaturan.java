package id.co.myproject.angkutapps_penumpang.view.menu_akun;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import id.co.myproject.angkutapps_penumpang.R;

public class Pengaturan extends AppCompatActivity {

    ImageButton appbar_button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        appbar_button_back = findViewById(R.id.appbar_button_back);

        appbar_button_back.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.appbar_button_back :
                    finish();
                    break;
            }
        }
    };
}
