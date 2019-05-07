package com.shu747.androidcourse.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.model.BasicData;

import org.litepal.LitePal;

public class Homework3AssistActivity extends AppCompatActivity {

    private TextView tv_homework3_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3_assist);
        initView();
        BasicData basicData = LitePal.findLast(BasicData.class);
        if (basicData != null)
            setTitle(basicData.getName());

        Bundle extras = getIntent().getExtras();
        StringBuilder sb = new StringBuilder();
        sb.append("用户名："+extras.getString("username")+"\n");
        sb.append("密码："+extras.getString("password")+"\n");
        sb.append("性别："+extras.getString("gender")+"\n");
        tv_homework3_info.setText(sb.toString());


    }

    private void initView() {
        tv_homework3_info = (TextView) findViewById(R.id.tv_homework3_info);
    }
}
