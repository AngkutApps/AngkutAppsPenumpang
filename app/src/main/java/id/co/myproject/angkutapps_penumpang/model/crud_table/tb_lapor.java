package id.co.myproject.angkutapps_penumpang.model.crud_table;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class tb_lapor {

    private Context context;

    public tb_lapor(Context context) {
        this.context = context;
    }

    public void insertBeliVoucher(String no_hp, String kode_driver){
        AndroidNetworking.post("http://angkutapps.com/angkut_api/insert_lapor.php")
                .addBodyParameter("no_hp", no_hp)
                .addBodyParameter("kode_driver", kode_driver)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Terima Kasih, Laporan Anda Telah Direkam", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Laporan Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
