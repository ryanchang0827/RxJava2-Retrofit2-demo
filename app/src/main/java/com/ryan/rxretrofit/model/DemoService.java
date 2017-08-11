package com.ryan.rxretrofit.model;


import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;

/**
 * Created by Ryan on 2017/8/9.
 */

public class DemoService {
    public static String API_URL = "http://52.197.192.141:3443";

    private DemoAPIInterface mDemoAPI;

    public DemoService(DemoAPIInterface demoAPI) {
        mDemoAPI = demoAPI;
    }

    public static DemoService getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return new DemoService(retrofit.create(DemoAPIInterface.class));
    }

    public Observable<TokenResponse> rxLogin(@Body String body) {
        return mDemoAPI.rxLogin(body);
    }

    public Observable<MemberList> rxGetMemberList(@Header("Authorization") String token) {
        return mDemoAPI.rxGetMemberList(token);
    }




}
