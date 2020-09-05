package id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.fragment.app.DialogFragment;

import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_voucher_penggunaan;

public class Df_voucher_penggunaan extends DialogFragment {

    ImageView imgClose;
    ImageView oval;
    TextView tvNamaVoucher, tvTglPemakaian, tvHariPemakaian, tvHargaHistory, riwayatRute, riwayatHari, riwayatTanggal,
    tvDeskripsiVoucher;

    List<loadView_rw_voucher_penggunaan> listPenggunaan;
    int position;

    public Df_voucher_penggunaan(List<loadView_rw_voucher_penggunaan> listPenggunaan, int position) {
        this.listPenggunaan = listPenggunaan;
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.df_rw_penggunaan_promo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNamaVoucher = view.findViewById(R.id.tvNamaVoucher);
        tvTglPemakaian = view.findViewById(R.id.tvTglPemakaian);
        tvHariPemakaian = view.findViewById(R.id.tvHariPemakaian);
        tvHargaHistory = view.findViewById(R.id.tvHargaHistory);
        oval = view.findViewById(R.id.oval);
        riwayatRute = view.findViewById(R.id.riwayatRute);
        riwayatHari = view.findViewById(R.id.riwayatHari);
        riwayatTanggal = view.findViewById(R.id.riwayatTanggal);
        tvDeskripsiVoucher = view.findViewById(R.id.tvDeskripsiVoucher);
        imgClose = view.findViewById(R.id.imgClose);

        tvNamaVoucher.setText(listPenggunaan.get(position).getNama_voucher());
        String[] tgl_penggunaan = String.valueOf(listPenggunaan.get(position).getTgl_penggunaan()).split(" ");
        String tglDigunakan = tgl_penggunaan[0];
        tvTglPemakaian.setText(tglDigunakan);
        tvHariPemakaian.setText(listPenggunaan.get(position).getHari_penggunaan());

        int panjang = listPenggunaan.get(position).getBiaya();
        if (panjang>9999 && panjang<99999){
            tvHargaHistory.setText(String.valueOf(listPenggunaan.get(position).getBiaya()).substring(0, 2)+"k");
        }else if (panjang>99999 && panjang<999999){
            tvHargaHistory.setText(String.valueOf(listPenggunaan.get(position).getBiaya()).substring(0, 3)+"k");
        }else if (panjang>999999){
            tvHargaHistory.setText(String.valueOf(listPenggunaan.get(position).getBiaya()).substring(0, 4)+"k");
        }

        String transportasi = listPenggunaan.get(position).getTransportasi();
        if (transportasi.equals("bus")){
            oval.setBackgroundResource(R.drawable.shape_oval_bus);
        }else if (transportasi.equals("travel")){
            oval.setBackgroundResource(R.drawable.mobil_penumpang);
        }else if (transportasi.equals("pete")){
            oval.setBackgroundResource(R.drawable.shape_oval_pete);
        }
        riwayatRute.setText(listPenggunaan.get(position).getDari()+" > "+listPenggunaan.get(position).getTujuan());
        riwayatHari.setText(listPenggunaan.get(position).getHari_keberangkatan());

        String[] tgl_berangkat = String.valueOf(listPenggunaan.get(position).getTgl_berangkat()).split(" ");
        String tglBerangkat = tgl_berangkat[0];
        riwayatTanggal.setText(tglBerangkat);
        tvDeskripsiVoucher.setText(listPenggunaan.get(position).getDeskripsi());


        imgClose.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgClose:
                    Df_voucher_penggunaan.super.onStop();
                    Df_voucher_penggunaan.super.onDestroyView();
                    break;
            }
        }
    };
}
