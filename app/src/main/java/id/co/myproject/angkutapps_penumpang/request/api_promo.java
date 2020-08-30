package id.co.myproject.angkutapps_penumpang.request;

import java.util.List;

import id.co.myproject.angkutapps_penumpang.model.data_object.LoadSKVoucher;
import id.co.myproject.angkutapps_penumpang.model.data_object.LoadVoucher;
import id.co.myproject.angkutapps_penumpang.model.data_object.loadView_rw_perjalanan_user;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api_promo {

    @GET("read_promo_beli_voucher.php")
    Call<List<LoadVoucher>> getPromoBeliVoucher(
//            @Query("no_hp") String no_hp
    );

    @GET("read_promo_voucherku.php")
    Call<List<LoadVoucher>> getPromoVoucherku(
            @Query("no_hp") String no_hp
    );

}
