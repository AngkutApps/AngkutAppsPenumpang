package id.co.myproject.angkutapps_penumpang.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.myproject.angkutapps_penumpang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AktivitasFragment extends Fragment {

    public AktivitasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aktivitas, container, false);
    }
}
