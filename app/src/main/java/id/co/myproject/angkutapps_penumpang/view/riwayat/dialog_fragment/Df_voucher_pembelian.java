package id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rv_list_sk;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadSKVoucher;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadVoucher;
import id.co.myproject.angkutapps_penumpang.request.request_promo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Df_voucher_pembelian extends DialogFragment {

    ImageView imgClose;
    TextView tvTitleVoucher, tvTanggalPembelian, tvHariPembelian, tvMasaBerlaku, tvPembayaran, tvDeskripsiVoucher;

    RecyclerView rvSyaratKetentuan;
    rv_list_sk list_sk;

    ProgressDialog progressDialog;

    String masa_berlaku, tgl_pembelian;

    LoadVoucher loadVoucher;

    public Df_voucher_pembelian(LoadVoucher loadVoucher, String masa_berlaku, String tgl_pembelian) {
        this.loadVoucher = loadVoucher;
        this.masa_berlaku = masa_berlaku;
        this.tgl_pembelian = tgl_pembelian;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.df_rw_pembelian_voucher, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTanggalPembelian = view.findViewById(R.id.tvTanggalPembelian);
        tvTitleVoucher = view.findViewById(R.id.tvTitleVoucher);
        tvDeskripsiVoucher = view.findViewById(R.id.tvDeskripsiVoucher);
        tvPembayaran = view.findViewById(R.id.tvPembayaran);
        tvHariPembelian = view.findViewById(R.id.tvHariPembelian);
        tvMasaBerlaku = view.findViewById(R.id.tvMasaBerlaku);
        tvPembayaran = view.findViewById(R.id.tvPembayaran);
        rvSyaratKetentuan = view.findViewById(R.id.rvSyaratKetentuan);
        imgClose = view.findViewById(R.id.imgClose);

        tvTitleVoucher.setText(loadVoucher.getNama_voucher());
        tvTanggalPembelian.setText(loadVoucher.getTgl_pembelian());
        tvMasaBerlaku.setText(masa_berlaku);
        tvHariPembelian.setText(loadVoucher.getHari_pembelian());
        tvPembayaran.setText(""+loadVoucher.getHarga());
        tvDeskripsiVoucher.setText(loadVoucher.getDeskripsi());

        rvSyaratKetentuan.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSyaratKetentuan.setHasFixedSize(true);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mohon Tunggu....");
        progressDialog.show();
        list_sk = new rv_list_sk(getContext(), loadVoucher.getSyarat_ketentuan());
        rvSyaratKetentuan.setAdapter(list_sk);
        progressDialog.dismiss();

        imgClose.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgClose :
                    Df_voucher_pembelian.super.onStop();
                    Df_voucher_pembelian.super.onDestroyView();
                    break;
            }
        }
    };

}
