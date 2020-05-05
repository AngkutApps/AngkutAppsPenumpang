package id.co.myproject.angkutapps_penumpang.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.model.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromoFragment extends Fragment {

    TextView voucherku, beliVoucher;
    RecyclerView rvPromoFragment;
    rvPromoVoucherku rvPromoVoucherku;
    rvPenggunaanPromoAdapter rvPenggunaanPromoAdapter;
    ArrayList<loadPromoVoucherku> listPromo;
    ArrayList<Integer> loadPenggunaanList;

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
                    PenggunaanPromo();
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
        loadPromo(R.drawable.loading, 1, "Nikmati Promo Hingga Tembus Pagi Sampai Capek dan Drop Out", "20-08-2021");
    }

    private void loadPromo(int img, int kondisi, String title, String masa_berlaku){
        listPromo = new ArrayList<>();
        for (int i=0 ; i< 10;i++){
            listPromo.add(new loadPromoVoucherku(img,kondisi,title,masa_berlaku));
        }

        rvPromoVoucherku = new rvPromoVoucherku(getContext(), listPromo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvPromoFragment.setLayoutManager(layoutManager);
        rvPromoFragment.setHasFixedSize(true);
        rvPromoFragment.setAdapter(rvPromoVoucherku);
    }

    private void PenggunaanPromo(){
        loadPenggunaanList = new ArrayList<>();
        for (int i=0 ; i< 10;i++){
            loadPenggunaanList.add(R.drawable.frame_promo);
        }
        rvPenggunaanPromoAdapter = new rvPenggunaanPromoAdapter(getContext(), loadPenggunaanList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvPromoFragment.setLayoutManager(layoutManager);
        rvPromoFragment.setHasFixedSize(true);
        rvPromoFragment.setAdapter(rvPenggunaanPromoAdapter);
    }
}
