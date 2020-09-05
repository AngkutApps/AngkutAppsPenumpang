package id.co.myproject.angkutapps_penumpang.view.riwayat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadVoucher;
import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_voucher_penggunaan;
import id.co.myproject.angkutapps_penumpang.request.request_promo;
import id.co.myproject.angkutapps_penumpang.request.request_riwayat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatVoucherFragment extends Fragment {

    TextView tvPenggunaan, tvPembelian;
    RecyclerView rvRiwayat;
    rv_rw_voucher_pembelian rv_voucher_pembelian;
    List<LoadVoucher> listPembelian = new ArrayList<>();
    List<loadView_rw_voucher_penggunaan> listPenggunaan = new ArrayList<>();
    rv_rw_voucher_penggunaan voucherPenggunaanAdapter;
    SharedPreferences sharedPreferences;

    ProgressDialog progressDialog;

    public RiwayatVoucherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_riwayat_promo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);

        tvPenggunaan = view.findViewById(R.id.tvPenggunaan);
        tvPembelian = view.findViewById(R.id.tvPembelian);
        rvRiwayat = view.findViewById(R.id.rvPromo);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mohon Tunggu....");
        rvRiwayat.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRiwayat.setHasFixedSize(true);

        defaultView();

        tvPenggunaan.setOnClickListener(clickListener);
        tvPembelian.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvPenggunaan :
                    normalView();
                    defaultView();
                    break;
                case R.id.tvPembelian :
                    normalView();
                    tvPembelian.setBackgroundResource(R.drawable.bg_button_voucher);
                    tvPembelian.setTextColor(Color.parseColor("#FFFFFF"));
//                    tvPembelian.setBackgroundResource(R.drawable.bg_button_tunai_gopay_custom);
//                    tvPembelian.setTextColor(Color.parseColor("#008577"));
                    progressDialog.show();
                    loadPembelianPromo();
                    break;
            }
        }
    };

    private void normalView(){
        tvPembelian.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvPembelian.setTextColor(Color.parseColor("#008577"));
        tvPenggunaan.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvPenggunaan.setTextColor(Color.parseColor("#008577"));
//        tvPembelian.setBackgroundResource(0);
//        tvPembelian.setTextColor(Color.GRAY);
//        tvPenggunaan.setBackgroundResource(0);
//        tvPenggunaan.setTextColor(Color.GRAY);
    }

    private void defaultView(){
//        tvPenggunaan.setBackgroundResource(R.drawable.bg_button_tunai_gopay_custom);
//        tvPenggunaan.setTextColor(Color.parseColor("#008577"));
        tvPenggunaan.setBackgroundResource(R.drawable.bg_button_voucher);
        tvPenggunaan.setTextColor(Color.parseColor("#FFFFFF"));
        progressDialog.show();
        loadPenggunaanVoucher();
    }

    public void loadPenggunaanVoucher() {
        String noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
        Call<List<loadView_rw_voucher_penggunaan>> call = request_riwayat.getInstance().getApi().getRiwayatPenggunaanVoucher(noHpUser);
        call.enqueue(new Callback<List<loadView_rw_voucher_penggunaan>>() {
            @Override
            public void onResponse(Call<List<loadView_rw_voucher_penggunaan>> call, Response<List<loadView_rw_voucher_penggunaan>> response) {
                listPenggunaan = response.body();

                voucherPenggunaanAdapter = new rv_rw_voucher_penggunaan(getContext(), listPenggunaan);
                rvRiwayat.setAdapter(voucherPenggunaanAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<loadView_rw_voucher_penggunaan>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public void loadPembelianPromo() {
        String noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
        Call<List<LoadVoucher>> call = request_riwayat.getInstance().getApi().getRiwayatPembelianVoucher(noHpUser);
        call.enqueue(new Callback<List<LoadVoucher>>() {
            @Override
            public void onResponse(Call<List<LoadVoucher>> call, Response<List<LoadVoucher>> response) {
                listPembelian = response.body();

                rv_voucher_pembelian = new rv_rw_voucher_pembelian(getContext(), listPembelian);
                rvRiwayat.setAdapter(rv_voucher_pembelian);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<LoadVoucher>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
