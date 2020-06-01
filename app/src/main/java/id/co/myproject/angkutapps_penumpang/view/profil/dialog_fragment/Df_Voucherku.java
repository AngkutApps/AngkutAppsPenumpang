package id.co.myproject.angkutapps_penumpang.view.profil.dialog_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.model.InformasiKeuntungan;

public class Df_Voucherku extends DialogFragment {

    ImageView imgClose;

    RecyclerView rvInformasiKeuntungan;
    rvInformasiKeuntunganPromo rvInformasiKeuntunganPromo;
    ArrayList<InformasiKeuntungan> infoKeuntungan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_penggunaan_promo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgClose = view.findViewById(R.id.imgClose);
        rvInformasiKeuntungan = view.findViewById(R.id.rvInformasiKeuntungan);
        imgClose.setOnClickListener(clickListener);


        infoKeuntungan = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            infoKeuntungan.add(new InformasiKeuntungan(i, "Syarat dan Ketentuan Berlaku jika anda membeli promo yang tepat sesuai dengan jadwal anda yang akan datang"));
        }
        rvInformasiKeuntunganPromo = new rvInformasiKeuntunganPromo(getContext(), infoKeuntungan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvInformasiKeuntungan.setLayoutManager(layoutManager);
        rvInformasiKeuntungan.setHasFixedSize(true);
        rvInformasiKeuntungan.setAdapter(rvInformasiKeuntunganPromo);

//        rvListMenuProfil = view.findViewById(R.id.rvListMenuProfil);
//        rvAdapter = new rvListMenuProfilAdapter(getContext());
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        rvListMenuProfil.setLayoutManager(layoutManager);
//        rvListMenuProfil.setHasFixedSize(true);
//        rvListMenuProfil.setAdapter(rvAdapter);

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
