package id.co.myproject.angkutapps_penumpang.view.promo.dialog_fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rv_list_sk;
import id.co.myproject.angkutapps_penumpang.adapter.rv_promo_beli_voucher;
import id.co.myproject.angkutapps_penumpang.model.crud_table.tb_rw_pembelian_voucher_user;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadSKVoucher;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadVoucher;
import id.co.myproject.angkutapps_penumpang.request.request_promo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class df_beli_voucher extends DialogFragment {

    ImageView imgClose;
    TextView tvTitleVoucher, tvMasaBerlaku, tvHarga, tvPoint, tvDeskripsiPromo;
    Button btnBeliVoucher;

    RecyclerView rvSyaratKetentuan;
    rv_list_sk list_sk;
    List<LoadSKVoucher> listSK = new ArrayList<>();

    tb_rw_pembelian_voucher_user crudPembelianVoucher;

    ProgressDialog progressDialog;

    String kode_voucher, nama_voucher, masa_berlaku;
    int harga, point;
    String deskripsi, foto_url;

    public df_beli_voucher(String kode_voucher, String nama_voucher, String masa_berlaku, int harga, int point, String deskripsi, String foto_url) {
        this.kode_voucher = kode_voucher;
        this.nama_voucher = nama_voucher;
        this.masa_berlaku = masa_berlaku;
        this.harga = harga;
        this.point = point;
        this.deskripsi = deskripsi;
        this.foto_url = foto_url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_pembelian_promo, container, false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTitleVoucher = view.findViewById(R.id.tvTitleVoucher);
        tvMasaBerlaku = view.findViewById(R.id.tvMasaBerlaku);
        tvHarga = view.findViewById(R.id.tvHarga);
        tvPoint = view.findViewById(R.id.tvPoint);
        tvDeskripsiPromo = view.findViewById(R.id.tvDeskripsiPromo);
        imgClose = view.findViewById(R.id.imgClose);
        rvSyaratKetentuan = view.findViewById(R.id.rvSyaratKetentuan);
        btnBeliVoucher = view.findViewById(R.id.btnBeliVoucher);

        AndroidNetworking.initialize(getContext());

        crudPembelianVoucher = new tb_rw_pembelian_voucher_user(getContext());
        tvTitleVoucher.setText(nama_voucher);
        tvMasaBerlaku.setText(masa_berlaku);
        tvHarga.setText(String.valueOf(harga));
        tvPoint.setText(String.valueOf(point));
        tvDeskripsiPromo.setText(deskripsi);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mohon Tunggu....");

        rvSyaratKetentuan.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSyaratKetentuan.setHasFixedSize(true);
        progressDialog.show();
        loadSyaratKetentuan();

        imgClose.setOnClickListener(clickListener);
        btnBeliVoucher.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgClose :
                    df_beli_voucher.super.onStop();
                    df_beli_voucher.super.onDestroyView();
                    break;
                case R.id.btnBeliVoucher :
                    crudPembelianVoucher.insertBeliVoucher(kode_voucher, "82397147928");
                    df_beli_voucher.super.onStop();
                    df_beli_voucher.super.onDestroyView();
                    break;
            }
        }
    };

    public void loadSyaratKetentuan() {
        Call<List<LoadSKVoucher>> call = request_promo.getInstance().getApi().getSKBeliVoucher(kode_voucher);
        call.enqueue(new Callback<List<LoadSKVoucher>>() {
            @Override
            public void onResponse(Call<List<LoadSKVoucher>> call, Response<List<LoadSKVoucher>> response) {
                listSK = response.body();

                list_sk = new rv_list_sk(getContext(), listSK);
                rvSyaratKetentuan.setAdapter(list_sk);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<LoadSKVoucher>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
