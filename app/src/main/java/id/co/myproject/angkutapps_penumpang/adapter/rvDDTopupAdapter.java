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

public class rvDDTopupAdapter extends RecyclerView.Adapter<rvDDTopupAdapter.ViewHolder> {

    Context context;
    ArrayList<String> keterangan;

    public rvDDTopupAdapter(Context context, ArrayList<String> keterangan) {
        this.context = context;
        this.keterangan = keterangan;
    }

    @Override
    public rvDDTopupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_dd_topup_atm, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvDDTopupAdapter.ViewHolder holder, int position) {
        holder.tvNumber.setText(position+1+".");
        holder.tvKeterangan.setText(keterangan.get(position));
    }

    @Override
    public int getItemCount() {
        return keterangan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumber, tvKeterangan;

        public ViewHolder(View itemView) {
            super(itemView);

            tvKeterangan = itemView.findViewById(R.id.keteranganDropDown);
            tvNumber = itemView.findViewById(R.id.numberDropDown);

        }
    }
}
