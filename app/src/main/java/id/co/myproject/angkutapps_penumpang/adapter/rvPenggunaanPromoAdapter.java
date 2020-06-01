package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.view.promo.dialog_fragment.df_beli_voucher;
import id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment.Df_Voucherku;

public class rvPenggunaanPromoAdapter extends RecyclerView.Adapter<rvPenggunaanPromoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> loadViewPenggunaan;

    public rvPenggunaanPromoAdapter(Context context, ArrayList<Integer> loadViewPenggunaan) {
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

        holder.cvPenggunaanPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new df_beli_voucher());
            }
        });
    }

    @Override
    public int getItemCount() {
        return loadViewPenggunaan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvPenggunaanPromo;

        public ViewHolder(View itemView) {
            super(itemView);

            cvPenggunaanPromo = itemView.findViewById(R.id.cvPenggunaanPromo);

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
