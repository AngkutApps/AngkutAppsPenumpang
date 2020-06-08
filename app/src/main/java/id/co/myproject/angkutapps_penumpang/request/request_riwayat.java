package id.co.myproject.angkutapps_penumpang.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class request_riwayat {

    private static final String Base_URL = "https://angkutapps.com/angkut_api/riwayat/";
    private static request_riwayat apiClient;
    private static Retrofit retrofit;

    private request_riwayat(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized request_riwayat getInstance(){
        if(apiClient == null){
            apiClient = new request_riwayat();
        }
        return apiClient;
    }

    public api_riwayat getApi(){
        return retrofit.create(api_riwayat.class);
    }

}
