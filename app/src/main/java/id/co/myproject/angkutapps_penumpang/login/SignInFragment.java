package id.co.myproject.angkutapps_penumpang.login;

import android.animation.ArgbEvaluator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.MainActivity;
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

import static id.co.myproject.angkutapps_penumpang.helper.Utils.ID_USER_KEY;
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
    String title = "Transportasi Lintas Daerah Dalam Genggaman";
    String desc = "Satu klik dan tracking transportasi bookingan anda kapan saja dan dimana saja secara real time";

    TextView tvPoint1, tvPoint2, tvPoint3, tvPoint4, tvPoint5;
    //baru

    EditText etEmail, etPassword;
    Button btnSignIn;
    TextView tvRegistrasi, tvLupaPassword, tv_email;
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

        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnSignIn = view.findViewById(R.id.btn_sign_in);
        tvRegistrasi = view.findViewById(R.id.tv_registrasi);
        tvLupaPassword = view.findViewById(R.id.tv_lupa_password);
        tv_email = view.findViewById(R.id.tv_email);
        parentFrameLayout = getActivity().findViewById(R.id.frame_login);

        //baru
        tvPoint1 = view.findViewById(R.id.tvPoint1);
        tvPoint2 = view.findViewById(R.id.tvPoint2);
        tvPoint3 = view.findViewById(R.id.tvPoint3);
        tvPoint4 = view.findViewById(R.id.tvPoint4);
        tvPoint5 = view.findViewById(R.id.tvPoint5);

        viewPagerLogins = new ArrayList<>();
        viewPagerLogins.add(new viewPagerLogin(img, title, desc));
        viewPagerLogins.add(new viewPagerLogin(img, title, desc));
        viewPagerLogins.add(new viewPagerLogin(img, title, desc));
        viewPagerLogins.add(new viewPagerLogin(img, title, desc));
        viewPagerLogins.add(new viewPagerLogin(img, title, desc));

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


        btnSignIn.setEnabled(false);
        btnSignIn.setTextColor(Color.argb(50,255,255, 255));

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isConnectionInternet(getActivity())){
                    cekForm();
//                    setFragment(new KonfirmasiEmailFragment());
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

        tvLupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new LupaPasswordFragment());
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private void cekForm(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (etEmail.getText().toString().matches(emailPattern)) {
            if (etPassword.length() >= 8) {
                progressDialog.show();
                btnSignIn.setEnabled(true);
                prosesLogin(email, password);
            } else {
                Toast.makeText(getActivity(), "Password kurang boss", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Username atau Password salah boss", Toast.LENGTH_SHORT).show();
        }

    }

    private void checkInput() {
        if (!TextUtils.isEmpty(etEmail.getText())){
            if (!TextUtils.isEmpty(etPassword.getText())){
                btnSignIn.setEnabled(true);
                btnSignIn.setTextColor(Color.rgb(255,255, 255));
            }else {
                btnSignIn.setEnabled(false);
                btnSignIn.setTextColor(Color.argb(50,255,255, 255));
            }
        }else {
            btnSignIn.setEnabled(false);
            btnSignIn.setTextColor(Color.argb(50,255,255, 255));
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

    private void prosesLogin(final String email, final String password) {
        Call<Value> callLoginUser = apiRequest.loginUserRequest(email, password);
        callLoginUser.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                if (response.isSuccessful()) {
                    if (response.body().getValue() == 1) {
                        String idPenumpang = response.body().getIdPenumpang();
                        Call<Value> loginUser = apiRequest.loginUserRequest(email, password);
                        loginUser.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    if(response.body().getValue() == 1){
                                        progressDialog.dismiss();
                                        editor.putString(ID_USER_KEY, idPenumpang);
                                        editor.putBoolean(LOGIN_STATUS, true);
                                        editor.commit();
                                        updateUI();
                                    }else {
                                        progressDialog.dismiss();
                                        btnSignIn.setEnabled(true);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {
                                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    private void tvColorPointDefault(){
        tvPoint1.setBackgroundResource(R.drawable.bg_point_slider_default);
        tvPoint2.setBackgroundResource(R.drawable.bg_point_slider_default);
        tvPoint3.setBackgroundResource(R.drawable.bg_point_slider_default);
        tvPoint4.setBackgroundResource(R.drawable.bg_point_slider_default);
        tvPoint5.setBackgroundResource(R.drawable.bg_point_slider_default);
    }

}
