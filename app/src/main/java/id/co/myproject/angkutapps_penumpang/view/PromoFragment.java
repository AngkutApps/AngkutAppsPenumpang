package id.co.myproject.angkutapps_penumpang.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.co.myproject.angkutapps_penumpang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromoFragment extends Fragment {

    TextView voucherku, beliVoucher;

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
    }
}
