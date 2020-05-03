package id.co.myproject.angkutapps_penumpang.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.myproject.angkutapps_penumpang.KeberangkatanActivity;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.Value;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    LinearLayout linearBayar, linearIsiUlang, linearRewards;

    RelativeLayout rvSaldoOvo;
    TextView tvPengaturanSaldo;

    CircleImageView iv_profil;
    CardView cvDaerah;
    SharedPreferences sharedPreferences;
    ApiRequest apiRequest;
    String idPenumpang;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT );

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        sharedPreferences = getActivity().getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        idPenumpang = sharedPreferences.getString(Utils.ID_USER_KEY, "");

        cvDaerah = view.findViewById(R.id.cvDaerah);
        iv_profil = view.findViewById(R.id.iv_user);
        rvSaldoOvo = view.findViewById(R.id.rvSaldoOvo);
        tvPengaturanSaldo = view.findViewById(R.id.btnPengaturanOvo);
        linearBayar = view.findViewById(R.id.linearBayar);
        linearIsiUlang = view.findViewById(R.id.linearIsiUlang);
        linearRewards = view.findViewById(R.id.linearRewards);

        cvDaerah.setOnClickListener(clickListener);
        iv_profil.setOnClickListener(clickListener);
        rvSaldoOvo.setOnClickListener(clickListener);
        tvPengaturanSaldo.setOnClickListener(clickListener);
        linearBayar.setOnClickListener(clickListener);
        linearRewards.setOnClickListener(clickListener);
        linearIsiUlang.setOnClickListener(clickListener);

    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cvDaerah :
                    Intent intent = new Intent(getActivity(), KeberangkatanActivity.class);
                    startActivity(intent);
                    break;
                case R.id.iv_user :
                    Toast.makeText(getContext(), "Activity Profil", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rvSaldoOvo :
                    Toast.makeText(getContext(), "Activity Saldo Ovo", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnPengaturanOvo :
                    Toast.makeText(getContext(), "Activity Saldo Ovo", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.linearBayar :
                    Toast.makeText(getContext(), "Activity Bayar", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.linearIsiUlang :
                    Toast.makeText(getContext(), "Activity Isi Ulang", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.linearRewards :
                    Toast.makeText(getContext(), "Activity Rewards", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
