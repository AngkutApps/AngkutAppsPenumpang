package id.co.myproject.angkutapps_penumpang.view.riwayat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rv_rw_perjalanan;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_perjalanan_user;
import id.co.myproject.angkutapps_penumpang.request.request_riwayat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPerjalananFragment extends Fragment {

    RecyclerView rvRiwayat;
    rv_rw_perjalanan rvRiwayatperjalananAdapter;
    List<loadView_rw_perjalanan_user> arrayList = new ArrayList<>();
//    progress_bar_custom progressBar;

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    public RiwayatPerjalananFragment() {

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_riwayat_pembayaran, container, false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRiwayat = view.findViewById(R.id.rvPembayaran);
        rvRiwayat.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRiwayat.setHasFixedSize(true);

        sharedPreferences = getActivity().getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mohon Tunggu....");
        progressDialog.show();
//        progressBar = new progress_bar_custom(getActivity());

        readData();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void readData() {
        String noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
        Call<List<loadView_rw_perjalanan_user>> call = request_riwayat.getInstance().getApi().getRiwayatPembayaran(noHpUser);
        call.enqueue(new Callback<List<loadView_rw_perjalanan_user>>() {
            @Override
            public void onResponse(Call<List<loadView_rw_perjalanan_user>> call, Response<List<loadView_rw_perjalanan_user>> response) {
                arrayList = response.body();

                rvRiwayatperjalananAdapter = new rv_rw_perjalanan(getContext(), arrayList);
                rvRiwayat.setAdapter(rvRiwayatperjalananAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<loadView_rw_perjalanan_user>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

//    public void progress_bar(){
//        progressBar.startLoadingDialog();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                progressBar.dismissDialog();
//                Log.i("Tracking7", "sss");
//            }
//        },10000);
//    }
}
