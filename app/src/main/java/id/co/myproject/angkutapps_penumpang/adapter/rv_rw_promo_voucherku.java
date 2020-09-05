package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadVoucher;
import id.co.myproject.angkutapps_penumpang.view.promo.dialog_fragment.df_voucherku;
import id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment.Df_voucher_penggunaan;

public class rv_rw_promo_voucherku extends RecyclerView.Adapter<rv_rw_promo_voucherku.ViewHolder> {

    private Context context;
    private List<LoadVoucher> loadPromoVoucherku;

    public rv_rw_promo_voucherku(Context context, List<LoadVoucher> loadPromoVoucherku) {
        this.context = context;
        this.loadPromoVoucherku = loadPromoVoucherku;
    }

    @Override
    public rv_rw_promo_voucherku.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_promo_voucherku, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rv_rw_promo_voucherku.ViewHolder holder, int position) {
//        holder.imgVoucherku.setImageResource(R.drawable.loading);
        holder.tvTitle.setText(loadPromoVoucherku.get(position).getNama_voucher());
        String[] masaBerlaku = String.valueOf(loadPromoVoucherku.get(position).getMasa_berlaku()).split(" ");

        String masa_berlaku = masaBerlaku[0];
            holder.tv_masaberlaku.setText("Berlaku s/d "+masa_berlaku);
            holder.sk_berlaku.setText("s&k berlaku");
        holder.cvVoucherku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setFragment(new df_voucherku(
                            loadPromoVoucherku.get(position),
//                            loadPromoVoucherku.get(position).getKode_voucher(),
//                            loadPromoVoucherku.get(position).getNama_voucher(),
                            masa_berlaku
//                            loadPromoVoucherku.get(position).getHarga(),
//                            loadPromoVoucherku.get(position).getPoint(),
//                            loadPromoVoucherku.get(position).getDeskripsi(),
//                            loadPromoVoucherku.get(position).getFoto_url(),
//                            loadPromoVoucherku.get(position).getHari_pembelian()
                    ));
            }
        });
    }

    @Override
    public int getItemCount() {
        return loadPromoVoucherku.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvVoucherku;
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
