package com.ryan.rxretrofit.model;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Ryan on 2017/8/9.
 */

public interface DemoAPIInterface {

    @Headers("Content-Type: application/json")
    @POST("/")
    Observable<TokenResponse> rxLogin(@Body String body);

    @GET("/member")
    Observable<MemberList> rxGetMemberList(@Header("Authorization") String token);

}
