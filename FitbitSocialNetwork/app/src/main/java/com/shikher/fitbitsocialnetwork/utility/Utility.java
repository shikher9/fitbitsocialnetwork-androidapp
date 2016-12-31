package com.shikher.fitbitsocialnetwork.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shikh on 23-Dec-16.
 */

public class Utility {

    public static Retrofit retrofit;
    public static final String APP_URL = "http://fitbitsocialnetwork-shikherpandey.rhcloud.com/app/";
    public static final String APP_SP = "com.shikher.fitbitsocialnetwork";


    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(Utility.APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public static void setImageLoaderConfiguration(Context context) {
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

    public static ImageLoader getImageLoader(Context context) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        return imageLoader;
    }

    public static void showLongToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static void showShortToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static SharedPreferences.Editor getSharedPreferencesEditor(Context context) {
        SharedPreferences settings = context.getSharedPreferences(APP_SP, 0);
        SharedPreferences.Editor editor = settings.edit();
        return editor;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SP, 0);
        return sharedPreferences;
    }

}
