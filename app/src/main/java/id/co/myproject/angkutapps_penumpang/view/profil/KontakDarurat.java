package id.co.myproject.angkutapps_penumpang.view.profil;

import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadKontakDarurat;
import id.co.myproject.angkutapps_penumpang.model.crud_table.tb_kontak_darurat_user;
import id.co.myproject.angkutapps_penumpang.request.request_tb_kontak_darurat;
import id.co.myproject.angkutapps_penumpang.view.profil.dialog_fragment.Df_TambahKontakDarurat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KontakDarurat extends AppCompatActivity {

    ImageButton appbar_button_back;
    CardView cvTambahKontakDarurat;
    RecyclerView rvKontakDarurat;
    List<LoadKontakDarurat> arrayList = new ArrayList<>();
    rv_profil_kontak_darurat kontakDaruratAdapter;
    tb_kontak_darurat_user crudKontakDarurat;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak_darurat);

        appbar_button_back = findViewById(R.id.appbar_button_back);
        cvTambahKontakDarurat = findViewById(R.id.cvTambahKontakDarurat);
        rvKontakDarurat = findViewById(R.id.rvKontakDarurat);
        AndroidNetworking.initialize(KontakDarurat.this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu....");

        crudKontakDarurat = new tb_kontak_darurat_user(KontakDarurat.this);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KontakDarurat.this);
        rvKontakDarurat.setLayoutManager(new LinearLayoutManager(KontakDarurat.this));
        rvKontakDarurat.setHasFixedSize(true);
        progressDialog.show();
        readData();

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
                    if (arrayList.size() < 5) {
                        setFragment(new Df_TambahKontakDarurat());
//                        readData();
                    } else {
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

    public void readData() {
        Call<List<LoadKontakDarurat>> call = request_tb_kontak_darurat.getInstance().getApi().getKontakDarurat("82397147928");
        call.enqueue(new Callback<List<LoadKontakDarurat>>() {
            @Override
            public void onResponse(Call<List<LoadKontakDarurat>> call, Response<List<LoadKontakDarurat>> response) {
                arrayList = response.body();

                kontakDaruratAdapter = new rv_profil_kontak_darurat(KontakDarurat.this, arrayList);
                rvKontakDarurat.setAdapter(kontakDaruratAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<LoadKontakDarurat>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KontakDarurat.this, "Data Gagal Ter-input", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
