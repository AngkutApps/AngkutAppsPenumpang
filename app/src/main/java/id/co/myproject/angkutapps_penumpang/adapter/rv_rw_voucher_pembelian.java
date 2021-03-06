package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import com.androidnetworking.AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.crud_table.crud_riwayat;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadVoucher;
import id.co.myproject.angkutapps_penumpang.model.data_object.Value;
import id.co.myproject.angkutapps_penumpang.view.riwayat.dialog_fragment.Df_voucher_pembelian;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class rv_rw_voucher_pembelian extends RecyclerView.Adapter<rv_rw_voucher_pembelian.ViewHolder> {

    Context context;
    List<LoadVoucher> load_pembelian_voucher;

    SharedPreferences sharedPreferences;

    crud_riwayat crudRiwayat;

    public rv_rw_voucher_pembelian(Context context, List<LoadVoucher> load_pembelian_voucher) {
        this.context = context;
        this.load_pembelian_voucher = load_pembelian_voucher;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_rw_voucher_pembelian, null);
        sharedPreferences = context.getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        crudRiwayat = new crud_riwayat(context);
        AndroidNetworking.initialize(context);
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
                        load_pembelian_voucher.get(position),
                        masaBerlaku[0],
                        tglPembelian[0]
                ));
            }
        });
        holder.btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");
                popupmenu(v, load_pembelian_voucher.get(position).getId_pembelian_voucher(), noHpUser);
            }
        });

    }

    @Override
    public int getItemCount() {
        return load_pembelian_voucher.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvHargaVoucher, titleVoucher, hariPembelian, tanggalPembelian;
        ImageView oval;
        ImageView btnConfig;
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

    private void popupmenu(View v, int getId, String no_hp){
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                crudRiwayat.deleteVoucherPembelian(""+getId, no_hp);
//                hapusRiwayat(getId);
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
