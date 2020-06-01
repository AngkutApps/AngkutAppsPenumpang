package id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import id.co.myproject.angkutapps_penumpang.R;

public class Df_Voucherku extends DialogFragment {

    ImageView imgClose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_rw_penggunaan_promo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgClose = view.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgClose:
                    Df_Voucherku.super.onStop();
                    Df_Voucherku.super.onDestroyView();
                    break;
            }
        }
    };
}
