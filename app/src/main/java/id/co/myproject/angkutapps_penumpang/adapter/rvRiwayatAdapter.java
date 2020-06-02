package id.co.myproject.angkutapps_penumpang.adapter;

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

import com.androidnetworking.AndroidNetworking;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.crud_table.tb_rw_perjalanan_user;
import id.co.myproject.angkutapps_penumpang.model.loadView_rw_perjalanan_user;
import id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment.Df_DetailRiwayatFragment;

public class rvRiwayatAdapter extends RecyclerView.Adapter<rvRiwayatAdapter.ViewHolder> {

    private Context context;
    private ArrayList<loadView_rw_perjalanan_user> loadViewRiwayats;
    tb_rw_perjalanan_user crudRiwayat;
    int id;

    public rvRiwayatAdapter(Context context, ArrayList<loadView_rw_perjalanan_user> loadViewRiwayats) {
        this.context = context;
        this.loadViewRiwayats = loadViewRiwayats;
    }

    @Override
    public rvRiwayatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_riwayat_perjalanan, null);
        crudRiwayat = new tb_rw_perjalanan_user(context);
        AndroidNetworking.initialize(context);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(rvRiwayatAdapter.ViewHolder holder, int position) {
        int panjang = loadViewRiwayats.get(position).getBiaya();
        if (panjang>9999 && panjang<99999){
            holder.tvHarga.setText(String.valueOf(loadViewRiwayats.get(position).getBiaya()).substring(0, 2)+"k");
        }else if (panjang>99999 && panjang<999999){
            holder.tvHarga.setText(String.valueOf(loadViewRiwayats.get(position).getBiaya()).substring(0, 3)+"k");
        }else if (panjang>999999){
            holder.tvHarga.setText(String.valueOf(loadViewRiwayats.get(position).getBiaya()).substring(0, 4)+"k");
        }
        holder.tvRutePerjalanan.setText(loadViewRiwayats.get(position).getDari()+" > "+loadViewRiwayats.get(position).getTujuan());
        String transportasi = loadViewRiwayats.get(position).getTransportasi();
        if (transportasi.equals("bus")){
            holder.oval.setBackgroundResource(R.drawable.shape_oval_bus);
        }else if (transportasi.equals("travel")){
            holder.oval.setBackgroundResource(R.drawable.shape_oval_travel);
        }else if (transportasi.equals("pete")){
            holder.oval.setBackgroundResource(R.drawable.shape_oval_pete);
        }
        String[] berangkat = String.valueOf(loadViewRiwayats.get(position).getTgl_berangkat()).split(" ");
        String tgl_berangkat = berangkat[0];
        String jam_berangkat = berangkat[1];

        String[] sampai = String.valueOf(loadViewRiwayats.get(position).getTgl_sampai()).split(" ");
        String tgl_sampai = sampai[0];
        String jam_sampai = sampai[1];

        holder.tvHari.setText(loadViewRiwayats.get(position).getHari());
        holder.tvTanggal.setText(tgl_berangkat);
        holder.cvRiwayatPerjalanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new Df_DetailRiwayatFragment(loadViewRiwayats.get(position).getTransportasi(),
                        loadViewRiwayats.get(position).getDari(),loadViewRiwayats.get(position).getTujuan(),
                        loadViewRiwayats.get(position).getHari(),
                        String.valueOf(loadViewRiwayats.get(position).getP_dewasa()),
                        String.valueOf(loadViewRiwayats.get(position).getP_anak()), tgl_berangkat,
                        tgl_sampai,
                        jam_berangkat,jam_sampai,
                        loadViewRiwayats.get(position).getNama_driver(),loadViewRiwayats.get(position).getNomor_plat(),
                        loadViewRiwayats.get(position).getMerk_mobil(),loadViewRiwayats.get(position).getWarna_kendaraan(),
                        holder.tvHarga.getText().toString()));
            }
        });
        holder.cvRiwayatPerjalanan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(context, "Harga : " + loadViewRiwayats.get(position).getHarga(), Toast.LENGTH_SHORT).show();
                popupmenu(v,loadViewRiwayats.get(position).getId());
                return true;
            }
        });
        holder.imgPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupmenu(v,loadViewRiwayats.get(position).getId());
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
        View oval;

        public ViewHolder(View itemView) {
            super(itemView);

            tvHarga = itemView.findViewById(R.id.tvHargaHistory);
            tvHari = itemView.findViewById(R.id.riwayatHari);
            tvTanggal = itemView.findViewById(R.id.riwayatTanggal);
            cvRiwayatPerjalanan = itemView.findViewById(R.id.cvRiwayatPerjalanan);
            tvRutePerjalanan = itemView.findViewById(R.id.riwayatRute);
            imgPengaturan = itemView.findViewById(R.id.btnDetailRiwayat);
            oval = itemView.findViewById(R.id.oval);

        }
    }

    private void popupmenu(View v, int getId){
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        popupMenu.inflate(R.menu.popup_menu);
        id = getId;
        popupMenu.setOnMenuItemClickListener(clickMenuListener);
        popupMenu.show();
    }

    private PopupMenu.OnMenuItemClickListener clickMenuListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.popup_hapus:
                    crudRiwayat.deletePerjalanan(String.valueOf(id));
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
