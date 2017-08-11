package com.ryan.rxretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.ryan.rxretrofit.model.DemoServiceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name) EditText et_name;
    @BindView(R.id.password) EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        goLogin();
    }

    private void goLogin() {
        JsonObject jo = new JsonObject();
        jo.addProperty("name", "ken");
        jo.addProperty("pwd", "hello");
        new DemoServiceManager().rxLogin(jo.toString())
                .subscribe((tokenResponse) -> {
                    Log.e("MyInfo", tokenResponse.token.name);
                    Log.e("MyInfo", tokenResponse.token.tokenString);
                    Constant.tr = tokenResponse;
                    Constant.tokenTime = System.currentTimeMillis();
                    Intent i = new Intent(MainActivity.this, MemberListActivity.class);
                    startActivity(i);
                });
    }

    @OnClick(R.id.btn_layout)
    public void onLayoutClick() {
        Intent i = new Intent(MainActivity.this, LayoutActivity.class);
        startActivity(i);
    }

}
