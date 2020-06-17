package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;

import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadKontakDarurat;
import id.co.myproject.angkutapps_penumpang.model.crud_table.tb_kontak_darurat_user;
import id.co.myproject.angkutapps_penumpang.view.profil.dialog_fragment.Df_TambahKontakDarurat;

public class rv_profil_kontak_darurat extends RecyclerView.Adapter<rv_profil_kontak_darurat.ViewHolder> {

    private Context context;
    private List<LoadKontakDarurat> kontakDarurat;
    String nomorKontak;
    tb_kontak_darurat_user crudKontakDarurat;

    SharedPreferences sharedPreferences;

    public rv_profil_kontak_darurat(Context context, List<LoadKontakDarurat> kontakDarurat) {
        this.context = context;
        this.kontakDarurat = kontakDarurat;
    }

    @Override
    public rv_profil_kontak_darurat.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_kontak_darurat, null);
        sharedPreferences = context.getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        String noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
        crudKontakDarurat = new tb_kontak_darurat_user(context, noHpUser);
        AndroidNetworking.initialize(context);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rv_profil_kontak_darurat.ViewHolder holder, int position) {
        holder.tvNomorKontakDarurat.setText("+62"+kontakDarurat.get(position).getNomor_kontak_darurat());
        holder.tvNamaKontakDarurat.setText(kontakDarurat.get(position).getNama_kontak());
        holder.tvHubunganKontakDarurat.setText("("+kontakDarurat.get(position).getHubungan_kontak()+")");
        holder.btnSettingKontakDarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomor = holder.tvNomorKontakDarurat.getText().toString().trim().substring(3);
                popupmenu(v, nomor);
            }
        });
        holder.rlKontakDarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new Df_TambahKontakDarurat(), holder.tvNamaKontakDarurat.getText().toString().trim(),
                        kontakDarurat.get(position).getHubungan_kontak().trim(), holder.tvNomorKontakDarurat.getText().toString().trim().substring(3));
            }
        });
    }

    @Override
    public int getItemCount() {
        return kontakDarurat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNamaKontakDarurat, tvHubunganKontakDarurat, tvNomorKontakDarurat;
        ImageButton btnSettingKontakDarurat;
        RelativeLayout rlKontakDarurat;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNamaKontakDarurat = itemView.findViewById(R.id.tvNamaKontakDarurat);
            tvHubunganKontakDarurat = itemView.findViewById(R.id.tvHubunganKontakDarurat);
            tvNomorKontakDarurat = itemView.findViewById(R.id.tvNomorKontakDarurat);
            btnSettingKontakDarurat = itemView.findViewById(R.id.btnSettingKontakDarurat);
            rlKontakDarurat = itemView.findViewById(R.id.rlKontakDarurat);

        }
    }

    private void popupmenu(View v, String nomor){
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        popupMenu.inflate(R.menu.popup_kontak_darurat);
        popupMenu.setOnMenuItemClickListener(clickMenuListener);
        nomorKontak = nomor;
        popupMenu.show();
    }

    private PopupMenu.OnMenuItemClickListener clickMenuListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.popup_hapus:
                    crudKontakDarurat.deleteKontakDarurat(nomorKontak);
                    break;
            }
            return false;
        }
    };

    private void setFragment(DialogFragment fragment, String namaKontak, String hubunganKontak, String nomor){
        Bundle data = new Bundle();
        data.putString("namaKontak", namaKontak);
        data.putString("hubunganKontak", hubunganKontak);
        data.putString("nomorKontak", nomor);
        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        fragment.setArguments(data);
        if (prev !=null){
            fragmentTransaction.remove(prev);
        }
        fragment.show(fragmentTransaction, "dialog");
    }

}
