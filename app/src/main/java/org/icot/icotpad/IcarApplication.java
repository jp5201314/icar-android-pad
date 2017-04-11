package org.icot.icotpad;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.icot.icotpad.event.UnLoginEvent;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * Created by 11608 on 2017/4/5.
 */
public class IcarApplication extends Application {

    private static  IcarApplication CURRENTOBJECT;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        CURRENTOBJECT = this;
        initOkHttpFinal();
        EventBus.getDefault().register(mContext);
    }
    public static synchronized Context getContext() {
        return mContext;
    }

    public synchronized IcarApplication getInstance(){
        return CURRENTOBJECT;
    }

    public synchronized  Context getmContext(){
        return mContext;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        EventBus.getDefault().unregister(mContext);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnLoginedEvent(UnLoginEvent event){

    }



    private void initOkHttpFinal(){
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        builder.setDebug(true);
        OkHttpFinal.getInstance().init(builder.build());

        OkHttpFinal.getInstance().updateCommonHeader("Accept","Application/json");

        String jwtToken = UserSharedPreferences.getInstance().getJwtToken();
        if(!TextUtils.isEmpty(jwtToken)){
            OkHttpFinal.getInstance().updateCommonHeader("Authorization","Bearer"+jwtToken);
        }
    }
}
