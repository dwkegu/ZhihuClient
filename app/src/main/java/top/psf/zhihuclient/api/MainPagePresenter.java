package top.psf.zhihuclient.api;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPagePresenter extends IPresenter<BaseView> {
    ZhihuService api = null;
    public MainPagePresenter(){
        if(api == null)
            api = ResApi.getInstance(ZhihuService.class);
    }
    public void loginWithPW(String username, String password, String captcach){
        if(captcach == null){
            Call<JsonObject> call = api.loginWithPW(username, password);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }


    }
}
