package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.LoadKontakDarurat;
import id.co.myproject.angkutapps_penumpang.view.dialogFragment.DetailRiwayatFragment;

public class rvKontakDarurat extends RecyclerView.Adapter<rvKontakDarurat.ViewHolder> {

    private Context context;
    private ArrayList<LoadKontakDarurat> kontakDarurat;
    int popup;

    public rvKontakDarurat(Context context, ArrayList<LoadKontakDarurat> kontakDarurat) {
        this.context = context;
        this.kontakDarurat = kontakDarurat;
    }

    @Override
    public rvKontakDarurat.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_kontak_darurat, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvKontakDarurat.ViewHolder holder, int position) {
        holder.tvNomorKontakDarurat.setText(kontakDarurat.get(position).getNomor_telepon());
        holder.tvNamaKontakDarurat.setText(kontakDarurat.get(position).getNama());
        holder.tvHubunganKontakDarurat.setText("("+kontakDarurat.get(position).getHubungan()+")");
        holder.btnSettingKontakDarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupmenu(v,position);
            }
        });
        holder.rlKontakDarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
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

    private void popupmenu(View v, int position){
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        popupMenu.inflate(R.menu.popup_kontak_darurat);
        popupMenu.setOnMenuItemClickListener(clickMenuListener);
        popup = position;
        popupMenu.show();
    }

    private PopupMenu.OnMenuItemClickListener clickMenuListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.popup_ubah:
                    Toast.makeText(context, "Popup Ubah "+popup, Toast.LENGTH_SHORT).show();
//                    setFragment(new DetailRiwayatFragment());
                    break;
                case R.id.popup_hapus:
                    Toast.makeText(context, "Popup hapus "+popup, Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    };
}
