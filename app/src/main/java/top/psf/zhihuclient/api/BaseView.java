package top.psf.zhihuclient.api;

import com.google.gson.JsonObject;
import retrofit2.Response;

public interface BaseView {

    public static enum REMOTE_TYPE {
        LOGIN_PW,
        LOGIN_PWC,
        LOGIN_TOKEN
    }

    public void onRemoteResultCallback(REMOTE_TYPE type, Response<JsonObject> result);

    public void onRemoteFailedCallback(REMOTE_TYPE type);
}
