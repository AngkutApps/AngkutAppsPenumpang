package id.co.myproject.angkutapps_penumpang.request;

import java.util.List;

import id.co.myproject.angkutapps_penumpang.model.data_object.LoadKontakDarurat;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api_tb_kontak_darurat {

    //Tambahan Irwan
    @GET("read_kontak_darurat_user.php")
    Call<List<LoadKontakDarurat>> getKontakDarurat(
            @Query("no_hp") String no_hp
    );

}
