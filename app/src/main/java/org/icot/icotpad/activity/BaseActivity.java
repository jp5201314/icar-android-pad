package org.icot.icotpad.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.icot.icotpad.dialog.DialogCreater;
import org.icot.icotpad.dialog.WaitDialog;

/**
 * Created by 11608 on 2017/4/10.
 */

public class BaseActivity extends AppCompatActivity {

    WaitDialog waitDialog;


    public void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void showWaitDialog(boolean flag){
        showWaitDialog(flag,"请稍后");
    }

    protected void showWaitDialog(boolean show ,String msg){
        waitDialog = DialogCreater.createWaitDialog(BaseActivity.this,msg);
        if(!show){
           waitDialog.dismiss();
        }
        waitDialog.show();
    }
}
