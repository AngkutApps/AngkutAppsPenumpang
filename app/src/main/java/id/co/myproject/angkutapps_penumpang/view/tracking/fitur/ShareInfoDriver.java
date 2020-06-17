package id.co.myproject.angkutapps_penumpang.view.tracking.fitur;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.DetailDestinasi;
import id.co.myproject.angkutapps_penumpang.model.data_object.Driver;
import id.co.myproject.angkutapps_penumpang.model.data_object.ListPassenger;

public class ShareInfoDriver {

    private Context context;
    private Driver driver;
    private DetailDestinasi detailDestinasi;

    public ShareInfoDriver(Context context, Driver driver, DetailDestinasi detailDestinasi) {
        this.context = context;
        this.driver = driver;
        this.detailDestinasi = detailDestinasi;
    }

    public void kirimInformasiDriver(){
       String jk;

       if (driver.getJk().equals("L")){
            jk = "Laki-laki";
       }else{
            jk = "perempuan";
       }

        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    myIntent.putExtra(Intent.EXTRA_SUBJECT, "Your body here");
        myIntent.putExtra(Intent.EXTRA_TEXT, "Rekan Anda sedang berada pada tumpangan yang menuju :\n\n" +
                "Dari : "+detailDestinasi.getFromLocation()+"\n"+
                "Tujuan : "+detailDestinasi.getAddress()+"\n\n"+
                "Dengan Driver!!\n"+
                "\tNama Driver : "+driver.getNama()+"\n"+
                "\tKode Driver : "+driver.getKodeDriver()+"\n"+
                "\tJenis Kelamin : "+jk+"\n"+
                "\tMerk Kendaraan : "+driver.getMerkMobil()+"\n"+
                "\tPlat Kendaraan : "+driver.getPlat()+"\n"
                );
        context.startActivity(Intent.createChooser(myIntent, "Kirim Melalui"));
    }

}
