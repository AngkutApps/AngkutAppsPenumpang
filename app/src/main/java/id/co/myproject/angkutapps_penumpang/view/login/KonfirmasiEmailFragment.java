package id.co.myproject.angkutapps_penumpang.view.login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import id.co.myproject.angkutapps_penumpang.view.MainActivity;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.User;
import id.co.myproject.angkutapps_penumpang.model.Value;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.co.myproject.angkutapps_penumpang.helper.Utils.LOGIN_KEY;
import static id.co.myproject.angkutapps_penumpang.helper.Utils.LOGIN_STATUS;
import static id.co.myproject.angkutapps_penumpang.helper.Utils.NO_HP_USER_KEY;

public class KonfirmasiEmailFragment extends Fragment{

    private EditText etKonfirOTP1, etKonfirOTP2, etKonfirOTP3, etKonfirOTP4, etKonfirOTP5, etKonfirOTP6;
    private TextView tvKirimUlang;
    private User user;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Button btnMasuk;
    String verifCode, noHpUser;
    int type_sign;

    ApiRequest apiRequest;

    public KonfirmasiEmailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_konfirmasi_email_nohp, container, false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

        progressDialog = new ProgressDialog(getActivity());
        sharedPreferences = getActivity().getSharedPreferences(LOGIN_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnMasuk = view.findViewById(R.id.btn_masuk);
        tvKirimUlang = view.findViewById(R.id.tv_kirim_ulang);

        etKonfirOTP1 = view.findViewById(R.id.etKonfirOTP1);
        etKonfirOTP2 = view.findViewById(R.id.etKonfirOTP2);
        etKonfirOTP3 = view.findViewById(R.id.etKonfirOTP3);
        etKonfirOTP4 = view.findViewById(R.id.etKonfirOTP4);
        etKonfirOTP5 = view.findViewById(R.id.etKonfirOTP5);
        etKonfirOTP6 = view.findViewById(R.id.etKonfirOTP6);

        type_sign = getArguments().getInt("type_sign", 0);
        etKonfirOTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etKonfirOTP1.getText().toString().length()==1)
                    etKonfirOTP2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etKonfirOTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etKonfirOTP2.getText().toString().length()==1){
                    etKonfirOTP3.requestFocus();
                }else if (etKonfirOTP2.getText().toString().length()==0){
                    etKonfirOTP1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etKonfirOTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etKonfirOTP3.getText().toString().length()==1){
                    etKonfirOTP4.requestFocus();
                }else if (etKonfirOTP3.getText().toString().length()==0){
                    etKonfirOTP2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etKonfirOTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etKonfirOTP4.getText().toString().length()==1){
                    etKonfirOTP5.requestFocus();
                }else if (etKonfirOTP4.getText().toString().length()==0){
                    etKonfirOTP3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etKonfirOTP5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etKonfirOTP5.getText().toString().length()==1){
                    etKonfirOTP6.requestFocus();
                }else if (etKonfirOTP5.getText().toString().length()==0){
                    etKonfirOTP4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etKonfirOTP6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etKonfirOTP6.getText().toString().length()==0){
                    etKonfirOTP5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (type_sign == Utils.TYPE_SIGN_UP_BUNDLE) {
            user = getArguments().getParcelable("user_data");
            noHpUser = user.getNoHp();
        }else if (type_sign == Utils.TYPE_SIGN_IN_BUNDLE){
            noHpUser = getArguments().getString("no_hp");
        }

        sendVerifyCode();

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        sendSms();

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kode_otp = etKonfirOTP1.getText().toString()+
                        etKonfirOTP2.getText().toString()+
                        etKonfirOTP3.getText().toString()+
                        etKonfirOTP4.getText().toString()+
                        etKonfirOTP5.getText().toString()+
                        etKonfirOTP6.getText().toString();
                verifyCodeProses(kode_otp);
            }
        });

        tvKirimUlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerifyCode();
//                sendSms();
//                sendEsmes();
            }
        });

    }

//    public void sendEsmes(){
//        try{
//            SmsManager smgr = SmsManager.getDefault();
//            smgr.sendTextMessage("6282397147928",null,"Apa tong",null,null);
//            Toast.makeText(getActivity(), "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
//        }
//        catch (Exception e){
//            Toast.makeText(getActivity(), "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
//        }
//
//    }

    public void sendSms() {
        try {
            // Construct data
            Random random = new Random();
            int randomNumber = random.nextInt(999999);

            String apiKey = "apikey=" + "dV2lY0CzIj8-eVl2w1DRx9pRO5qwftD4wsYYiClIZg";
            String message = "&message=" + "koko";
            String sender = "&sender=" + "Mantap";
            String numbers = "&numbers=" + "06282397147928";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            Toast.makeText(getActivity(), "Otp berhasil terkirim", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void sendVerifyCode() {
        progressDialog.setMessage("Mengirim ...");
        progressDialog.show();


        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCall = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Code : "+code, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);
                verifCode = s;
                Toast.makeText(getActivity(), "Berhasil terkirim", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Sontoloyo : "+s, Toast.LENGTH_SHORT).show();
            }
        };
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+62" + noHpUser,        // Phone number to verify
                20,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,   // Activity (for callback binding)
                mCall);

    }

    private void verifyCodeProses(String kode_otp){
        progressDialog.setMessage("Proses ...");
        progressDialog.show();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifCode, kode_otp);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (type_sign == Utils.TYPE_SIGN_UP_BUNDLE) {
                                registerProses();
                            }else if (type_sign == Utils.TYPE_SIGN_IN_BUNDLE){
                                loginProses(kode_otp);
                            }
                        } else {
                            Toast.makeText(getActivity(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void registerProses() {
        Call<Value> callRegistrasiUser = apiRequest.registrasiUserRequest(
                        user.getEmail(),
                        user.getNama(),
                        user.getNoHp(),
                        user.getJk()
                );
                callRegistrasiUser.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        if (response.body().getValue() == 1){
                            String noHpUser = response.body().getNoHpUser();
                            editor.putString(NO_HP_USER_KEY, noHpUser);
                            editor.putBoolean(LOGIN_STATUS, true);
                            editor.commit();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Selamat Datang", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void loginProses(String kodeOtp) {
        Call<Value> callLoginUser = apiRequest.loginUserRequest(noHpUser);
        callLoginUser.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                if (response.isSuccessful()) {
                    if (response.body().getValue() == 1){
                        String noHpUser = response.body().getNoHpUser();
                        editor.putString(NO_HP_USER_KEY, noHpUser);
                        editor.putString("verifId", verifCode);
                        editor.putString("otp", kodeOtp);
                        editor.putBoolean(LOGIN_STATUS, true);
                        editor.commit();
                        progressDialog.dismiss();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }else {
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

}
