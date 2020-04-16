package id.co.myproject.angkutapps_penumpang.request;

import id.co.myproject.angkutapps_penumpang.model.Driver;
import id.co.myproject.angkutapps_penumpang.model.Value;
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
            @Field("id_penumpang") String idPenumpang,
            @Field("email") String email,
            @Field("password") String password,
            @Field("nama") String nama,
            @Field("foto") String foto
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
    @POST("cek_email.php")
    Call<Value> cekUserCallback(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("login_penumpang.php")
    Call<Value> loginUserRequest(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("tampil_driver.php")
    Call<Driver> driverByIdRequest(
            @Query("id_user") String idUser
    );
}
