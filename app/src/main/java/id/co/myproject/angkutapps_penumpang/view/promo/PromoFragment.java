package id.co.myproject.angkutapps_penumpang.view.promo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadVoucher;
import id.co.myproject.angkutapps_penumpang.request.request_promo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromoFragment extends Fragment {

    TextView voucherku, beliVoucher;
    RecyclerView rvPromoFragment;
    rv_rw_promo_voucherku rv_rw_promo_voucherku;
    rv_promo_beli_voucher rv_promo_beli_voucher;
    List<LoadVoucher> loadBeliVoucher = new ArrayList<>();

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    public PromoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        voucherku = view.findViewById(R.id.tvVoucherku);
        beliVoucher = view.findViewById(R.id.tvBeliVoucher);
        rvPromoFragment = view.findViewById(R.id.rvPromoFragment);

        rvPromoFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPromoFragment.setHasFixedSize(true);

        sharedPreferences = getActivity().getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mohon Tunggu....");
        defaultViewButton();

        voucherku.setOnClickListener(clickListener);
        beliVoucher.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvVoucherku :
                    viewButtonNormal();
                    defaultViewButton();
                    break;
                case R.id.tvBeliVoucher :
                    viewButtonNormal();
                    beliVoucher.setBackgroundResource(R.drawable.bg_button_history);
                    beliVoucher.setTextColor(Color.parseColor("#FFFFFF"));
                    progressDialog.show();
                    loadBagianPromo(2);
                    break;
            }
        }
    };

    private void viewButtonNormal(){
        voucherku.setBackgroundResource(R.drawable.bg_button_history_normal);
        voucherku.setTextColor(Color.parseColor("#008577"));
        beliVoucher.setBackgroundResource(R.drawable.bg_button_history_normal);
        beliVoucher.setTextColor(Color.parseColor("#008577"));
    }

    private void defaultViewButton(){
        voucherku.setBackgroundResource(R.drawable.bg_button_history);
        voucherku.setTextColor(Color.parseColor("#FFFFFF"));
        progressDialog.show();
        loadBagianPromo(3);
    }

    public void loadBagianPromo(int i) {
        String noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");

        Call<List<LoadVoucher>> call = null;
        if (i==2){
            call = request_promo.getInstance().getApi().getPromoBeliVoucher();
        }else if (i==3){
            call = request_promo.getInstance().getApi().getPromoVoucherku(noHpUser);
        }
        call.enqueue(new Callback<List<LoadVoucher>>() {
            @Override
            public void onResponse(Call<List<LoadVoucher>> call, Response<List<LoadVoucher>> response) {
                loadBeliVoucher = response.body();

                if (i==2){
                    rv_promo_beli_voucher = new rv_promo_beli_voucher(getContext(), loadBeliVoucher);
                    rvPromoFragment.setAdapter(rv_promo_beli_voucher);
                }else if (i==3){
                    rv_rw_promo_voucherku = new rv_rw_promo_voucherku(getContext(), loadBeliVoucher);
                    rvPromoFragment.setAdapter(rv_rw_promo_voucherku);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<LoadVoucher>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

}
