package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import id.co.myproject.angkutapps_penumpang.R;

public class rvListMenuProfilAdapter extends RecyclerView.Adapter<rvListMenuProfilAdapter.ViewHolder> {

    private Context context;

    String[] menu = {"Bahasa","Dijadwalkan","Tempat yang Disimpan","Pusat Bantuan","Kontak Darurat","Pengaturan","Bagikan Feedback", "Beri Masukan","FAQ","sdadianda"};

    public rvListMenuProfilAdapter(Context context) {
        this.context = context;
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
}
