package id.co.myproject.angkutapps_penumpang.view.riwayat;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
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
import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_perjalanan_user;
import id.co.myproject.angkutapps_penumpang.request.request_riwayat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPerjalananFragment extends Fragment {

    TextView tvTunai, tvGoPay;
    RecyclerView rvRiwayat;
    rv_rw_perjalanan rvRiwayatperjalananAdapter;
    List<loadView_rw_perjalanan_user> arrayList = new ArrayList<>();
//    progress_bar_custom progressBar;

    ProgressDialog progressDialog;

    public RiwayatPerjalananFragment() {

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_riwayat_pembayaran, container, false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTunai = view.findViewById(R.id.tvTunai);
        tvGoPay = view.findViewById(R.id.tvGoPay);
        rvRiwayat = view.findViewById(R.id.rvPembayaran);
        rvRiwayat.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRiwayat.setHasFixedSize(true);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mohon Tunggu....");
//        progressBar = new progress_bar_custom(getActivity());

        defaultView();

        tvTunai.setOnClickListener(clickListener);
        tvGoPay.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvTunai :
                    normalView();
                    defaultView();
                    break;
                case R.id.tvGoPay :
                    normalView();
                    progressDialog.show();
                    tvGoPay.setBackgroundResource(R.drawable.bg_button_tunai_gopay_custom);
                    tvGoPay.setTextColor(Color.parseColor("#008577"));
                    readData(2);
                    break;
            }
        }
    };

    private void normalView(){
        tvTunai.setBackgroundResource(0);
        tvTunai.setTextColor(Color.GRAY);
        tvGoPay.setBackgroundResource(0);
        tvGoPay.setTextColor(Color.GRAY);
    }

    private void defaultView(){
        progressDialog.show();
        tvTunai.setBackgroundResource(R.drawable.bg_button_tunai_gopay_custom);
        tvTunai.setTextColor(Color.parseColor("#008577"));
        readData(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void readData(int i) {
        Call<List<loadView_rw_perjalanan_user>> call = null;
        if (i==1){
            call = request_riwayat.getInstance().getApi().getRiwayatPembayaranTunai("82397147928");
        }else if (i==2){
            call = request_riwayat.getInstance().getApi().getRiwayatPembayaranElektronik("82397147928");
        }
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
