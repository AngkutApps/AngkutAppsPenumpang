package id.co.myproject.angkutapps_penumpang.view.riwayat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rvRiwayatAdapter;
import id.co.myproject.angkutapps_penumpang.model.LoadViewRiwayat;

public class RiwayatPerjalananFragment extends Fragment {

    RecyclerView rvRiwayat;
    rvRiwayatAdapter rvRiwayatperjalananAdapter;
    ArrayList<LoadViewRiwayat> arrayList;

    public RiwayatPerjalananFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rv_riwayat_perjanalan, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRiwayat = view.findViewById(R.id.rvRiwayat);
        PemanggilanAdapter("70k","Riwayat Perjalanan","Selasa","06-12-1762");
    }

    private void PemanggilanAdapter(String harga, String desc, String hari, String tanggal){
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
