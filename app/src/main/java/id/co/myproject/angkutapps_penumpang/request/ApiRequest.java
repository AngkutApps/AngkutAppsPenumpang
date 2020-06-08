package id.co.myproject.angkutapps_penumpang.request;

import id.co.myproject.angkutapps_penumpang.model.data_object.Driver;
import id.co.myproject.angkutapps_penumpang.model.data_object.Value;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("registrasi_penumpang.php")
    Call<Value> registrasiUserRequest(
            @Field("email") String email,
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("jk") String jk
    );

    @FormUrlEncoded
    @POST("input_keberangkatan.php")
    Call<Value> inputKeberangkatanRequest(
            @Field("id_penumpang") String idPenumpang,
            @Field("tempat") String tempat,
            @Field("jumlah") String jumlah,
            @Field("barang") String barang
    );

    @FormUrlEncoded
    @POST("cek_no_hp.php")
    Call<Value> cekNoHpRequest(
            @Field("no_hp") String nohp
    );

    @FormUrlEncoded
    @POST("login_penumpang.php")
    Call<Value> loginUserRequest(
            @Field("no_hp") String noHp
    );

    @FormUrlEncoded
    @POST("logout_penumpang.php")
    Call<Value> logoutUserRequest(
            @Field("no_hp") String noHp
    );

    @GET("tampil_driver.php")
    Call<Driver> driverByIdRequest(
            @Query("id_user") String idUser
    );

}
