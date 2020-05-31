package id.co.myproject.angkutapps_penumpang.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.LoadViewRiwayat;
import id.co.myproject.angkutapps_penumpang.view.dialogFragment.*;

public class rvRiwayatAdapter extends RecyclerView.Adapter<rvRiwayatAdapter.ViewHolder> {

    private Context context;
    private ArrayList<LoadViewRiwayat> loadViewRiwayats;
    int popup;

    public rvRiwayatAdapter(Context context, ArrayList<LoadViewRiwayat> loadViewRiwayats) {
        this.context = context;
        this.loadViewRiwayats = loadViewRiwayats;
    }

    @Override
    public rvRiwayatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_riwayat_perjalanan, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvRiwayatAdapter.ViewHolder holder, int position) {
        holder.tvHarga.setText(loadViewRiwayats.get(position).getHarga());
        holder.tvRutePerjalanan.setText(loadViewRiwayats.get(position).getRute_perjalanan());
        holder.tvHari.setText(loadViewRiwayats.get(position).getHari());
        holder.tvTanggal.setText(loadViewRiwayats.get(position).getTanggal());
        holder.cvRiwayatPerjalanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new DetailRiwayatFragment());
            }
        });
        holder.cvRiwayatPerjalanan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(context, "Harga : " + loadViewRiwayats.get(position).getHarga(), Toast.LENGTH_SHORT).show();
                popupmenu(v,position);
                return true;
            }
        });
        holder.imgPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupmenu(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loadViewRiwayats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvRiwayatPerjalanan;
        TextView tvHarga, tvRutePerjalanan, tvHari, tvTanggal;
        ImageButton imgPengaturan;

        public ViewHolder(View itemView) {
            super(itemView);

            tvHarga = itemView.findViewById(R.id.tvHargaHistory);
            tvHari = itemView.findViewById(R.id.riwayatHari);
            tvTanggal = itemView.findViewById(R.id.riwayatTanggal);
            cvRiwayatPerjalanan = itemView.findViewById(R.id.cvRiwayatPerjalanan);
            tvRutePerjalanan = itemView.findViewById(R.id.riwayatRute);
            imgPengaturan = itemView.findViewById(R.id.btnDetailRiwayat);

        }
    }

    private void popupmenu(View v, int position){
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(clickMenuListener);
        popup = position;
        popupMenu.show();
    }

    private PopupMenu.OnMenuItemClickListener clickMenuListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.popup_hapus:
                    Toast.makeText(context, "Popup hapus "+popup, Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    };

    private void setFragment(DialogFragment fragment){
        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if (prev !=null){
            fragmentTransaction.remove(prev);
        }
        fragment.show(fragmentTransaction, "dialog");
    }
}
