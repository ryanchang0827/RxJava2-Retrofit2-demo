package com.ryan.rxretrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ryan on 2017/8/9.
 */

public class Token {
    @SerializedName("name")
    public String name;
    @SerializedName("iat")
    public double iat;
    @SerializedName("exp")
    public double exp;
    @SerializedName("token")
    public String tokenString;
}
