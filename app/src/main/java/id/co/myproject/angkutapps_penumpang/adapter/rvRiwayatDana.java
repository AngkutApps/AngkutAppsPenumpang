package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.data_object.riwayatDana;

public class rvRiwayatDana extends RecyclerView.Adapter<rvRiwayatDana.ViewHolder> {


    Context context;
    ArrayList<riwayatDana> riwayatDana;

    public rvRiwayatDana(Context context, ArrayList<id.co.myproject.angkutapps_penumpang.model.data_object.riwayatDana> riwayatDana) {
        this.context = context;
        this.riwayatDana = riwayatDana;
    }

    @Override
    public rvRiwayatDana.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_riwayat_pembayaran, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvRiwayatDana.ViewHolder holder, int position) {
        holder.tvMediaPembayaran.setText(riwayatDana.get(position).getMedia());
        holder.tvHargaPembayaran.setText(riwayatDana.get(position).getHarga());
        holder.tvWaktuPembayaran.setText(riwayatDana.get(position).getTanggal());
        holder.statusPembayaran.setText(riwayatDana.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return riwayatDana.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMediaPembayaran, tvWaktuPembayaran, tvHargaPembayaran, statusPembayaran;

        public ViewHolder(View itemView) {
            super(itemView);

            tvMediaPembayaran = itemView.findViewById(R.id.tvMediaPembayaran);
            tvWaktuPembayaran = itemView.findViewById(R.id.tvWaktuPembayaran);
            tvHargaPembayaran = itemView.findViewById(R.id.tvHargaPembayaran);
            statusPembayaran = itemView.findViewById(R.id.statusPembayaran);

        }
    }
}
