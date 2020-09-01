package id.co.myproject.angkutapps_penumpang.view.promo.dialog_fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.crud_table.tb_promo_voucherku;
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

    tb_promo_voucherku tablePromoVoucherku;
    tb_rw_pembelian_voucher_user tablePembelianVoucher;

    ProgressDialog progressDialog;

    String kode_voucher, masa_berlaku;

    SharedPreferences sharedPreferences;

    LoadVoucher loadVoucher;

    public df_beli_voucher(LoadVoucher loadVoucher, String masa_berlaku) {
        this.loadVoucher = loadVoucher;
        this.masa_berlaku = masa_berlaku;
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

        tablePromoVoucherku = new tb_promo_voucherku(getContext());
        tablePembelianVoucher = new tb_rw_pembelian_voucher_user(getContext());

        tvTitleVoucher.setText(loadVoucher.getNama_voucher());
        tvMasaBerlaku.setText(masa_berlaku);
        tvHarga.setText(String.valueOf(loadVoucher.getHarga()));
        tvPoint.setText(String.valueOf(loadVoucher.getPoint()));
        tvDeskripsiPromo.setText(loadVoucher.getDeskripsi());

        sharedPreferences = getActivity().getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mohon Tunggu....");

        rvSyaratKetentuan.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSyaratKetentuan.setHasFixedSize(true);
        progressDialog.show();
        list_sk = new rv_list_sk(getContext(), loadVoucher.getSyarat_ketentuan());
        rvSyaratKetentuan.setAdapter(list_sk);
        progressDialog.dismiss();

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
                    String noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
                    tablePromoVoucherku.insertBeliVoucher(kode_voucher, noHpUser);
                    progress_bar();
                    df_beli_voucher.super.onStop();
                    df_beli_voucher.super.onDestroyView();
                    break;
            }
        }
    };

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void progress_bar(){
        progressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                String noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
                tablePembelianVoucher.insertBeliVoucher(kode_voucher, noHpUser);
            }
        },2000);
    }
}
