package id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_perjalanan_user;
import id.co.myproject.angkutapps_penumpang.view.tracking.dialog_fragment.Df_chat;

public class Df_DetailRiwayatFragment extends DialogFragment {

    LinearLayout btn_chat;

    ImageView imgClose;
    View imgProfil;

    String tgl_berangkat, tgl_sampai, jam_berangkat, jam_sampai;

    TextView tvDari, tvTujuan, tvHari, tvPDewasa, tvPAnak, tvTglBerangkat, tvTglSampai, tvJamBerangkat,
            tvJamSampai, tvNamaDriver, tvNomorPlat, tvJenisKendaraa, tvWarnaKendaraan, tvHargaPerjalanan;

    loadView_rw_perjalanan_user loadView;

    public Df_DetailRiwayatFragment(loadView_rw_perjalanan_user loadView_rw_perjalanan_user, String tgl_berangkat, String tgl_sampai, String jam_berangkat, String jam_sampai) {
        this.loadView = loadView_rw_perjalanan_user;
        this.tgl_berangkat = tgl_berangkat;
        this.tgl_sampai = tgl_sampai;
        this.jam_berangkat = jam_berangkat;
        this.jam_sampai = jam_sampai;
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
        btn_chat = view.findViewById(R.id.btn_chat);

        imgProfil = view.findViewById(R.id.oval);
        imgClose = view.findViewById(R.id.imgClose);

        tvDari.setText(loadView.getDari());
        tvTujuan.setText(loadView.getTujuan());
        tvHari.setText(loadView.getHari_keberangkatan());
        tvPDewasa.setText(""+loadView.getPenumpang_dewasa());
        tvPAnak.setText(""+loadView.getPenumpang_anak());
        tvTglBerangkat.setText(tgl_berangkat);
        tvTglSampai.setText(tgl_sampai);
        tvJamBerangkat.setText(jam_berangkat);
        tvJamSampai.setText(jam_sampai);
        tvNamaDriver.setText(loadView.getNama_driver());
        tvNomorPlat.setText(loadView.getPlat_mobil());
        tvJenisKendaraa.setText(loadView.getMerk_mobil());
        tvWarnaKendaraan.setText(loadView.getWarna_kendaraan());
        tvHargaPerjalanan.setText(""+loadView.getBiaya());

        if (loadView.getTransportasi().equals("bus"))
            imgProfil.setBackgroundResource(R.drawable.shape_oval_bus);
        else if (loadView.getTransportasi().equals("pete"))
            imgProfil.setBackgroundResource(R.drawable.shape_oval_pete);
        else if (loadView.getTransportasi().equals("travel"))
            imgProfil.setBackgroundResource(R.drawable.shape_oval_travel);
        imgClose.setOnClickListener(clickListener);
        btn_chat.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgClose :
                    Df_DetailRiwayatFragment.super.onStop();
                    Df_DetailRiwayatFragment.super.onDestroyView();
                    break;
                case R.id.btn_chat:
                    setFragment(new Df_chat(loadView.getNo_hp(), loadView.getNama_driver(), loadView.getPlat_mobil()));
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

    private void setFragment(DialogFragment fragment){
        FragmentManager fragmentManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if (prev !=null){
            fragmentTransaction.remove(prev);
        }
        fragment.show(fragmentTransaction, "dialog");
    }
}
