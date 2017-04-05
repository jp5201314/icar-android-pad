package org.icot.icotpad;

import android.app.Application;
import android.content.Context;

import org.greenrobot.eventbus.EventBus;

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

        EventBus.getDefault().register(mContext);
        initOkHttpFinal();
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

    private void initOkHttpFinal(){
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }
}
