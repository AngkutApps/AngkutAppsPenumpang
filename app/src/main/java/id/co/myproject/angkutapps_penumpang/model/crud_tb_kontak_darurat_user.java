package id.co.myproject.angkutapps_penumpang.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.adapter.rvKontakDarurat;
import id.co.myproject.angkutapps_penumpang.view.menu_akun.KontakDarurat;

public class crud_tb_kontak_darurat_user {

    private Context context;

    public crud_tb_kontak_darurat_user(Context context) {
        this.context = context;
    }

    public void tambahKontakDarurat(String nama, String hubungan, String nomor){
        AndroidNetworking.post("http://angkutapps.com/angkut_api/insert_kontak_darurat_user.php")
                .addBodyParameter("no_hp", "82397147928")
                .addBodyParameter("nama_kontak", nama)
                .addBodyParameter("hubungan_kontak", hubungan)
                .addBodyParameter("nomor_kontak", nomor)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Kontak Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Kontak Gagal Ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deleteKontakDarurat(String nomor){
        AndroidNetworking.post("http://angkutapps.com/angkut_api/delete_kontak_darurat_user.php")
                .addBodyParameter("nomor_kontak", nomor)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Kontak Berhasil Terhapus", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Kontak Gagal Terhapus", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void updateKontakDarurat(String nama, String hubungan, String nomor){
        AndroidNetworking.post("http://angkutapps.com/angkut_api/update_kontak_darurat_user.php")
                .addBodyParameter("no_hp", "82397147928")
                .addBodyParameter("nama_kontak", nama)
                .addBodyParameter("hubungan_kontak", hubungan)
                .addBodyParameter("nomor_kontak", nomor)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Kontak Berhasil Diubah", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Kontak Gagal Diubah", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
