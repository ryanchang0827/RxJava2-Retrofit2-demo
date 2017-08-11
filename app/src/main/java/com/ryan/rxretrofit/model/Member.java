package com.ryan.rxretrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ryan on 2017/8/9.
 */

public class Member {
    @SerializedName("ID")
    public int id;
    @SerializedName("name")
    public String name;
}
