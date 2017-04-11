package org.icot.icotpad.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import org.icot.icotpad.R;
import org.icot.icotpad.UserSharedPreferences;
import org.icot.icotpad.callback.IcarHttpRequestCallBack;
import org.icot.icotpad.utils.API;
import org.icot.icotpad.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.OkHttpClient;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ib_more)
    ImageButton ibMore;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_password)
    EditText etPassword;

    private String mobile,password;
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.ib_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.ib_more)
    public void more() {
        toast("更多设置");
    }

    @OnClick(R.id.btn_login)
    public void login() {

        mobile = etMobile.getText().toString();
        password = etPassword.getText().toString();
        if(TextUtils.isEmpty(mobile)||TextUtils.isEmpty(password)){
            toast("手机号或密码不能为空");
            showWaitDialog(false);
            return;
        }
        showWaitDialog(true);
        RequestParams params = new RequestParams();
        params.addFormDataPart("mobile",mobile);
        params.addFormDataPart("password",password);
       HttpRequest.post(Constant.getHost()+ API.PAD_LOGIN_URL,params,new IcarHttpRequestCallBack(){
           @Override
           protected void onDataSuccess(JSONObject data) {
               super.onDataSuccess(data);
               toast( "登录成功"+"   "+data.getString("token"));
               String token = data.getString("token");
               UserSharedPreferences.getInstance().setJwtToken(token);
               UserSharedPreferences.getInstance().setPhoneAndPassword(mobile, password);
               OkHttpFinal.getInstance().updateCommonHeader("Authorization", "Bearer " + token);
               startActivity(new Intent(LoginActivity.this, DefineActivity.class));
               LoginActivity.this.finish();
               showWaitDialog(false);
           }

           @Override
           protected void onDataError(int status, JSONObject statusInfo) {
               super.onDataError(status, statusInfo);
           }
       });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
