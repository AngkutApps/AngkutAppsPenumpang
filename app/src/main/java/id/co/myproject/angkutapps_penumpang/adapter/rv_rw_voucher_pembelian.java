package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
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
import id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment.Df_voucher_pembelian;

public class rv_rw_voucher_pembelian extends RecyclerView.Adapter<rv_rw_voucher_pembelian.ViewHolder> {

    Context context;
    List<LoadVoucher> load_pembelian_voucher;

    public rv_rw_voucher_pembelian(Context context, List<LoadVoucher> load_pembelian_voucher) {
        this.context = context;
        this.load_pembelian_voucher = load_pembelian_voucher;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_rw_voucher_pembelian, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int panjang = load_pembelian_voucher.get(position).getHarga();
        if (panjang<9999){
            holder.tvHargaVoucher.setText(String.valueOf(load_pembelian_voucher.get(position).getHarga()).substring(0, 1)+"k");
        }else if (panjang>9999){
            holder.tvHargaVoucher.setText(String.valueOf(load_pembelian_voucher.get(position).getHarga()).substring(0, 2)+"k");
        }
        holder.titleVoucher.setText(load_pembelian_voucher.get(position).getNama_voucher());
        holder.hariPembelian.setText(load_pembelian_voucher.get(position).getHari_pembelian());
        String[] masaBerlaku = String.valueOf(load_pembelian_voucher.get(position).getMasa_berlaku()).split(" ");
        String[] tglPembelian = String.valueOf(load_pembelian_voucher.get(position).getTgl_pembelian()).split(" ");
        holder.tanggalPembelian.setText(tglPembelian[0]);
//        String transportasi = load_pembelian_voucher.get(position).getTransportasi();
//        if (transportasi.equals("bus")){
//            holder.oval.setBackgroundResource(R.drawable.shape_oval_bus);
//        }else if (transportasi.equals("travel")){
//            holder.oval.setBackgroundResource(R.drawable.shape_oval_travel);
//        }else if (transportasi.equals("pete")){
//            holder.oval.setBackgroundResource(R.drawable.shape_oval_pete);
//        }
        holder.cvVoucherPembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new Df_voucher_pembelian(
                        load_pembelian_voucher.get(position).getKode_voucher(),
                        load_pembelian_voucher.get(position).getNama_voucher(),
                        masaBerlaku[0], load_pembelian_voucher.get(position).getHarga(),
                        load_pembelian_voucher.get(position).getPoint(),
                        load_pembelian_voucher.get(position).getDeskripsi(),
                        load_pembelian_voucher.get(position).getFoto_url(),
                        load_pembelian_voucher.get(position).getHari_pembelian(),
                        tglPembelian[0]
                ));
            }
        });
        holder.btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupmenu(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return load_pembelian_voucher.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvHargaVoucher, titleVoucher, hariPembelian, tanggalPembelian;
        View oval;
        ImageButton btnConfig;
        CardView cvVoucherPembelian;

        public ViewHolder(View itemView) {
            super(itemView);

            tvHargaVoucher = itemView.findViewById(R.id.tvHargaVoucher);
            oval = itemView.findViewById(R.id.oval);
            titleVoucher = itemView.findViewById(R.id.titleVoucher);
            hariPembelian = itemView.findViewById(R.id.hariPembelian);
            tanggalPembelian = itemView.findViewById(R.id.tanggalPembelian);
            btnConfig = itemView.findViewById(R.id.btnConfig);
            cvVoucherPembelian = itemView.findViewById(R.id.cvVoucherPembelian);

        }
    }

    private void popupmenu(View v, int getId){
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        popupMenu.inflate(R.menu.popup_menu);
//        id = getId;
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(context, "Pop Up Menu : "+getId, Toast.LENGTH_SHORT).show();
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
