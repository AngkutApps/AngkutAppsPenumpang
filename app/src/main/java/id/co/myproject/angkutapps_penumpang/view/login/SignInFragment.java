package id.co.myproject.angkutapps_penumpang.view.login;

import android.animation.ArgbEvaluator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.view.MainActivity;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.PagerLoginAdapter;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.Value;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import id.co.myproject.angkutapps_penumpang.model.*;

import static id.co.myproject.angkutapps_penumpang.helper.Utils.LOGIN_KEY;
import static id.co.myproject.angkutapps_penumpang.helper.Utils.LOGIN_STATUS;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    //baru
    ViewPager viewPager;
    PagerLoginAdapter pagerAdapter;
    List<viewPagerLogin> viewPagerLogins;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    int img = R.drawable.loading;
    String[] title = {"Transportasi Lintas Daerah Dalam Genggaman","Tracking Lokasi Transportasi Daerah yang Real Time",
            "Harga yang Murah dan Transparan",
    "Terdapat Fitur Multi Relation", "Keterbukaan Informasi Detail Transportasi Daerah"};

    String[] desc = {"Satu klik dan pesan transportasi lintas daerah anda untuk pulang kampung atau keluar kota melalui smartphone",
            "Tracking transportasi daerah bookingan anda kapan saja dan dimana saja secara real time",
    "Harga yang murah dan transparan membuat anda menjadi lebih yakin untuk memilih",
    "Fitur multi relation memungkinkan anda untuk membooking mobil yang sama dengan kerabat walaupun " +
            "kota penjemputannya berbeda", "Terdapat fitur lihat informasi transportasi dan driver terlebih dahulu untuk memilih transportasi yang tepat dan aman"};

    TextView tvPoint1, tvPoint2, tvPoint3, tvPoint4, tvPoint5;
    //baru

    EditText etNoHp;
    Button btnSignIn;
    TextView tvRegistrasi, tv_email;
    FrameLayout parentFrameLayout;
    int idUser, login_level;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    ApiRequest apiRequest;
    private boolean userExists = false;
    ProgressDialog progressDialog;


    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNoHp = view.findViewById(R.id.et_nomor_hp);
        btnSignIn = view.findViewById(R.id.btn_sign_in);
        tvRegistrasi = view.findViewById(R.id.tv_registrasi);
        tv_email = view.findViewById(R.id.tv_email);
        parentFrameLayout = getActivity().findViewById(R.id.frame_login);

        //baru
        tvPoint1 = view.findViewById(R.id.tvPoint1);
        tvPoint2 = view.findViewById(R.id.tvPoint2);
        tvPoint3 = view.findViewById(R.id.tvPoint3);
        tvPoint4 = view.findViewById(R.id.tvPoint4);
        tvPoint5 = view.findViewById(R.id.tvPoint5);

        viewPagerLogins = new ArrayList<>();
        for (int i=0; i<5;i++){
            viewPagerLogins.add(new viewPagerLogin(img, title[i], desc[i]));
        }

        pagerAdapter = new PagerLoginAdapter(viewPagerLogins,getContext());

        viewPager = view.findViewById(R.id.viewPagerLogin);
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    tvColorPointDefault();
                    tvPoint1.setBackgroundResource(R.drawable.bg_point_slider);
                } else if (position == 1) {
                    tvColorPointDefault();
                    tvPoint2.setBackgroundResource(R.drawable.bg_point_slider);
                }else if (position == 2){
                    tvColorPointDefault();
                    tvPoint3.setBackgroundResource(R.drawable.bg_point_slider);
                }else if (position == 3){
                    tvColorPointDefault();
                    tvPoint4.setBackgroundResource(R.drawable.bg_point_slider);
                }else if (position == 4){
                    tvColorPointDefault();
                    tvPoint5.setBackgroundResource(R.drawable.bg_point_slider);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //baru

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memproses ...");
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        sharedPreferences = getActivity().getSharedPreferences(LOGIN_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isConnectionInternet(getActivity())){
                    cekForm();
                }else {
                    Toast.makeText(getActivity(), "Tidak ada jaringan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignUpFragment());
            }
        });

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private void cekForm(){
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Proses ...");
        progressDialog.show();
        if (!TextUtils.isEmpty(etNoHp.getText())) {
            Call<Value> cekNoHPCall = apiRequest.cekNoHpRequest(etNoHp.getText().toString());
            cekNoHPCall.enqueue(new Callback<Value>() {
                @Override
                public void onResponse(Call<Value> call, Response<Value> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()){
                        if (response.body().getValue() == 1){
                            Bundle bundle = new Bundle();
                            bundle.putString("no_hp", etNoHp.getText().toString());
                            bundle.putInt("type_sign", Utils.TYPE_SIGN_IN_BUNDLE);
                            KonfirmasiEmailFragment konfirmasiEmailFragment = new KonfirmasiEmailFragment();
                            konfirmasiEmailFragment.setArguments(bundle);
                            setFragment(konfirmasiEmailFragment);
                        }else {
                            Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<Value> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "No HP tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Proses ...");
        progressDialog.show();
        boolean statusLogin = sharedPreferences.getBoolean(LOGIN_STATUS, false);
        if (statusLogin){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }else {
            progressDialog.dismiss();
            setFragment(new SignInFragment());
        }
    }

    private void tvColorPointDefault(){
        tvPoint1.setBackgroundResource(R.drawable.bg_point_slider_default);
        tvPoint2.setBackgroundResource(R.drawable.bg_point_slider_default);
        tvPoint3.setBackgroundResource(R.drawable.bg_point_slider_default);
        tvPoint4.setBackgroundResource(R.drawable.bg_point_slider_default);
        tvPoint5.setBackgroundResource(R.drawable.bg_point_slider_default);
    }

}
