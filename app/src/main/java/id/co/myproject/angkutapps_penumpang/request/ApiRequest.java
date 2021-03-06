package id.co.myproject.angkutapps_penumpang.request;

import id.co.myproject.angkutapps_penumpang.model.data_object.DataMessage;
import id.co.myproject.angkutapps_penumpang.model.data_object.Driver;
import id.co.myproject.angkutapps_penumpang.model.data_object.FCMResponse;
import id.co.myproject.angkutapps_penumpang.model.data_object.InputOtp;
import id.co.myproject.angkutapps_penumpang.model.data_object.RiwayatPerjalanan;
import id.co.myproject.angkutapps_penumpang.model.data_object.User;
import id.co.myproject.angkutapps_penumpang.model.data_object.Value;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

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

    @POST("edit_profil.php")
    Call<Value> editProfilRequest(
            @Body User user
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

    @GET("tampil_penumpang.php")
    Call<User> penumpangByIdRequest(
            @Query("no_hp") String nohp
    );

//    GoogleMapsApi
    @GET
    Call<String> getPath(@Url String url);

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAA-SJF_lE:APA91bF26uwqPJ-bLQjeB0KotXnpRe0986RjsfgDeAueKMzOGTWSyltxndSY5l5MRj6AweIa7aH78BqKPv6MaLPdRp05mCIo5KJp6iSNJ2asobQ90W_9yE8KCpkQaCWGAt7sYu6GsUkx"
    })
    @POST("fcm/send")
    Call<FCMResponse> sendMessage(@Body DataMessage body);

    @FormUrlEncoded
    @POST("riwayat/insert_rw_perjalanan_driver.php")
    Call<Value> insertRiwayat(
            @Field("kode_driver") String kodeDriver,
            @Field("no_hp") String noHp,
            @Field("dari") String dari,
            @Field("tujuan") String tujuan,
            @Field("alamat_dari") String alamatDari,
            @Field("alamat_tujuan") String alamatTujuan,
            @Field("transportasi") String transportasi,
            @Field("p_anak") String pAnak,
            @Field("p_dewasa") String pDewasa,
            @Field("biaya") String biaya,
            @Field("tgl_keberangkatan") String tglKeberangkatan,
            @Field("tgl_sampai") String tglSampai
    );

    @FormUrlEncoded
    @POST("insert_rating.php")
    Call<Value> insertRating(
            @Field("kode_driver") String kodeDriver,
            @Field("no_hp") String noHpUser,
            @Field("rating") String rating
    );


    @Headers({
            "Content-Type:application/json",
            "x-api-key: b4emnOXwXDdGSeTZH2DcHKqhHCXCFSqI"
    })
    @PUT("otp/{key}")
    Call<Value> sendOtp(
            @Path("key") String key,
            @Body InputOtp inputOtp
    );


    @Headers({
            "Content-Type:application/json",
            "x-api-key: b4emnOXwXDdGSeTZH2DcHKqhHCXCFSqI"
    })
    @POST("otp/{key}/verifications")
    Call<Value> verifyOtp(
            @Path("key") String key,
            @Body InputOtp inputOtp
    );





}
