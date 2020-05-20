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
import id.co.myproject.angkutapps_penumpang.model.InformasiKeuntungan;

public class rvInformasiKeuntunganPromo extends RecyclerView.Adapter<rvInformasiKeuntunganPromo.ViewHolder> {

    private Context context;
    private ArrayList<InformasiKeuntungan> infoKeuntungan;

    public rvInformasiKeuntunganPromo(Context context, ArrayList<InformasiKeuntungan> infoKeuntungan) {
        this.context = context;
        this.infoKeuntungan = infoKeuntungan;
    }

    @Override
    public rvInformasiKeuntunganPromo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_info_keuntungan_promo, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvInformasiKeuntunganPromo.ViewHolder holder, int position) {
        holder.tvNumberInfo.setText(String.valueOf(infoKeuntungan.get(position).getNumber()));
        holder.tvInformasiKeuntungan.setText(infoKeuntungan.get(position).getInformasi());
    }

    @Override
    public int getItemCount() {
        return infoKeuntungan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumberInfo, tvInformasiKeuntungan;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNumberInfo = itemView.findViewById(R.id.tvNumberInfo);
            tvInformasiKeuntungan = itemView.findViewById(R.id.tvInfoKeuntungan);

        }
    }
}
