package id.co.myproject.angkutapps_penumpang.view.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.User;
import id.co.myproject.angkutapps_penumpang.model.data_object.Value;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.co.myproject.angkutapps_penumpang.helper.Utils.LOGIN_KEY;

public class SignUpFragment extends Fragment {


    ScrollView svSignUp;
    EditText etNama, etEmail, etNomorHp;
    RadioButton rbL, rbP;
    private CardView isAtLeast8Parent, hasUppercaseParent, hasNumberParent;
    Button btnSignUp;
    TextView tv_login;
    private FrameLayout parentFrameLayout;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private ProgressDialog progressDialog;
    public static final String TAG = SignUpFragment.class.getSimpleName();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ApiRequest apiRequest;
    private boolean isAtLeast8 = false, hasUppercase = false, hasNumber = false, isRegistrationClickable = false;

    int idUser;
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        svSignUp = view.findViewById(R.id.sv_sign_up);
        etNama = view.findViewById(R.id.et_nama);
        etEmail = view.findViewById(R.id.et_email);
        rbL = view.findViewById(R.id.rb_l);
        rbP = view.findViewById(R.id.rb_p);
        etNomorHp = view.findViewById(R.id.et_nomor_hp);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        tv_login = view.findViewById(R.id.tv_login);
        parentFrameLayout = getActivity().findViewById(R.id.frame_login);

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        sharedPreferences = getActivity().getSharedPreferences(LOGIN_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Proses ...");

        rbL.setChecked(true);



//        etNomorHp.addTextChangedListener(new PhoneNumberFormattingTextWatcher("ID"));


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isConnectionInternet(getActivity())){
                    checkInput();
                }else {
                    Toast.makeText(getActivity(), "Tidak ada jaringan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });
    }


    private void checkInput() {
        if (!TextUtils.isEmpty(etEmail.getText())) {
            if (etEmail.getText().toString().matches(emailPattern)) {
                if (!TextUtils.isEmpty(etNomorHp.getText())){
                    if (!TextUtils.isEmpty(etNama.getText())) {
                        progressDialog.show();
                        Call<Value> cekNoHPCall = apiRequest.cekNoHpRequest(etNomorHp.getText().toString());
                        cekNoHPCall.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                if (response.isSuccessful()){
                                    if (response.body().getValue() == 1){
                                        progressDialog.dismiss();
                                        Toast.makeText(getActivity(), "No Hp sudah digunakan, gunakan no hp yang lain", Toast.LENGTH_SHORT).show();
                                    }else {
                                        daftar();
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
                        etNama.setError("Nama tidak boleh kosong");
                        Utils.scrollToView(svSignUp, etNama);
                    }
                }else {

                }
            }else {
                etEmail.setError("Email tidak sesuai format");
                Utils.scrollToView(svSignUp, etEmail);
            }
        }else {
            etEmail.setError("Email tidak boleh kosong");
            Utils.scrollToView(svSignUp, etEmail);
        }
    }


    private void daftar(){
        final String nama = etNama.getText().toString();
        final String email = etEmail.getText().toString();
        final String noHp = etNomorHp.getText().toString();
        User user = new User();
//        String id = UUID.randomUUID().toString();
//
//        user.setIdUser(id.substring(0, 20));
        user.setEmail(email);
        user.setNama(nama);
        user.setNoHp(noHp);

        if (rbP.isChecked()){
            user.setJk("P");
        }else if (rbL.isChecked()){
            user.setJk("L");
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable("user_data", user);
        bundle.putInt("type_sign", Utils.TYPE_SIGN_UP_BUNDLE);
        KonfirmasiEmailFragment konfirmasiEmailFragment = new KonfirmasiEmailFragment();
        konfirmasiEmailFragment.setArguments(bundle);

        progressDialog.dismiss();
        setFragment(konfirmasiEmailFragment);
    }


    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }


}
