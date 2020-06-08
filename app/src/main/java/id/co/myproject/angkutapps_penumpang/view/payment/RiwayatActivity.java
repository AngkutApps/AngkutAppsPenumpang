package id.co.myproject.angkutapps_penumpang.view.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rvRiwayatDana;
import id.co.myproject.angkutapps_penumpang.model.data_object.riwayatDana;

public class RiwayatActivity extends AppCompatActivity {

    ImageButton appbar_button_back;
    RecyclerView rvRiwayatDana;
    rvRiwayatDana rvAdapter;
    ArrayList<riwayatDana> riwayatDanaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        appbar_button_back = findViewById(R.id.appbar_button_back);
        rvRiwayatDana = findViewById(R.id.rvRiwayatDana);
        loadRiwayat();

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

    private void loadRiwayat(){
        riwayatDanaList = new ArrayList<>();
        for (int i=0 ; i< 10;i++){
            riwayatDanaList.add(new riwayatDana("Indomaret Group","20 Agustus 2020 - 04.15 WIB","Rp. 50.000","Transfer"));
        }

        rvAdapter = new rvRiwayatDana(RiwayatActivity.this, riwayatDanaList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RiwayatActivity.this);
        rvRiwayatDana.setLayoutManager(layoutManager);
        rvRiwayatDana.setHasFixedSize(true);
        rvRiwayatDana.setAdapter(rvAdapter);
    }
}
