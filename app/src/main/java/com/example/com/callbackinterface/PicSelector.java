package com.example.com.callbackinterface;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Uzibaba on 05-08-2017.
 */

public class PicSelector {

    @Retention(RetentionPolicy.SOURCE)//declare retention policy source i.e complile time
    @StringDef({NORMAL_USER, FACEBOOK_USER,GOOGLE_USER})

    //Declare interface which we gonna use as our custom annotation
    public @interface UserMode {}

    // Must be static final int values for intDef
    public static final String NORMAL_USER = "normal";
    public static final String FACEBOOK_USER = "facebook";
    public static final String GOOGLE_USER = "google";

}
