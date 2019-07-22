package top.psf.zhihuclient.api;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ZhihuService {
    public final static String BASE_URL = "http://218.193.181.45:8084/";
    @POST("login_pw")
    @FormUrlEncoded
    Call<JsonObject> loginWithPW(@Field("username") String username, @Field("password") String passwd);

    @POST("login_pw")
    @FormUrlEncoded
    Call<JsonObject> loginWithPWC(@Field("username") String username, @Field("password") String passwd, @Field("captcha") String captcha);

}
