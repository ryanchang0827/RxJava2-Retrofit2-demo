package com.ryan.rxretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 2017/8/9.
 */

public class MemberList {
    @SerializedName("data")
    public List<Member> list = new ArrayList<>();
}
