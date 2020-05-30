package id.co.myproject.angkutapps_penumpang.model;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class crud_tb_kontak_darurat_user {

    private String pesanTambahKontak;

    public String tambahKontakDarurat(String nama, String hubungan, String nomor){
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
                        pesanTambahKontak = ""+response;
                    }

                    @Override
                    public void onError(ANError anError) {
                        pesanTambahKontak = ""+anError;
                    }
                });
        return pesanTambahKontak;
    }

    public void readKontakDarurat(){

    }

    public String deleteKontakDarurat(){
        return "Kontak Berhasil Dihapus";
    }

    public String updateKontakDarurat(){
        return "Kontak Berhasil Dirubah";
    }

}
