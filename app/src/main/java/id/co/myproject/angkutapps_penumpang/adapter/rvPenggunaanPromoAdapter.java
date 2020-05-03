package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.LoadViewRiwayat;
import id.co.myproject.angkutapps_penumpang.model.*;

public class rvPenggunaanPromoAdapter extends RecyclerView.Adapter<rvPenggunaanPromoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<loadViewPenggunaanPromo> loadViewPenggunaan;

    public rvPenggunaanPromoAdapter(Context context, ArrayList<loadViewPenggunaanPromo> loadViewPenggunaan) {
        this.context = context;
        this.loadViewPenggunaan = loadViewPenggunaan;
    }

    @Override
    public rvPenggunaanPromoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_riwayat_promo, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvPenggunaanPromoAdapter.ViewHolder holder, int position) {
        holder.tvNamaPromo.setText(loadViewPenggunaan.get(position).getTvNamaPromo());
        holder.tvHariPromo.setText(loadViewPenggunaan.get(position).getTvHariPromo());
        holder.tvTanggalPromo.setText(loadViewPenggunaan.get(position).getTvTanggalPromo());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaPromo, tvHariPromo, tvTanggalPromo;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNamaPromo = itemView.findViewById(R.id.tvNamaPromo);
            tvHariPromo = itemView.findViewById(R.id.tvHariPromo);
            tvTanggalPromo = itemView.findViewById(R.id.tvTanggalPromo);

        }
    }
}
