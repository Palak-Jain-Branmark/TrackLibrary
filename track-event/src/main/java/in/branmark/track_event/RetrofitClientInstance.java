package in.branmark.mailmart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import in.branmark.mailmart.database.UserResponse;
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
    private static final String BASE_URL = "http://cp.foxymark.com/";

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

       // http://cp.foxymark.com/test-b.php?siteid=""&uid=""&pass=""&token=""&phn_detail=""&google_adv_id=""
        //http://cp.foxymark.com/auth?what=login
        @FormUrlEncoded
        @POST("auth?what=login")
        Call<ResponseBody> login_validate(
                @Field("siteid")  String siteId,
                @Field("uid") String email_id,
                @Field("pass") String password,
                @Field("token") String FirebaseToken,
                @Field("google_adv_id") String google_adv_id,
                @Field("phn_detail[FINGERPRINT]") String phoneDetail_p,
                @Field("phn_detail[SDK]") String phoneDetail_s,
                @Field("phn_detail[RELEASE]") String phoneDetail_d,
                @Field("phn_detail[MODEL]") String phoneDetail_m);

        @GET("auth?what=auth")
        Call<ResponseBody> urlRedirectResponse(
                @Query("_aUth")  String _aUth,
                @Query("_rd") String _rd);

        @GET("auth?what=logout")
        Call<ResponseBody> logout(
                @Query("login_key")  String loginkey,
                @Query("uid") String uid);

        @GET("auth?what=forget_pass")
        Call<ResponseBody> forgetPasword(
                @Query("siteid")  String siteId,
                @Query("uid") String email_id);

        @GET("/sendServerFirebaseToken.php")
        UserResponse sendRegistrationServerToken(
                @Query("id") String id,
                @Query("token") String refreshedToken);

    }

}
