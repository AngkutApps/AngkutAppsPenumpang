package id.co.myproject.angkutapps_penumpang.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    RecyclerView rvListMenuProfil;
    rvListMenuProfilAdapter rvAdapter;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvListMenuProfil = view.findViewById(R.id.rvListMenuProfil);
        rvAdapter = new rvListMenuProfilAdapter(getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvListMenuProfil.setLayoutManager(layoutManager);
        rvListMenuProfil.setHasFixedSize(true);
        rvListMenuProfil.setAdapter(rvAdapter);

    }
}
