package id.co.myproject.angkutapps_penumpang.view.profil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.myproject.angkutapps_penumpang.BuildConfig;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.User;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.request.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    CircleImageView ivUser;
    TextView tvUser;
    RecyclerView rvListMenuProfil;
    rv_menu_list_akun rvAdapter;
    RelativeLayout rlProfil;
    SharedPreferences sharedPreferences;
    String noHpUser;
    ApiRequest apiRequest;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        sharedPreferences = getActivity().getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");

        ivUser = view.findViewById(R.id.iv_user);
        tvUser = view.findViewById(R.id.tv_user);
        rvListMenuProfil = view.findViewById(R.id.rvListMenuProfil);
        rlProfil = view.findViewById(R.id.rl_profil);
        rvAdapter = new rv_menu_list_akun(getContext(), apiRequest);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvListMenuProfil.setLayoutManager(layoutManager);
        rvListMenuProfil.setHasFixedSize(true);
        rvListMenuProfil.setAdapter(rvAdapter);

        rlProfil.setOnClickListener(clickListener);


    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_profil:
                    startActivity(new Intent(getContext(), ProfilUser.class));
                    break;
            }
        }
    };


    private void loadData(){
        Call<User> userCall = apiRequest.penumpangByIdRequest(noHpUser);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user = response.body();int fotoUser;
                    if(user.getFoto().equals("")){
                        if (user.getJk().equals("L")){
                            fotoUser = R.drawable.person_male;
                        }else {
                            fotoUser = R.drawable.person_female;
                        }
                        Glide.with(getActivity()).load(fotoUser)
                                .into(ivUser);
                    }else {
                        Glide.with(getActivity()).load(BuildConfig.BASE_URL_GAMBAR+"profil/"+user.getFoto())
                                .into(ivUser);
                    }
                    tvUser.setText(user.getNama());

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}
