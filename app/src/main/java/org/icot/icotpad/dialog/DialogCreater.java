package org.icot.icotpad.dialog;

import android.content.Context;
import android.os.Build;

/**
 * Created by 11608 on 2017/4/10.
 */

public class DialogCreater {

    public static WaitDialog createWaitDialog(Context context, String string){
        WaitDialog waitDialog = new WaitDialog(context);
        waitDialog.setTitle(string);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            waitDialog.create();
        }
        return waitDialog;
    }
}
