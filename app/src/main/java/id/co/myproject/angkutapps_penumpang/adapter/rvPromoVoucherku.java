package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.*;
import id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment.Df_Voucherku;

public class rvPromoVoucherku extends RecyclerView.Adapter<rvPromoVoucherku.ViewHolder> {

    private Context context;
    private ArrayList<loadPromoVoucherku> loadPromoVoucherku;

    public rvPromoVoucherku(Context context, ArrayList<loadPromoVoucherku> loadPromoVoucherku) {
        this.context = context;
        this.loadPromoVoucherku = loadPromoVoucherku;
    }

    @Override
    public rvPromoVoucherku.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_promo_voucherku, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvPromoVoucherku.ViewHolder holder, int position) {
        holder.imgVoucherku.setImageResource(loadPromoVoucherku.get(position).getImg());
        holder.tvTitle.setText(loadPromoVoucherku.get(position).getTitle());
        if (loadPromoVoucherku.get(position).getKondisi_sk()==1){
            holder.tv_masaberlaku.setText("Berlaku s/d "+loadPromoVoucherku.get(position).getMasa_berlaku());
            holder.sk_berlaku.setText("s&k berlaku");
        }else {
            holder.tv_masaberlaku.setText("Senin");
            holder.sk_berlaku.setText(loadPromoVoucherku.get(position).getMasa_berlaku());
        }
        holder.cvVoucherku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Dicobaji Dulu", Toast.LENGTH_SHORT).show();
                setFragment(new Df_Voucherku());
            }
        });
    }

    @Override
    public int getItemCount() {
        return loadPromoVoucherku.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout cvVoucherku;
        TextView tvTitle, tv_masaberlaku, sk_berlaku;
        ImageView imgVoucherku;

        public ViewHolder(View itemView) {
            super(itemView);

            cvVoucherku = itemView.findViewById(R.id.layVoucher);
            tvTitle = itemView.findViewById(R.id.titleVoucherku);
            tv_masaberlaku = itemView.findViewById(R.id.masaBerlakuVoucherku);
            sk_berlaku = itemView.findViewById(R.id.sk_berlaku);
            imgVoucherku = itemView.findViewById(R.id.imgVoucherku);

        }
    }

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
