package com.shu747.androidcourse;

import android.app.Application;
import android.content.Context;

import com.tencent.stat.StatService;

import org.litepal.LitePal;

public class MyApplication extends Application {
    private Context context = this;
    @Override
    public void onCreate() {
        super.onCreate();
        // 基础统计API
        StatService.registerActivityLifecycleCallbacks(this);
        LitePal.initialize(this);
    }
}
