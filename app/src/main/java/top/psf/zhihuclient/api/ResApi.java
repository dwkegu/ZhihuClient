package top.psf.zhihuclient.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Field;

public class ResApi {
    public static ResApi instance;

    public static synchronized <T> T getInstance(Class<T> clz){
        if(instance == null){
            instance = new ResApi();
        }
        return instance.create(clz);
    }
    public static final boolean isDebug = true;

    public <T> T create(Class<T> clz){
        String base_url = "";
        try{
            Field field = clz.getField("BASE_URL");
            base_url = (String) field.get(clz);


        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return createApiClient(base_url).create(clz);
    }

    public Retrofit createApiClient(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createOkHttpClient(isDebug))
                .build();
    }

    public OkHttpClient createOkHttpClient(boolean debug){
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(debug? HttpLoggingInterceptor.Level.BODY: HttpLoggingInterceptor.Level.NONE))
                .build();
    }
}
