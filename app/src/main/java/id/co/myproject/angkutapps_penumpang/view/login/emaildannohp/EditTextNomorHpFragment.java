package id.co.myproject.angkutapps_penumpang.view.login.emaildannohp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import id.co.myproject.angkutapps_penumpang.R;

public class EditTextNomorHpFragment extends DialogFragment {

    private EditText etLokasiNomor, etNomorHp;

    public EditTextNomorHpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_lupa_password_nomorhp, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etLokasiNomor = view.findViewById(R.id.etLokasiNomor);
        etNomorHp = view.findViewById(R.id.etNomorHp);

        etNomorHp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
