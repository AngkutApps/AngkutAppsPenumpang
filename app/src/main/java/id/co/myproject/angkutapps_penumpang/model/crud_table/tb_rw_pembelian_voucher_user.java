package id.co.myproject.angkutapps_penumpang.model.crud_table;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class tb_rw_pembelian_voucher_user {

    private Context context;

    public tb_rw_pembelian_voucher_user(Context context) {
        this.context = context;
    }

    public void insertBeliVoucher(String kode_voucher, String no_hp){
        AndroidNetworking.post("http://angkutapps.com/angkut_api/riwayat/insert_rw_voucher_pembelian.php")
                .addBodyParameter("kode_voucher", kode_voucher)
                .addBodyParameter("no_hp", no_hp)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Pembelian Berhasil", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Pembelian Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
    }

//    public void deletePembelianVoucher(String nomor){
//        AndroidNetworking.post("http://angkutapps.com/angkut_api/delete_kontak_darurat_user.php")
//                .addBodyParameter("nomor_kontak_darurat", nomor)
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(context, "Kontak Berhasil Terhapus", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Toast.makeText(context, "Kontak Gagal Terhapus", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

}
