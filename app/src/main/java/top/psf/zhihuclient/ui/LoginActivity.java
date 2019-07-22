package top.psf.zhihuclient.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import top.psf.zhihuclient.MainActivity;
import top.psf.zhihuclient.R;
import top.psf.zhihuclient.api.BaseView;
import top.psf.zhihuclient.api.LoginPresenter;
import top.psf.zhihuclient.api.ZhihuService;
import top.psf.zhihuclient.uitls.Tools;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements BaseView {
    LoginPresenter presenter;

    @BindView(R.id.tie_username)
    TextInputEditText tieUsername;

    @BindView(R.id.tie_pw)
    TextInputEditText tiePw;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.tr_verify_row)
    TableRow trVerifyRow;

    @BindView(R.id.iv_verify)
    ImageView ivCap;

    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;

    boolean loginWithCap = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.statusBarColor(this);
        presenter = new LoginPresenter();
        presenter.bindView(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginWithCap = false;

    }

    @OnClick(R.id.btn_login)
    public void loginClick(View view){
        if(tieUsername.getText() == null || tiePw.getText() == null){
            Toast.makeText(getApplicationContext(), "请输入用户名和密码", Toast.LENGTH_LONG).show();
            return;
        }
        if(loginWithCap){
            if(etVerifyCode.getText() == null || etVerifyCode.getText().toString().length() == 0){
                Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_LONG).show();
            }else{
                presenter.loginWithPWC(tieUsername.getText().toString(), tiePw.getText().toString(), etVerifyCode.getText().toString());
            }

        }else{
            presenter.loginWithPW(tieUsername.getText().toString(), tiePw.getText().toString());
        }

    }

    @Override
    public void onRemoteResultCallback(REMOTE_TYPE type, Response<JsonObject> result) {
        JsonObject jsonObject = null;
        switch (type){
            case LOGIN_PW:
//                Log.d(this.getLocalClassName(), result.body().toString());
                Toast.makeText(getApplicationContext(), "登入成功", Toast.LENGTH_LONG).show();
                if(result.isSuccessful()){
                    jsonObject = result.body();
                    if (jsonObject.has("result")){
                        if(!jsonObject.get("result").getAsString().equals("ok")){
                            if(jsonObject.has("img_url")){
                                Log.d(getLocalClassName(), jsonObject.get("img_url").getAsString());
                                Glide.with(this).load(Uri.decode(ZhihuService.BASE_URL) + jsonObject.get("img_url").getAsString())
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true).into(ivCap);
                                trVerifyRow.setVisibility(View.VISIBLE);
                                loginWithCap = true;
                            }else{
                                Toast.makeText(getApplicationContext(), "服务器返回结果错误", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "服务器错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case LOGIN_PWC:
                if(result.isSuccessful()){
                    jsonObject = result.body();
                    if(jsonObject.has("result")){
                        if(jsonObject.get("result").getAsString().equals("ok")){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                        }
                    }
                }
                Toast.makeText(getApplicationContext(), "登入出现错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onRemoteFailedCallback(REMOTE_TYPE type) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbindView();
    }
}
