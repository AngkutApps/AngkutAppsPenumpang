package id.co.myproject.angkutapps_penumpang.view.profil.dialog_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.androidnetworking.AndroidNetworking;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.crud_table.tb_kontak_darurat_user;

public class Df_TambahKontakDarurat extends DialogFragment {

    EditText namaKontak, hubunganKontak, nomorKontak;
    Button btnSaveKontak;
    tb_kontak_darurat_user crudKontakDarurat;

    int kondisi = 0;

    public Df_TambahKontakDarurat() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.df_tambah_kontak, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        crudKontakDarurat = new tb_kontak_darurat_user(getContext());

        namaKontak = view.findViewById(R.id.etNamaKontakDarurat);
        hubunganKontak = view.findViewById(R.id.etHubunganKontak);
        nomorKontak = view.findViewById(R.id.et_nomor_hp);
        btnSaveKontak = view.findViewById(R.id.btnSaveKontak);
        AndroidNetworking.initialize(getContext());

        try {
            if (getArguments()!=null){
                namaKontak.setText(getArguments().getString("namaKontak"));
                hubunganKontak.setText(getArguments().getString("hubunganKontak"));
                nomorKontak.setText(getArguments().getString("nomorKontak"));
                nomorKontak.setKeyListener(null);
                kondisi = 2;
            }else {
                kondisi = 1;
            }
        }catch (Exception ex){
            Toast.makeText(getContext(), ""+ex, Toast.LENGTH_SHORT).show();
        }

        btnSaveKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kondisi==1){
                    if(namaKontak.getText().toString().trim()==null){
                        namaKontak.setError("Kosong");
                    }else if (hubunganKontak.getText().toString().trim().equals("")){
                        hubunganKontak.setError("Kosong");
                    }else if (nomorKontak.getText().toString().trim()==null){
                        nomorKontak.setError("Kosong");
                    }else{
                        crudKontakDarurat.tambahKontakDarurat(namaKontak.getText().toString().trim(), hubunganKontak.getText().toString().trim(),
                                nomorKontak.getText().toString().trim());
                    }
                }else if (kondisi==2){
                    if(namaKontak.getText().toString().trim().equals("")){
                        namaKontak.setError("Kosong");
                    }else if (hubunganKontak.getText().toString().trim().equals("")){
                        hubunganKontak.setError("Kosong");
                    }else {
                        crudKontakDarurat.updateKontakDarurat(namaKontak.getText().toString().trim(), hubunganKontak.getText().toString().trim(),
                                nomorKontak.getText().toString().trim());
                    }
                }
                Df_TambahKontakDarurat.super.onStop();
                Df_TambahKontakDarurat.super.onDestroyView();
            }
        });

    }

}
