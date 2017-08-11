package com.ryan.rxretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.ryan.rxretrofit.model.DemoServiceManager;
import com.ryan.rxretrofit.model.Member;
import com.ryan.rxretrofit.model.MemberList;
import com.ryan.rxretrofit.model.TokenResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class MemberListActivity extends AppCompatActivity {

    @BindView(R.id.tv_memberlist) TextView tv_memberlist;
    @BindView(R.id.tv_token) TextView tv_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        ButterKnife.bind(this);
        tv_token.setText(Constant.tr.token.tokenString);
    }

    @OnClick(R.id.btn_settokenfailed)
    public void setTokenFailed() {
        Constant.tr.token.tokenString = "123";
        Constant.tokenTime = Constant.tokenTime - Constant.expiredTime;
        tv_token.setText("");
        tv_memberlist.setText("");

    }

    // if use Oauth, can use Interceptor renew token
    @OnClick(R.id.btn_getmemberlist)
    public void getMemberList() {
        Long nowTime = System.currentTimeMillis();
        if(nowTime - Constant.tokenTime > Constant.expiredTime) {
            reLoginAndGetList();
        } else {
            goGetList();
        }
    }

    private void reLoginAndGetList() {
        JsonObject jo = new JsonObject();
        jo.addProperty("name", "ken");
        jo.addProperty("pwd", "hello");
        new DemoServiceManager().rxLogin(jo.toString())
                .flatMap(new Function<TokenResponse, ObservableSource<MemberList>>() {
                    @Override
                    public ObservableSource<MemberList> apply(@NonNull TokenResponse tokenResponse) throws Exception {
                        Log.e("ReGet", tokenResponse.token.name);
                        Log.e("ReGet", tokenResponse.token.tokenString);
                        Constant.tr = tokenResponse;
                        Constant.tokenTime = System.currentTimeMillis();
                        tv_token.setText(Constant.tr.token.tokenString);
                        return new DemoServiceManager().rxGetMemberList(Constant.tr.token.tokenString);
                    }
                })
                .map(memberList -> {
                    String memberString = "";
                    for(Member member : memberList.list){
                        memberString += member.id + member.name + "\n";
                    }
                    return memberString;
                })
                .subscribe(memberString -> tv_memberlist.setText(memberString));
    }

    private void goGetList() {
        new DemoServiceManager().rxGetMemberList(Constant.tr.token.tokenString)
                .map(memberList -> {
                    String memberString = "";
                    for(Member member : memberList.list){
                        memberString += member.id + member.name + "\n";
                    }
                    return memberString;
                })
                .subscribe(memberString -> tv_memberlist.setText(memberString));
    }

}
