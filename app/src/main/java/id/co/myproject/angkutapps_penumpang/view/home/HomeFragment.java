package id.co.myproject.angkutapps_penumpang.view.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.myproject.angkutapps_penumpang.BuildConfig;
import id.co.myproject.angkutapps_penumpang.model.data_object.User;
import id.co.myproject.angkutapps_penumpang.view.tracking.KeberangkatanActivity;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.RetrofitRequest;
import id.co.myproject.angkutapps_penumpang.view.profil.ProfilUser;
import id.co.myproject.angkutapps_penumpang.view.tracking.TrackingActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageView iv_profil;
    TextView tvProfil;
    CardView cvDaerah;
    SharedPreferences sharedPreferences;
    ApiRequest apiRequest;
    String idPenumpang;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT );

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        sharedPreferences = getActivity().getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        idPenumpang = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");

        cvDaerah = view.findViewById(R.id.cvDaerah);
        iv_profil = view.findViewById(R.id.img_foto_profil);
        tvProfil = view.findViewById(R.id.tv_profil);

        cvDaerah.setOnClickListener(clickListener);
        iv_profil.setOnClickListener(clickListener);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    public View.OnClickListener clickListener = v -> {
        switch (v.getId()){
            case R.id.cvDaerah :
                Intent intent = new Intent(getActivity(), TrackingActivity.class);
                startActivity(intent);
                break;
            case R.id.img_foto_profil :
//                    Toast.makeText(getContext(), "Activity Profil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), ProfilUser.class));
                break;
        }
    };

    private void loadData(){
        Call<User> userCall = apiRequest.penumpangByIdRequest(idPenumpang);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user = response.body();
                    int fotoUser;
                    if(user.getFoto().equals("")){
                        if (user.getJk().equals("L")){
                            fotoUser = R.drawable.person_male;
                        }else {
                            fotoUser = R.drawable.person_female;
                        }
                        Glide.with(getActivity().getApplicationContext()).load(fotoUser)
                                .into(iv_profil);
                    }else {
                        Glide.with(getActivity().getApplicationContext()).load(BuildConfig.BASE_URL_GAMBAR+"profil/"+user.getFoto())
                                .into(iv_profil);
                    }
                    tvProfil.setText("Hi, "+user.getNama());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
