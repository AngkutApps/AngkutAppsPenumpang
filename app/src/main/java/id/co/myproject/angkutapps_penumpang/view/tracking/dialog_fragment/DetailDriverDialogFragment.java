package id.co.myproject.angkutapps_penumpang.view.tracking.dialog_fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.PerjalananAdapter;
import id.co.myproject.angkutapps_penumpang.helper.BookingListener;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.DataMessage;
import id.co.myproject.angkutapps_penumpang.model.data_object.Destinasi;
import id.co.myproject.angkutapps_penumpang.model.data_object.Driver;
import id.co.myproject.angkutapps_penumpang.model.data_object.FCMResponse;
import id.co.myproject.angkutapps_penumpang.model.data_object.Token;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.FCMClient;
import id.co.myproject.angkutapps_penumpang.view.tracking.dialog_fragment.Df_chat;
import id.co.myproject.angkutapps_penumpang.view.tracking.fitur.CallActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailDriverDialogFragment extends DialogFragment {

    private static final String TAG = "DetailDriverDialogFragm";

    Driver driver;
    TextView tvNamaDriver, tvJk, tvMobil, tvPlat;
    RecyclerView rvTujuan;
    ProgressBar progressBar;
    Button btnBooking;
    RelativeLayout rvaction;
    LinearLayout lvKonfirmasi;
    ImageButton ivCall, ivMessage;

    PerjalananAdapter perjalananAdapter;
    String key, noHpUser;
    Location location;
    String idDestiansi;

    ApiRequest apiRequest;
    BookingListener bookingListener;

    private BroadcastReceiver mOrderReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String kodeDriver = intent.getStringExtra("kode_driver");
            String driverToken = intent.getStringExtra("driver_token");
            Log.d(TAG, "onReceive: KAMPANGGGGGGG : Kode Driver : "+kodeDriver);
            bookingListener.onFinishedBooking(driverToken, kodeDriver);
            dismiss();
        }
    };

    private BroadcastReceiver mCancelOrderReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Orderan anda telah di cancel", Toast.LENGTH_SHORT).show();
            dismiss();
        }
    };

    public DetailDriverDialogFragment(Driver driver, String key, String idDestinasi, String noHpUser, Location location, BookingListener bookingListener) {
        // Required empty public constructor
        this.driver = driver;
        this.key = key;
        this.location = location;
        this.bookingListener = bookingListener;
        this.idDestiansi = idDestinasi;
        this.noHpUser = noHpUser;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_detail_driver_dialog, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mOrderReceiver, new IntentFilter(Utils.ACCEPT_BROADCAST_STRING));

        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mCancelOrderReceiver, new IntentFilter(Utils.CANCEL_BROADCAST_STRING));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiRequest = FCMClient.getClient(Utils.fcmUrl).create(ApiRequest.class);

        tvNamaDriver = view.findViewById(R.id.tv_nama_driver);
        tvJk = view.findViewById(R.id.tv_jk_driver);
        tvMobil = view.findViewById(R.id.tv_mobil_driver);
        tvPlat = view.findViewById(R.id.tv_plat_driver);
        rvTujuan = view.findViewById(R.id.rv_tujuan);
        progressBar = view.findViewById(R.id.progres);
        btnBooking = view.findViewById(R.id.btn_booking);
        rvaction = view.findViewById(R.id.rv_action);
        lvKonfirmasi = view.findViewById(R.id.lv_konfirmasi);
        ivCall = view.findViewById(R.id.iv_call);
        ivMessage = view.findViewById(R.id.iv_messsage);
//        Todo : belum diinisilaaba ivCall, ivMessage

        tvNamaDriver.setText(driver.getNama());
        if (driver.getJk().equals("L")){
            tvJk.setText("Laki-Laki");
        }else {
            tvJk.setText("Perempuan");
        }
        tvMobil.setText(driver.getMerkMobil());
        tvPlat.setText(driver.getPlat());

        rvTujuan.setLayoutManager(new LinearLayoutManager(getActivity()));
        perjalananAdapter = new PerjalananAdapter(getActivity());
        rvTujuan.setAdapter(perjalananAdapter);
        loadPerjalanan();

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestToDriver();
            }
        });
        
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("USER",noHpUser);
                bundle.putString("DRIVER", driver.getNoHp());
                Intent intent = new Intent(getActivity(), CallActivity.class);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
                dismiss();
                
            }
        });
        
        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 17/06/2020
                setFragment(new Df_chat(driver.getNoHp()));
            }
        });

    }

    private void sendRequestToDriver() {
        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference(Utils.token_tbl);
        tokens.orderByKey().equalTo(key)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Token token = snapshot.getValue(Token.class);
                            String passengerToken = FirebaseInstanceId.getInstance().getToken();
                            Map<String, String> content = new HashMap<>();
                            content.put("customer", passengerToken);
                            content.put("lat", String.valueOf(location.getLatitude()));
                            content.put("lng", String.valueOf(location.getLongitude()));
                            content.put("id_destinasi", idDestiansi);
                            content.put("no_hp_user", noHpUser);
                            DataMessage dataMessage = new DataMessage(token.getToken(), content);
                            apiRequest.sendMessage(dataMessage)
                                    .enqueue(new Callback<FCMResponse>() {
                                        @Override
                                        public void onResponse(Call<FCMResponse> call, Response<FCMResponse> response) {
                                            if (response.body().success == 1){
                                                Toast.makeText(getActivity(), "Request sent !!!", Toast.LENGTH_SHORT).show();
                                                lvKonfirmasi.setVisibility(View.VISIBLE);
                                                rvaction.setVisibility(View.GONE);
                                            }else {
                                                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<FCMResponse> call, Throwable t) {
                                            Log.e("SONTOLOYO", "onFailure: "+t.getMessage());
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }


    private void loadPerjalanan() {
        DatabaseReference tb_destinasi_driver = FirebaseDatabase.getInstance().getReference(Utils.destination_tbl).child(key);
        tb_destinasi_driver.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    List<Destinasi> destinasiList = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Destinasi destinasi = snapshot.getValue(Destinasi.class);
                        destinasiList.add(destinasi);
                    }
                    progressBar.setVisibility(View.GONE);
                    rvTujuan.setVisibility(View.VISIBLE);
                    perjalananAdapter.setDestinasiList(destinasiList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mOrderReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mCancelOrderReceiver);
    }

    private void setFragment(DialogFragment fragment){
//        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if (prev !=null){
            fragmentTransaction.remove(prev);
        }
        fragment.show(fragmentTransaction, "dialog");
    }
}
