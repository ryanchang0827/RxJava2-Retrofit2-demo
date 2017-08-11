package com.ryan.rxretrofit.model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ryan on 2017/8/9.
 */

public class DemoServiceManager {

    private DemoService mService;


    public DemoServiceManager() {
        mService = DemoService.getInstance();
    }

    public Observable<TokenResponse> rxLogin(String body){
        return mService.rxLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MemberList> rxGetMemberList(String token) {
        return mService.rxGetMemberList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }





}
