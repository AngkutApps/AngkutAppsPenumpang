package id.co.myproject.angkutapps_penumpang.view.promo.dialog_fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rv_list_sk;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadSKVoucher;
import id.co.myproject.angkutapps_penumpang.request.request_promo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class df_voucherku extends DialogFragment {

    ImageView imgClose;
    TextView titleVoucher, masaBerlakuVoucher, deskripsiVoucher;
    RecyclerView rvSyaratKetentuan;

    rv_list_sk list_sk;
    List<LoadSKVoucher> listSK = new ArrayList<>();

    ProgressDialog progressDialog;

    String kode_voucher, nama_voucher, masa_berlaku;
    int harga, point;
    String deskripsi, foto_url, hari_pembelian;

    public df_voucherku(String kode_voucher, String nama_voucher, String masa_berlaku, int harga, int point, String deskripsi, String foto_url, String hari_pembelian) {
        this.kode_voucher = kode_voucher;
        this.nama_voucher = nama_voucher;
        this.masa_berlaku = masa_berlaku;
        this.harga = harga;
        this.point = point;
        this.deskripsi = deskripsi;
        this.foto_url = foto_url;
        this.hari_pembelian = hari_pembelian;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.df_promo_voucherku, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleVoucher = view.findViewById(R.id.titleVoucher);
        masaBerlakuVoucher = view.findViewById(R.id.masaBerlakuVoucher);
        deskripsiVoucher = view.findViewById(R.id.deksripsiVoucher);
        rvSyaratKetentuan = view.findViewById(R.id.rvSyaratKetentuan);
        imgClose = view.findViewById(R.id.imgClose);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mohon Tunggu....");

        titleVoucher.setText(nama_voucher);
        masaBerlakuVoucher.setText(masa_berlaku);
        deskripsiVoucher.setText(deskripsi);

        rvSyaratKetentuan.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSyaratKetentuan.setHasFixedSize(true);
        progressDialog.show();
        loadSyaratKetentuan();

        imgClose.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgClose :
                    df_voucherku.super.onStop();
                    df_voucherku.super.onDestroyView();
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

}
