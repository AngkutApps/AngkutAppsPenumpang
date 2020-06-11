package id.co.myproject.angkutapps_penumpang.view.profil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.RetrofitRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    RecyclerView rvListMenuProfil;
    rv_menu_list_akun rvAdapter;
    RelativeLayout rlProfil;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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

        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

        rvListMenuProfil = view.findViewById(R.id.rvListMenuProfil);
        rlProfil = view.findViewById(R.id.rl_profil);
        rvAdapter = new rv_menu_list_akun(getContext(), apiRequest);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvListMenuProfil.setLayoutManager(layoutManager);
        rvListMenuProfil.setHasFixedSize(true);
        rvListMenuProfil.setAdapter(rvAdapter);

        rlProfil.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_profil:
                    startActivity(new Intent(getContext(), ProfilUser.class));
                    break;
            }
        }
    };

}
