package id.co.myproject.angkutapps_penumpang.view.riwayat;

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

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rvRiwayatAdapter;
import id.co.myproject.angkutapps_penumpang.model.crud_table.tb_rw_perjalanan_user;
import id.co.myproject.angkutapps_penumpang.model.loadView_rw_perjalanan_user;

public class RiwayatPerjalananFragment extends Fragment {

    TextView tvTunai, tvGoPay;
    RecyclerView rvRiwayat;
    rvRiwayatAdapter rvRiwayatperjalananAdapter;
    tb_rw_perjalanan_user tb_pembayaran;
    ArrayList<loadView_rw_perjalanan_user> arrayList;

    public RiwayatPerjalananFragment() {

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_riwayat_pembayaran, container, false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTunai = view.findViewById(R.id.tvTunai);
        tvGoPay = view.findViewById(R.id.tvGoPay);
        rvRiwayat = view.findViewById(R.id.rvPembayaran);

        tb_pembayaran = new tb_rw_perjalanan_user(getActivity());

        defaultView();

        tvTunai.setOnClickListener(clickListener);
        tvGoPay.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvTunai :
                    normalView();
                    defaultView();
                    break;
                case R.id.tvGoPay :
                    normalView();
                    tvGoPay.setBackgroundResource(R.drawable.bg_button_tunai_gopay_custom);
                    tvGoPay.setTextColor(Color.parseColor("#008577"));
                    arrayList = tb_pembayaran.loadViewElektronik();
                    PemanggilanAdapter();
                    break;
            }
        }
    };

    private void normalView(){
        tvTunai.setBackgroundResource(0);
        tvTunai.setTextColor(Color.GRAY);
        tvGoPay.setBackgroundResource(0);
        tvGoPay.setTextColor(Color.GRAY);
    }

    private void defaultView(){
        tvTunai.setBackgroundResource(R.drawable.bg_button_tunai_gopay_custom);
        tvTunai.setTextColor(Color.parseColor("#008577"));
        arrayList = tb_pembayaran.loadViewTunai();
        PemanggilanAdapter();
    }

    private void PemanggilanAdapter(){
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
