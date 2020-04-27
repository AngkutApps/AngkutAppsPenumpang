package id.co.myproject.angkutapps_penumpang.login;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.login.emaildannohp.EditTextEmailFragment;
import id.co.myproject.angkutapps_penumpang.login.emaildannohp.EditTextNomorHpFragment;

public class LupaPasswordFragment extends Fragment implements View.OnClickListener{

    TextView tvEmail, tvNomorHp;

    public LupaPasswordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lupa_password, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvEmail = view.findViewById(R.id.btn_email);
        tvNomorHp = view.findViewById(R.id.btn_nomor_hp);

        defaultSelected();

        tvEmail.setOnClickListener(this::onClick);
        tvNomorHp.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_email:
                normalSelected();
                defaultSelected();
                break;
            case  R.id.btn_nomor_hp:
                normalSelected();
                tvNomorHp.setBackgroundResource(R.drawable.bg_fragment_lupa_password_custom);
                tvNomorHp.setTextColor(Color.parseColor("#FFFFFF"));
                setFragment(new EditTextNomorHpFragment());
                break;
        }
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layoutFrameEmailNoHp, fragment);
        fragmentTransaction.commit();
    }

    public void defaultSelected(){
        tvEmail.setBackgroundResource(R.drawable.bg_fragment_lupa_password_custom);
        tvEmail.setTextColor(Color.parseColor("#FFFFFF"));
        setFragment(new EditTextEmailFragment());
    }

    public void normalSelected(){
        tvEmail.setBackgroundResource(R.drawable.bg_button_lp_email);
        tvEmail.setTextColor(Color.parseColor("#020202"));
        tvNomorHp.setBackgroundResource(R.drawable.bg_button_lp_email);
        tvNomorHp.setTextColor(Color.parseColor("#020202"));
    }

}
