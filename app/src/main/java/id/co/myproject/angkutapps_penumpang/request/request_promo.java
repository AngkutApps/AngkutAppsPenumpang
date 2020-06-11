package id.co.myproject.angkutapps_penumpang.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class request_promo {

    private static final String Base_URL = "https://angkutapps.com/angkut_api/promo/";
    private static request_promo apiClient;
    private static Retrofit retrofit;

    private request_promo(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized request_promo getInstance(){
        if(apiClient == null){
            apiClient = new request_promo();
        }
        return apiClient;
    }

    public api_promo getApi(){
        return retrofit.create(api_promo.class);
    }

}
