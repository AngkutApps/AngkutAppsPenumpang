package id.co.myproject.angkutapps_penumpang.model;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

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
                .addBodyParameter("nomor_kontak_darurat", nomor)
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
                .addBodyParameter("nomor_kontak_darurat", nomor)
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
                .addBodyParameter("nomor_kontak_darurat", nomor)
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
