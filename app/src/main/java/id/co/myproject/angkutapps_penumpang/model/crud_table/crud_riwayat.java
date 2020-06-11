package id.co.myproject.angkutapps_penumpang.model.crud_table;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class crud_riwayat {

    Context context;

    public crud_riwayat(Context context) {
        this.context = context;
    }

    public void deleteVoucherPembelian(String id, String no_hp){
        AndroidNetworking.post("http://angkutapps.com/angkut_api/riwayat/delete_rw_voucher_pembelian.php")
                .addBodyParameter("no_hp", no_hp)
                .addBodyParameter("id_pembelian_voucher", id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Riwayat Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Riwayat Gagal Terhapus", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deleteVoucherPenggunaan(String id){
        AndroidNetworking.post("http://angkutapps.com/angkut_api/riwayat/delete_rw_voucher_penggunaan.php")
                .addBodyParameter("id_penggunaan_voucher", id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Riwayat Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Riwayat Gagal Terhapus", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deletePerjalanan(String id){
        AndroidNetworking.post("http://angkutapps.com/angkut_api/riwayat/delete_rw_pembayaran.php")
                .addBodyParameter("id_pembayaran", id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Riwayat Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Riwayat Gagal Terhapus", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

//    ArrayList<loadView_rw_perjalanan_user> loadTunai = new ArrayList<>();

//    public ArrayList<loadView_rw_perjalanan_user> loadViewElektronik() {
//        AndroidNetworking.get("http://angkutapps.com/angkut_api/riwayat/read_rw_pembayaran_elektronik.php")
//                .addQueryParameter("no_hp", "82397147928")
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONArray(new JSONArrayRequestListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        //mengambil data dari JSON array pada read_all.php
//                        try {
//                            for (int i = 0; i < response.length(); i++) {
//                                JSONObject data = response.getJSONObject(i);
////                                    //adding the product to product list
//                                loadElektronik.add(new loadView_rw_perjalanan_user(
//                                        data.getInt("biaya"),
//                                        data.getString("transportasi"),
//                                        data.getString("dari"),
//                                        data.getString("tujuan"),
//                                        data.getString("hari_keberangkatan"),
//                                        data.getString("tgl_berangkat"),
//                                        data.getInt("penumpang_dewasa"),
//                                        data.getInt("penumpang_anak"),
//                                        data.getString("tgl_sampai"),
//                                        data.getString("nama_driver"),
//                                        data.getString("plat_mobil"),
//                                        data.getString("merk_mobil"),
//                                        data.getString("warna_kendaraan"),
//                                        data.getInt("id_pembayaran")
//                                ));
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//                        error.printStackTrace();
//                    }
//                });
//        return loadElektronik;
//
//    }
