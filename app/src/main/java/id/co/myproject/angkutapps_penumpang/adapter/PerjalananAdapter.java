package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.data_object.Destinasi;

public class PerjalananAdapter extends RecyclerView.Adapter<PerjalananAdapter.ViewHolder> {
    List<Destinasi> destinasiList = new ArrayList<>();
    Context context;

    public PerjalananAdapter(Context context) {
        this.context = context;
    }

    public void setDestinasiList(List<Destinasi> destinasiList) {
        this.destinasiList.clear();
        this.destinasiList.addAll(destinasiList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PerjalananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_perjalanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerjalananAdapter.ViewHolder holder, int position) {
        holder.tvAlamat.setText(destinasiList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return destinasiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvAlamat;
        View view1, view2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
        }
    }
}
