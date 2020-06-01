package id.co.myproject.angkutapps_penumpang.view.profil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.model.LoadKontakDarurat;
import id.co.myproject.angkutapps_penumpang.model.crud_tb_kontak_darurat_user;
import id.co.myproject.angkutapps_penumpang.view.profil.dialog_fragment.Df_TambahKontakDarurat;

public class KontakDarurat extends AppCompatActivity {

    ImageButton appbar_button_back;
    CardView cvTambahKontakDarurat;
    RecyclerView rvKontakDarurat;
    ArrayList<LoadKontakDarurat> arrayList;
    rvKontakDarurat kontakDaruratAdapter;
    crud_tb_kontak_darurat_user crudKontakDarurat;
    int batasKontak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak_darurat);

        arrayList = new ArrayList<>();
        appbar_button_back = findViewById(R.id.appbar_button_back);
        cvTambahKontakDarurat = findViewById(R.id.cvTambahKontakDarurat);
        rvKontakDarurat = findViewById(R.id.rvKontakDarurat);
        AndroidNetworking.initialize(KontakDarurat.this);

        crudKontakDarurat = new crud_tb_kontak_darurat_user(KontakDarurat.this);
        readKontakDarurat();

        appbar_button_back.setOnClickListener(clickListener);
        cvTambahKontakDarurat.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.appbar_button_back:
                    finish();
                    break;
                case R.id.cvTambahKontakDarurat:
                    readKontakDarurat();
                    if (batasKontak<5){
                        setFragment(new Df_TambahKontakDarurat());
                    }else {
                        Toast.makeText(KontakDarurat.this, "Kontak Darurat Telah Mencapai Maximum", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void setFragment(DialogFragment fragment) {
        FragmentManager fragmentManager = KontakDarurat.this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        fragment.show(fragmentTransaction, "dialog");
    }

    public void readKontakDarurat() {
        AndroidNetworking.get("http://angkutapps.com/angkut_api/read_kontak_darurat_user.php")
                .addQueryParameter("no_hp", "82397147928")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //mengambil data dari JSON array pada read_all.php
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject data = response.getJSONObject(i);
//                                    //adding the product to product list
                                arrayList.add(new LoadKontakDarurat(
                                        data.getString("nama_kontak"),
                                        data.getString("hubungan_kontak"),
                                        data.getString("nomor_kontak")
                                ));
                            }
                            kontakDaruratAdapter = new rvKontakDarurat(KontakDarurat.this, arrayList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KontakDarurat.this);
                            rvKontakDarurat.setLayoutManager(layoutManager);
                            rvKontakDarurat.setHasFixedSize(true);
                            rvKontakDarurat.setAdapter(kontakDaruratAdapter);
                            batasKontak = arrayList.size();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                    }
                });
        arrayList = new ArrayList<>();
    }

}
