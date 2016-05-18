package com.nickming.cloudcontact.application;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/17
 * Time:13:57
 * E-mail:962570483@qq.com
 */
public class MyApplication extends Application{

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, "74a78cff3f8260ef99bc5bfc33964457");
        mContext=getApplicationContext();
    }

    public static Context getmContext() {
        return mContext;
    }
}
