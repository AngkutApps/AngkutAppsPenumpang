package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadVoucher;
import id.co.myproject.angkutapps_penumpang.view.promo.dialog_fragment.df_beli_voucher;

public class rv_promo_beli_voucher extends RecyclerView.Adapter<rv_promo_beli_voucher.ViewHolder> {

    private Context context;
    private List<LoadVoucher> loadViewPenggunaan;

    public rv_promo_beli_voucher(Context context, List<LoadVoucher> loadViewPenggunaan) {
        this.context = context;
        this.loadViewPenggunaan = loadViewPenggunaan;
    }

    @Override
    public rv_promo_beli_voucher.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_promo_beli_voucher, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rv_promo_beli_voucher.ViewHolder holder, int position) {
        String[] masaBerlaku = String.valueOf(loadViewPenggunaan.get(position).getMasa_berlaku()).split(" ");
        String masa_berlaku = masaBerlaku[0];

        holder.tvMasaBerlaku.setText(masa_berlaku);
        holder.tvHarga.setText(""+loadViewPenggunaan.get(position).getHarga());
        holder.cvBeliVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BeliVoucher",""+loadViewPenggunaan.get(position).getKode_voucher());
                setFragment(new df_beli_voucher(
                        loadViewPenggunaan.get(position),
//                        loadViewPenggunaan.get(position).getKode_voucher(),
//                        loadViewPenggunaan.get(position).getNama_voucher(),
                        masa_berlaku
//                        loadViewPenggunaan.get(position).getHarga(),
//                        loadViewPenggunaan.get(position).getPoint(),
//                        loadViewPenggunaan.get(position).getDeskripsi(),
//                        loadViewPenggunaan.get(position).getFoto_url()
                ));
            }
        });
    }

    @Override
    public int getItemCount() {
        return loadViewPenggunaan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvBeliVoucher;
        TextView tvMasaBerlaku, tvHarga;

        public ViewHolder(View itemView) {
            super(itemView);

            cvBeliVoucher = itemView.findViewById(R.id.cvBeliVoucher);
            tvMasaBerlaku = itemView.findViewById(R.id.tvMasaBerlaku);
            tvHarga = itemView.findViewById(R.id.tvHarga);

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
