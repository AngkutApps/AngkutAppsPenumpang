package id.co.myproject.angkutapps_penumpang;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import id.co.myproject.angkutapps_penumpang.helper.KeberangkatanListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputJumlahFragment extends DialogFragment {

    EditText etJumlah, etBarang;
    Button btnNext;
    String tujuan;
    KeberangkatanListener listener;

    public InputJumlahFragment(String tujuan, KeberangkatanListener listener) {
        // Required empty public constructor
        this.tujuan = tujuan;
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_jumlah, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etJumlah = view.findViewById(R.id.et_jumlah);
        etBarang = view.findViewById(R.id.et_barang);
        btnNext = view.findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFinishedPengisian(tujuan, etJumlah.getText().toString(), etBarang.getText().toString());
                dismiss();
            }
        });

    }
}
