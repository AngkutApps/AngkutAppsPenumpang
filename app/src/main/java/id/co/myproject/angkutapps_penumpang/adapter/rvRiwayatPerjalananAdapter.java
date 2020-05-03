package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import id.co.myproject.angkutapps_penumpang.R;

public class rvRiwayatPerjalananAdapter extends RecyclerView.Adapter<rvRiwayatPerjalananAdapter.ViewHolder> {

    private Context context;

    String[] menu = {"Makassar > Pinrang","Bone > Wajo","Makassar > Pare-pare","Makassar > Pangkep","Pangkep > Sidrap","Pengaturan","Bagikan Feedback", "Beri Masukan","Makassar > Barru","sdadianda"};

    public rvRiwayatPerjalananAdapter(Context context) {
        this.context = context;
    }

    @Override
    public rvRiwayatPerjalananAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_riwayat_perjalanan, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvRiwayatPerjalananAdapter.ViewHolder holder, int position) {
            holder.tvRutePerjalanan.setText(menu[position]);
            holder.cvRiwayatPerjalanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Menu : "+menu[position], Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return menu.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvRiwayatPerjalanan;
        TextView tvRutePerjalanan;

        public ViewHolder( View itemView) {
            super(itemView);

            cvRiwayatPerjalanan = itemView.findViewById(R.id.cvRiwayatPerjalanan);
            tvRutePerjalanan = itemView.findViewById(R.id.riwayatRute);

        }
    }
}
