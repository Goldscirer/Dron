package com.example.seweryn.dronapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 06.04.18.
 */

public class Topics
{
    /** */
    public static final String GET = "DRON/CONFIG/ANDROID/GET";
    public static final String POST = "DRON/CONFIG/ANDROID/POST";
    public static final String UPDATE = "DRON/CONFIG/ANDROID/UPDATE";
    public static final String REMOTE = "DRON/PILOT";
    /** */
    public static final String abc = "abc";

    /** */
    public static final List<String> SUBSCRIBE = new ArrayList<>();

    static {
        Log.d("tag", "added");
        SUBSCRIBE.add(POST);
        SUBSCRIBE.add(abc);
        Log.d("tag", "--");
        for (String s : SUBSCRIBE) {
            Log.d("tag", s);
        }
        Log.d("tag", "end");
    }
}
