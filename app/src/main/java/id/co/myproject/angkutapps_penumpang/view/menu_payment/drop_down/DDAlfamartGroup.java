package id.co.myproject.angkutapps_penumpang.view.menu_payment.drop_down;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rvDDTopupAdapter;
import id.co.myproject.angkutapps_penumpang.adapter.rvRiwayatDana;
import id.co.myproject.angkutapps_penumpang.model.riwayatDana;
import id.co.myproject.angkutapps_penumpang.view.menu_payment.RiwayatActivity;

public class DDAlfamartGroup extends DialogFragment {

    RecyclerView rvDropDown;
    rvDDTopupAdapter rvAdapter;
    ArrayList<String> listKeterangan;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_topup_dropdown, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvDropDown = view.findViewById(R.id.rvDropDownTopup);
        loadDropDown();

    }

    private void loadDropDown(){
        listKeterangan = new ArrayList<>();
        listKeterangan.add("Insert your card, choose languange, and then input your PIN.");
        listKeterangan.add("Go to Other menu > Payment / Purchase > Other Payment > BRIVA.");
        listKeterangan.add("Input 085299935661.");
        listKeterangan.add("Input desired top up amount (Minimal amount for top up is Rp. 10.000.");
        listKeterangan.add("If the VA Number is correct, the transaction information will be shown.");

        rvAdapter = new rvDDTopupAdapter(getContext(), listKeterangan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvDropDown.setLayoutManager(layoutManager);
        rvDropDown.setHasFixedSize(true);
        rvDropDown.setAdapter(rvAdapter);
    }
}
