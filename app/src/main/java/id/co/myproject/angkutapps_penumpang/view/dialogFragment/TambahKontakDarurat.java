package id.co.myproject.angkutapps_penumpang.view.dialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidnetworking.AndroidNetworking;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.crud_tb_kontak_darurat_user;

public class TambahKontakDarurat extends DialogFragment {

    EditText namaKontak, hubunganKontak, nomorKontak;
    Button btnSaveKontak;
    crud_tb_kontak_darurat_user crudKontakDarurat;

    public TambahKontakDarurat() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_tambah_kontak, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        crudKontakDarurat = new crud_tb_kontak_darurat_user();

        namaKontak = view.findViewById(R.id.etNamaKontakDarurat);
        hubunganKontak = view.findViewById(R.id.etHubunganKontak);
        nomorKontak = view.findViewById(R.id.et_nomor_hp);
        btnSaveKontak = view.findViewById(R.id.btnSaveKontak);
        AndroidNetworking.initialize(getContext());

        btnSaveKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesan = crudKontakDarurat.tambahKontakDarurat(namaKontak.getText().toString().trim(), hubunganKontak.getText().toString().trim(), nomorKontak.getText().toString().trim());
                Toast.makeText(getContext(), ""+pesan, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
