package in.branmark.track_event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://adr.jinex.in/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
    public interface GetDataService {


        //http://adr.jinex.in/app_tracking_api.php
        @FormUrlEncoded
        @POST("app_tracking_api.php")
        Call<ResponseBody> post_request(
                @Field("token")  String token,
                @Field("unique_id") String unique_id,
                @Field("referer") String referer,
                /*@Field("event") String event,
                @Field("event_value") String event_value,
                @Field("key") String key,
                @Field("key_value") String key_value,*/
                @Field("info[phn_detail[FINGERPRINT]") String phoneDetail_p,
                @Field("info[phn_detail[SDK]]") String phoneDetail_s,
                @Field("info[phn_detail[RELEASE]]") String phoneDetail_d,
                @Field("info[phn_detail[MODEL]]") String phoneDetail_m,
                @Field("info[other]]") HashMap<String, String> other_info
        );

        @GET("app_tracking_api.php")
        Call<ResponseBody> get_request(
                @Query("token")  String token,
                @Query("unique_id") String unique_id,
                @Query("referer") String referer,
                @Query("event") String event,
                @Query("event_value") String event_value,
                @Query("key") String key,
                @Query("key_value") String key_value,
                @Query("info[phn_detail[FINGERPRINT]") String phoneDetail_p,
                @Query("info[phn_detail[SDK]]") String phoneDetail_s,
                @Query("info[phn_detail[RELEASE]]") String phoneDetail_d,
                @Query("info[phn_detail[MODEL]]") String phoneDetail_m,
                @Query("info[other]]") String other_info
                );

        @FormUrlEncoded
        @POST("app_tracking_api.php")
        Call<ResponseBody> send_referer(
                @Field("token")  String token,
                @Field("unique_id") String unique_id,
                @Field("referer") String referer);

        @FormUrlEncoded
        @POST("app_tracking_api.php")
        Call<ResponseBody> event_trigger(
                @Field("token")  String token,
                @Field("unique_id") String gaid,
                @Field("event") String eventName);

    }

}
