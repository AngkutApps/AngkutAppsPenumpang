package id.co.myproject.angkutapps_penumpang.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class request_tb_kontak_darurat {

    private static final String Base_URL = "https://angkutapps.com/angkut_api/";
    private static request_tb_kontak_darurat apiClient;
    private static Retrofit retrofit;

    private request_tb_kontak_darurat(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized request_tb_kontak_darurat getInstance(){
        if(apiClient == null){
            apiClient = new request_tb_kontak_darurat();
        }
        return apiClient;
    }

    public api_tb_kontak_darurat getApi(){
        return retrofit.create(api_tb_kontak_darurat.class);
    }

}
