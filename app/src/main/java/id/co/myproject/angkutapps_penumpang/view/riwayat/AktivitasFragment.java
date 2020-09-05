package id.co.myproject.angkutapps_penumpang.view.riwayat;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.co.myproject.angkutapps_penumpang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AktivitasFragment extends Fragment {

    TextView tvRiwayatPembayaran, tvRiwayatPromo;

    public AktivitasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aktivitas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvRiwayatPembayaran = view.findViewById(R.id.tvRiwayatPembayaran);
        tvRiwayatPromo = view.findViewById(R.id.tvRiwayatPromo);

        defaultViewButton();

        tvRiwayatPembayaran.setOnClickListener(clickListener);
        tvRiwayatPromo.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvRiwayatPembayaran :
                    viewButtonNormal();
                    defaultViewButton();
                    break;
                case R.id.tvRiwayatPromo :
                    viewButtonNormal();
//                    tvRiwayatPromo.setBackgroundResource(R.drawable.bg_button_history);
                    tvRiwayatPromo.setBackgroundColor(Color.parseColor("#008577"));
                    tvRiwayatPromo.setTextColor(Color.parseColor("#FFFFFF"));
                    setFragment(new RiwayatVoucherFragment());
                    break;
            }
        }
    };

    private void viewButtonNormal(){
//        tvRiwayatPromo.setBackgroundResource(R.drawable.bg_button_history_normal);
        tvRiwayatPromo.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvRiwayatPromo.setTextColor(Color.parseColor("#008577"));
//        tvRiwayatPembayaran.setBackgroundResource(R.drawable.bg_button_history_normal);
        tvRiwayatPembayaran.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvRiwayatPembayaran.setTextColor(Color.parseColor("#008577"));
    }

    private void defaultViewButton(){
        tvRiwayatPembayaran.setBackgroundColor(Color.parseColor("#008577"));
//        tvRiwayatPembayaran.setBackgroundResource(R.drawable.bg_button_history);
        tvRiwayatPembayaran.setTextColor(Color.parseColor("#FFFFFF"));
        setFragment(new RiwayatPerjalananFragment());
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameRiwayat, fragment);
        fragmentTransaction.commit();
    }



}
