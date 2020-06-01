package id.co.myproject.angkutapps_penumpang.view.login.emaildannohp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import id.co.myproject.angkutapps_penumpang.R;

public class EditTextEmailFragment extends DialogFragment {

    EditText et_email_lupa_password;

    public EditTextEmailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_lupa_password_email, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_email_lupa_password = view.findViewById(R.id.et_email_lupa_password);

    }

}
