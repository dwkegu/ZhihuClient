package top.psf.zhihuclient.api;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends IPresenter<BaseView> {
    private ZhihuService api;

    public LoginPresenter(){
        api = ResApi.getInstance(ZhihuService.class);
    }

    public void loginWithPW(String username, String passwd){
        Call<JsonObject> callback = api.loginWithPW(username, passwd);
        callback.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                view.onRemoteResultCallback(BaseView.REMOTE_TYPE.LOGIN_PW, response);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                view.onRemoteFailedCallback(BaseView.REMOTE_TYPE.LOGIN_PW);
            }
        });
    }

    public void loginWithPWC(String username, String passwd, String cap){
        Call<JsonObject> callback = api.loginWithPWC(username, passwd, cap);
        callback.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                view.onRemoteResultCallback(BaseView.REMOTE_TYPE.LOGIN_PWC, response);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                view.onRemoteFailedCallback(BaseView.REMOTE_TYPE.LOGIN_PWC);
            }
        });
    }
}
