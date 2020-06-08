package id.co.myproject.angkutapps_penumpang.request;

import java.util.List;

import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_perjalanan_user;
import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_voucher_penggunaan;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api_riwayat {

    @GET("read_rw_pembayaran_elektronik.php")
    Call<List<loadView_rw_perjalanan_user>> getRiwayatPembayaranElektronik(
            @Query("no_hp") String no_hp
    );

    @GET("read_rw_pembayaran_tunai.php")
    Call<List<loadView_rw_perjalanan_user>> getRiwayatPembayaranTunai(
            @Query("no_hp") String no_hp
    );

    @GET("read_rw_voucher_penggunaan.php")
    Call<List<loadView_rw_voucher_penggunaan>> getRiwayatPenggunaanVoucher(
            @Query("no_hp") String no_hp
    );

}
