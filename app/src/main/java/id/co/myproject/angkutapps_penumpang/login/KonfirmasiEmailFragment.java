package id.co.myproject.angkutapps_penumpang.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.co.myproject.angkutapps_penumpang.R;

public class KonfirmasiEmailFragment extends Fragment implements View.OnClickListener{

    private EditText etKonfirOTP1, etKonfirOTP2, etKonfirOTP3, etKonfirOTP4, etKonfirOTP5, etKonfirOTP6;

    public KonfirmasiEmailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_konfirmasi_email_nohp, container, false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etKonfirOTP1 = view.findViewById(R.id.etKonfirOTP1);
        etKonfirOTP2 = view.findViewById(R.id.etKonfirOTP2);
        etKonfirOTP3 = view.findViewById(R.id.etKonfirOTP3);
        etKonfirOTP4 = view.findViewById(R.id.etKonfirOTP4);
        etKonfirOTP5 = view.findViewById(R.id.etKonfirOTP5);
        etKonfirOTP6 = view.findViewById(R.id.etKonfirOTP6);

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
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }

}
