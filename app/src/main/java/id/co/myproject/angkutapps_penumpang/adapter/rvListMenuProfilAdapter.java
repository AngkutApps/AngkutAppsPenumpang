package id.co.myproject.angkutapps_penumpang.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.view.login.LoginActivity;
import id.co.myproject.angkutapps_penumpang.model.Value;
import id.co.myproject.angkutapps_penumpang.request.ApiRequest;
import id.co.myproject.angkutapps_penumpang.view.profil.dialog_fragment.Df_BeriMasukan;
import id.co.myproject.angkutapps_penumpang.view.profil.dialog_fragment.Df_BagikanFeedback;
import id.co.myproject.angkutapps_penumpang.view.profil.KontakDarurat;
import id.co.myproject.angkutapps_penumpang.view.profil.LokasiDitandai;
import id.co.myproject.angkutapps_penumpang.view.profil.PemilihanBahasa;
import id.co.myproject.angkutapps_penumpang.view.profil.Pengaturan;
import id.co.myproject.angkutapps_penumpang.view.profil.Penjadwalan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class rvListMenuProfilAdapter extends RecyclerView.Adapter<rvListMenuProfilAdapter.ViewHolder> {

    private Context context;
    ApiRequest apiRequest;

    String[] menu = {"Bahasa","Dijadwalkan","Lokasi Ditandai","Pusat Bantuan","Kontak Darurat","Pengaturan","Bagikan Feedback", "Beri Masukan","FAQ","Log Out"};

    public rvListMenuProfilAdapter(Context context, ApiRequest apiRequest) {
        this.context = context;
        this.apiRequest = apiRequest;
    }

    @Override
    public rvListMenuProfilAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_list_menu_profil, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvListMenuProfilAdapter.ViewHolder holder, int position) {
        holder.tvListMenuProfil.setText(menu[position]);
        holder.cvMenuProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilih_menu_akun(position);
//                Toast.makeText(context, "Event : "+menu[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cvMenuProfil;
        TextView tvListMenuProfil;

        public ViewHolder(View itemView) {
            super(itemView);

            cvMenuProfil = itemView.findViewById(R.id.cvMenuProfil);
            tvListMenuProfil = itemView.findViewById(R.id.tvListMenuProfil);

        }
    }

    private void pilih_menu_akun(int position){
        switch (position){
            case 0 :
                context.startActivity(new Intent(context, PemilihanBahasa.class));
                break;
            case 1 :
                context.startActivity(new Intent(context, Penjadwalan.class));
                break;
            case 2 :
                context.startActivity(new Intent(context, LokasiDitandai.class));
                break;
//            case 3 :
//                context.startActivity(new Intent(context, PemilihanBahasa.class));
//                break;
            case 4 :
                context.startActivity(new Intent(context, KontakDarurat.class));
                break;
            case 5 :
                context.startActivity(new Intent(context, Pengaturan.class));
                break;
            case 6 :
                BottomDialogFragment(new Df_BagikanFeedback());
                break;
            case 7 :
                setFragment(new Df_BeriMasukan());
                break;
            case 9 :
                signOutProses();
                break;
        }
    }

    private void signOutProses() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Log Out");
        builder.setMessage("Apakah anda yakin ingin Log Out ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Proses ...");
                progressDialog.show();
                Call<Value> signOut = apiRequest.logoutUserRequest(FirebaseAuth.getInstance().getCurrentUser().getUid());
                signOut.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        progressDialog.dismiss();
                        dialog.dismiss();
                        if (response.isSuccessful()){
                            Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            if (response.body().getValue() == 1){
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(context, LoginActivity.class);
                                context.startActivity(intent);
                                ((Activity)context).finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setFragment(DialogFragment fragment){
        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if (prev !=null){
            fragmentTransaction.remove(prev);
        }
        fragment.show(fragmentTransaction, "dialog");
    }

    private void BottomDialogFragment(BottomSheetDialogFragment frag){
        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        frag.show(fragmentManager, "ExampleBottomSheet");
    }



}
