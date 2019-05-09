package com.shu747.androidcourse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.model.BasicData;
import com.tencent.stat.StatService;

import org.litepal.LitePal;

/**
 * @Author: shuo747
 * @Date: 2019/5/9 21:11
 */
public class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3);
        BasicData basicData = LitePal.findLast(BasicData.class);
        if (basicData != null)
            setTitle(basicData.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
    }
}
