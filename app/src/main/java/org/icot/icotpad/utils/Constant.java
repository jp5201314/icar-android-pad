package org.icot.icotpad.utils;

import org.icot.icotpad.BuildConfig;

/**
 * Created by 11608 on 2017/4/6.
 */

public class Constant {

    //正式版的主机地址
    public static final String REQUEST_HOST = "https://api.woshichezhu.com/";
    //测试版的主机地址
    public static final String REQUEST_HOST_FOR_TEST = "https://dev.api.woshichezhu.com/";
    //
    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }
    //获取主机地址
    public static String getHost() {
        if (isDebug()) {
            return REQUEST_HOST_FOR_TEST;
        } else {
            return REQUEST_HOST;
        }
    }
}
