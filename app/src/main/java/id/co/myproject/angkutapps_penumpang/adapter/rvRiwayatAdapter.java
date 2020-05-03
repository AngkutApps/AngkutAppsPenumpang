package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.LoadViewRiwayat;

public class rvRiwayatAdapter extends RecyclerView.Adapter<rvRiwayatAdapter.ViewHolder> {

    private Context context;
    private ArrayList<LoadViewRiwayat> loadViewRiwayats;

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
                    Toast.makeText(context, "Menu : "+loadViewRiwayats.get(position).getRute_perjalanan(), Toast.LENGTH_SHORT).show();
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

        public ViewHolder( View itemView) {
            super(itemView);

            tvHarga = itemView.findViewById(R.id.tvHargaHistory);
            tvHari = itemView.findViewById(R.id.riwayatHari);
            tvTanggal = itemView.findViewById(R.id.riwayatTanggal);
            cvRiwayatPerjalanan = itemView.findViewById(R.id.cvRiwayatPerjalanan);
            tvRutePerjalanan = itemView.findViewById(R.id.riwayatRute);

        }
    }
}
