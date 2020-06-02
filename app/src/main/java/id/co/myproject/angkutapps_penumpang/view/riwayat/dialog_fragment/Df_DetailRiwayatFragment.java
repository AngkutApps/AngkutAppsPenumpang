package id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.myproject.angkutapps_penumpang.R;

public class Df_DetailRiwayatFragment extends DialogFragment {

    ImageView imgClose;
    View imgProfil;

    String transportasi, dari, tujuan, hari, p_dewasa, p_anak, tgl_berangkat, tgl_sampai, jam_berangkat, jam_sampai, nama_driver,
        nomor_plat, jenis_kendaraan, warna_kendaraan, harga_perjalanan;

    TextView tvDari, tvTujuan, tvHari, tvPDewasa, tvPAnak, tvTglBerangkat, tvTglSampai, tvJamBerangkat,
            tvJamSampai, tvNamaDriver, tvNomorPlat, tvJenisKendaraa, tvWarnaKendaraan, tvHargaPerjalanan;

    public Df_DetailRiwayatFragment(String transportasi, String dari, String tujuan, String hari, String p_dewasa, String p_anak, String tgl_berangkat, String tgl_sampai, String jam_berangkat, String jam_sampai, String nama_driver, String nomor_plat, String jenis_kendaraan, String warna_kendaraan, String harga_perjalanan) {
        this.transportasi = transportasi;
        this.dari = dari;
        this.tujuan = tujuan;
        this.hari = hari;
        this.p_dewasa = p_dewasa;
        this.p_anak = p_anak;
        this.tgl_berangkat = tgl_berangkat;
        this.tgl_sampai = tgl_sampai;
        this.jam_berangkat = jam_berangkat;
        this.jam_sampai = jam_sampai;
        this.nama_driver = nama_driver;
        this.nomor_plat = nomor_plat;
        this.jenis_kendaraan = jenis_kendaraan;
        this.warna_kendaraan = warna_kendaraan;
        this.harga_perjalanan = harga_perjalanan;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_detail_riwayat, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDari = view.findViewById(R.id.tvDari);
        tvTujuan = view.findViewById(R.id.tvTujuan);
        tvHari = view.findViewById(R.id.tvHari);
        tvPDewasa = view.findViewById(R.id.tvPDewasa);
        tvPAnak = view.findViewById(R.id.tvPAnak);
        tvTglBerangkat = view.findViewById(R.id.tvTglBerangkat);
        tvTglSampai = view.findViewById(R.id.tvTglSampai);
        tvJamBerangkat = view.findViewById(R.id.tvJamBerangkat);
        tvJamSampai = view.findViewById(R.id.tvJamSampai);
        tvNamaDriver = view.findViewById(R.id.tvNamaDriver);
        tvNomorPlat = view.findViewById(R.id.tvNomorPlat);
        tvJenisKendaraa = view.findViewById(R.id.tvJenisKendaraan);
        tvWarnaKendaraan = view.findViewById(R.id.tvWarnaKendaraan);
        tvHargaPerjalanan = view.findViewById(R.id.tvHargaPerjalanan);

        imgProfil = view.findViewById(R.id.oval);
        imgClose = view.findViewById(R.id.imgClose);

        tvDari.setText(dari);
        tvTujuan.setText(tujuan);
        tvHari.setText(hari);
        tvPDewasa.setText(p_dewasa);
        tvPAnak.setText(p_anak);
        tvTglBerangkat.setText(tgl_berangkat);
        tvTglSampai.setText(tgl_sampai);
        tvJamBerangkat.setText(jam_berangkat);
        tvJamSampai.setText(jam_sampai);
        tvNamaDriver.setText(nama_driver);
        tvNomorPlat.setText(nomor_plat);
        tvJenisKendaraa.setText(jenis_kendaraan);
        tvWarnaKendaraan.setText(warna_kendaraan);
        tvHargaPerjalanan.setText(harga_perjalanan);

        if (transportasi.equals("bus"))
            imgProfil.setBackgroundResource(R.drawable.shape_oval_bus);
        else if (transportasi.equals("pete"))
            imgProfil.setBackgroundResource(R.drawable.shape_oval_pete);
        else if (transportasi.equals("travel"))
            imgProfil.setBackgroundResource(R.drawable.shape_oval_travel);
        imgClose.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgClose :
                    Df_DetailRiwayatFragment.super.onStop();
                    Df_DetailRiwayatFragment.super.onDestroyView();
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
