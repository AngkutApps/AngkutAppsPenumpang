package id.co.myproject.angkutapps_penumpang.model.crud_table;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_perjalanan_user;

public class tb_rw_perjalanan_user {

    private Context context;

    public tb_rw_perjalanan_user(Context context) {
        this.context = context;
        Log.i("Tracking2", "sss");
    }

    ArrayList<loadView_rw_perjalanan_user> loadTunai = new ArrayList<>();
    ArrayList<loadView_rw_perjalanan_user> loadElektronik = new ArrayList<>();

    public ArrayList<loadView_rw_perjalanan_user> loadViewTunai() {
        AndroidNetworking.get("http://angkutapps.com/angkut_api/riwayat/read_rw_pembayaran_tunai.php")
                .addQueryParameter("no_hp", "82397147928")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //mengambil data dari JSON array pada read_all.php
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject data = response.getJSONObject(i);
//                                    //adding the product to product list
                                ArrayList<loadView_rw_perjalanan_user> load;
                                loadTunai.add(new loadView_rw_perjalanan_user(
                                        data.getInt("biaya"),
                                        data.getString("transportasi"),
                                        data.getString("dari"),
                                        data.getString("tujuan"),
                                        data.getString("hari_keberangkatan"),
                                        data.getString("tgl_berangkat"),
                                        data.getInt("penumpang_dewasa"),
                                        data.getInt("penumpang_anak"),
                                        data.getString("tgl_sampai"),
                                        data.getString("nama_driver"),
                                        data.getString("plat_mobil"),
                                        data.getString("merk_mobil"),
                                        data.getString("warna_kendaraan"),
                                        data.getInt("id_pembayaran")
                                ));

                            }
                            Log.i("Tracking4", "sss");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                    }
                });
        Log.i("Tracking5", "sss");
        return loadTunai;
    }

    public ArrayList<loadView_rw_perjalanan_user> loadViewElektronik() {
        AndroidNetworking.get("http://angkutapps.com/angkut_api/riwayat/read_rw_pembayaran_elektronik.php")
                .addQueryParameter("no_hp", "82397147928")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //mengambil data dari JSON array pada read_all.php
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject data = response.getJSONObject(i);
//                                    //adding the product to product list
                                loadElektronik.add(new loadView_rw_perjalanan_user(
                                        data.getInt("biaya"),
                                        data.getString("transportasi"),
                                        data.getString("dari"),
                                        data.getString("tujuan"),
                                        data.getString("hari_keberangkatan"),
                                        data.getString("tgl_berangkat"),
                                        data.getInt("penumpang_dewasa"),
                                        data.getInt("penumpang_anak"),
                                        data.getString("tgl_sampai"),
                                        data.getString("nama_driver"),
                                        data.getString("plat_mobil"),
                                        data.getString("merk_mobil"),
                                        data.getString("warna_kendaraan"),
                                        data.getInt("id_pembayaran")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                    }
                });
        return loadElektronik;

    }

    public void deletePerjalanan(String id){
        AndroidNetworking.post("http://angkutapps.com/angkut_api/riwayat/delete_rw_pembayaran.php")
                .addBodyParameter("no_hp", "82397147928")
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
