package org.icot.icotpad;

import android.content.Context;
import android.content.SharedPreferences;

import org.icot.icotpad.utils.ACache;

/**
 * Created by 11608 on 2017/4/5.
 */

public class UserSharedPreferences {

    private static UserSharedPreferences userSharedPreferences;
    private String jwtToken;
    private Context mContext;
    private String key = "token";
    private SharedPreferences sharedPreferences;
    private String SHARED_PREFERENCES_NAME = "user";
    private SharedPreferences.Editor editor;

    private UserSharedPreferences(Context context) {
        this.mContext = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public synchronized static UserSharedPreferences getInstance() {
        if (userSharedPreferences==null){
            userSharedPreferences = new UserSharedPreferences(IcarApplication.getContext());
        }
        return userSharedPreferences;
    }

    public void setJwtToken(String jwtToken) {
        this.putJwtTokenToCache(jwtToken);
        this.putJwtTokenToSharedPreferences(jwtToken);
    }

    public void removeJwtToken() {
        removeJwtTokenFromCache();
        removeJwtTokenFromSharedferfences();
    }

    private void putJwtTokenToCache(String jwtToken) {
        ACache.getInstance().put(key, jwtToken);
    }

    private void putJwtTokenToSharedPreferences(String jwtToken) {
        editor.putString(key, jwtToken);
        editor.commit();
    }

    private void removeJwtTokenFromCache() {
        ACache.getInstance().remove(key);
    }

    private void removeJwtTokenFromSharedferfences() {
        editor.remove(key);
        editor.commit();
    }

    public String getJwtToken() {
        return ACache.getInstance().get(key);
    }

    public void setPhoneAndPassword(String mobile,String password){
        editor.putString("mobile",mobile);
        editor.putString("password",password);
        editor.commit();
    }
}
