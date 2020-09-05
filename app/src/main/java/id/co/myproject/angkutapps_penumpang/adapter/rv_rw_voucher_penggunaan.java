package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;

import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.crud_table.crud_riwayat;
import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_voucher_penggunaan;
import id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment.Df_voucher_penggunaan;

public class rv_rw_voucher_penggunaan extends RecyclerView.Adapter<rv_rw_voucher_penggunaan.ViewHolder> {

    Context context;
    List<loadView_rw_voucher_penggunaan> listPenggunaan;

    crud_riwayat crudRiwayat;

    public rv_rw_voucher_penggunaan(Context context, List<loadView_rw_voucher_penggunaan> listPenggunaan) {
        this.context = context;
        this.listPenggunaan = listPenggunaan;
    }

    @Override
    public rv_rw_voucher_penggunaan.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_promo_voucherku, null);
        crudRiwayat = new crud_riwayat(context);
        AndroidNetworking.initialize(context);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rv_rw_voucher_penggunaan.ViewHolder holder, int position) {
        holder.imgVoucherku.setImageResource(R.drawable.mobil_penumpang);
        holder.NamaVoucher.setText(listPenggunaan.get(position).getNama_voucher());
        holder.hari_penggunaan.setText(listPenggunaan.get(position).getHari_penggunaan());
        String[] tgl_penggunaan = String.valueOf(listPenggunaan.get(position).getTgl_penggunaan()).split(" ");
        String tglDigunakan = tgl_penggunaan[0];
        holder.tgl_penggunaan.setText(tglDigunakan);
        holder.layVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new Df_voucher_penggunaan(listPenggunaan, position));
            }
        });
        holder.layVoucher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                popupmenu(v, listPenggunaan.get(position).getId_penggunaan_voucher());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPenggunaan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgVoucherku;
        TextView NamaVoucher, hari_penggunaan, tgl_penggunaan;
        CardView layVoucher;

        public ViewHolder(View itemView) {
            super(itemView);

            imgVoucherku = itemView.findViewById(R.id.imgVoucherku);
            NamaVoucher = itemView.findViewById(R.id.titleVoucherku);
            hari_penggunaan = itemView.findViewById(R.id.masaBerlakuVoucherku);
            tgl_penggunaan = itemView.findViewById(R.id.sk_berlaku);
            layVoucher = itemView.findViewById(R.id.layVoucher);

        }
    }

    private void popupmenu(View v, int getId){
        androidx.appcompat.widget.PopupMenu popupMenu = new androidx.appcompat.widget.PopupMenu(v.getContext(), v);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(context, "Pop Up Menu : "+getId, Toast.LENGTH_SHORT).show();
                crudRiwayat.deleteVoucherPenggunaan(String.valueOf(getId));
                return false;
            }
        });
        popupMenu.show();
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
