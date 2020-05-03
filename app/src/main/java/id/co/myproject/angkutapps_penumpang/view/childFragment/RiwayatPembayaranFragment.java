package id.co.myproject.angkutapps_penumpang.view.childFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rvRiwayatPerjalananAdapter;

public class RiwayatPembayaranFragment extends Fragment {

    TextView tvTunai, tvGoPay;
    RecyclerView rvRiwayat;
    rvRiwayatPerjalananAdapter rvRiwayatperjalananAdapter;

    public RiwayatPembayaranFragment() {
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
        PemanggilanAdapter();
    }

    private void PemanggilanAdapter(){
        rvRiwayatperjalananAdapter = new rvRiwayatPerjalananAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvRiwayat.setLayoutManager(layoutManager);
        rvRiwayat.setHasFixedSize(true);
        rvRiwayat.setAdapter(rvRiwayatperjalananAdapter);
    }
}
