package id.co.myproject.angkutapps_penumpang.view.riwayat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.model.*;

public class RiwayatVoucherFragment extends Fragment {

    TextView tvPenggunaan, tvPembelian;
    RecyclerView rvRiwayat;
    rvRiwayatAdapter rvRiwayatperjalananAdapter;
    ArrayList<LoadViewRiwayat> arrayList;
    ArrayList<loadPromoVoucherku> listPromo;
    rvPromoVoucherku rvPromoVoucherku;

    public RiwayatVoucherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_riwayat_promo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvPenggunaan = view.findViewById(R.id.tvPenggunaan);
        tvPembelian = view.findViewById(R.id.tvPembelian);
        rvRiwayat = view.findViewById(R.id.rvPromo);

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
                    tvPembelian.setBackgroundResource(R.drawable.bg_button_tunai_gopay_custom);
                    tvPembelian.setTextColor(Color.parseColor("#008577"));
                    PembelianPromo("20k","Promo Pembelian", "Jum'at","07-07-2007");
                    break;
            }
        }
    };

    private void normalView(){
        tvPembelian.setBackgroundResource(0);
        tvPembelian.setTextColor(Color.GRAY);
        tvPenggunaan.setBackgroundResource(0);
        tvPenggunaan.setTextColor(Color.GRAY);
    }

    private void defaultView(){
        tvPenggunaan.setBackgroundResource(R.drawable.bg_button_tunai_gopay_custom);
        tvPenggunaan.setTextColor(Color.parseColor("#008577"));
        loadPromo(R.drawable.loading, 2, "Nikmati Promo Hingga Tembus Pagi Sampai Capek dan Drop Out", "20-08-2021");
    }

    private void loadPromo(int img, int kondisi, String title, String masa_berlaku){
        listPromo = new ArrayList<>();
        for (int i=0 ; i< 10;i++){
            listPromo.add(new loadPromoVoucherku(img,kondisi,title,masa_berlaku));
        }
        rvPromoVoucherku = new rvPromoVoucherku(getContext(), listPromo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvRiwayat.setLayoutManager(layoutManager);
        rvRiwayat.setHasFixedSize(true);
        rvRiwayat.setAdapter(rvPromoVoucherku);
    }

    private void PembelianPromo(String harga, String desc, String hari, String tanggal){
        arrayList = new ArrayList<>();
        for (int i=0 ; i< 10;i++){
            arrayList.add(new LoadViewRiwayat(harga,desc,hari,tanggal));
        }
        rvRiwayatperjalananAdapter = new rvRiwayatAdapter(getContext(), arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvRiwayat.setLayoutManager(layoutManager);
        rvRiwayat.setHasFixedSize(true);
        rvRiwayat.setAdapter(rvRiwayatperjalananAdapter);
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
