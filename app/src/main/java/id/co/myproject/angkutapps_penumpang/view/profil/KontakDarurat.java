package id.co.myproject.angkutapps_penumpang.view.profil;

import android.os.Bundle;
import android.util.Log;
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
import id.co.myproject.angkutapps_penumpang.model.crud_table.tb_kontak_darurat_user;
import id.co.myproject.angkutapps_penumpang.view.profil.dialog_fragment.Df_TambahKontakDarurat;

public class KontakDarurat extends AppCompatActivity {

    ImageButton appbar_button_back;
    CardView cvTambahKontakDarurat;
    RecyclerView rvKontakDarurat;
    ArrayList<LoadKontakDarurat> arrayList;
    rvKontakDarurat kontakDaruratAdapter;
    tb_kontak_darurat_user crudKontakDarurat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak_darurat);

        appbar_button_back = findViewById(R.id.appbar_button_back);
        cvTambahKontakDarurat = findViewById(R.id.cvTambahKontakDarurat);
        rvKontakDarurat = findViewById(R.id.rvKontakDarurat);
        AndroidNetworking.initialize(KontakDarurat.this);


        crudKontakDarurat = new tb_kontak_darurat_user(KontakDarurat.this);
        arrayList = crudKontakDarurat.readKontakDarurat();
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
                    if (arrayList.size()<5){
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

    public void readKontakDarurat(){
        kontakDaruratAdapter = new rvKontakDarurat(KontakDarurat.this, arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KontakDarurat.this);
        rvKontakDarurat.setLayoutManager(layoutManager);
        rvKontakDarurat.setHasFixedSize(true);
        rvKontakDarurat.setAdapter(kontakDaruratAdapter);
    }

}
