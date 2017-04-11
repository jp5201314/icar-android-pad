package org.icot.icotpad.utils;

import android.content.Context;
import android.text.TextUtils;

import org.icot.icotpad.IcarApplication;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 11608 on 2017/4/5.
 */

public class ACache {

    private Context context;
    private static ACache aCache;
    private File cacheDir;

    private ACache(Context context) {
        this.context = context;
        cacheDir = context.getCacheDir();
    }

    public static synchronized ACache getInstance() {
        if(aCache==null){
            aCache = new ACache(IcarApplication.getContext());
        }
        return aCache;
    }

    public void put(String key, String jwtToken) {
        new ACacheManager().putJwtTokenToCache(cacheDir, key, jwtToken);
    }

    public String get(String key){return new ACacheManager().getJwtTokenFormCache(key);}


    public void remove(String key){
        File file = getFile(key);
        if (file.exists()){
            file.delete();
        }
    }

    class ACacheManager {

        private void putJwtTokenToCache(File cacheDir, String key, String jwtToken) {
            File file = aCache.newFile(cacheDir, key);
            BufferedOutputStream bufferedOutputStream = null;
            if (!TextUtils.isEmpty(jwtToken)) {
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 1024);
                    bufferedOutputStream.write(jwtToken.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }

        private String getJwtTokenFormCache(String key) {
            File file = getFile(key);
            BufferedReader bis = null;
            StringBuilder sb = null;
            try {
                bis = new BufferedReader(new FileReader(file), 1024);
                byte[] buffer = new byte[1024];
                String str = null;
                while ((str = bis.readLine()) != null) {
                    sb.append(str);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }
    }

    private File getFile(String key) {
        return new File(cacheDir, key.hashCode() + "");
    }

    private File newFile(File cacheDir, String key) {
        if (cacheDir.exists() && !TextUtils.isEmpty(key)) {
            return new File(cacheDir, key.hashCode() + "");
        }
        return null;
    }
}
