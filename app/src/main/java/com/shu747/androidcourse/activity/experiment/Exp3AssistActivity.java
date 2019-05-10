package com.shu747.androidcourse.activity.experiment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.activity.MyActivity;
import com.shu747.androidcourse.activity.experiment.util.ActivityCollector;

public class Exp3AssistActivity extends MyActivity implements View.OnClickListener {

    private TextView tv_exp3_data;
    private Button bt_exp3_force_offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp3_assist);
        ActivityCollector.addActivity(this);
        initView();
        Bundle extras = getIntent().getExtras();
        StringBuilder sb = new StringBuilder();
        sb.append("用户名："+extras.getString("username")+"\n");
        sb.append("密码："+extras.getString("password")+"\n");
        tv_exp3_data.setText(sb.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private void initView() {
        tv_exp3_data = (TextView) findViewById(R.id.tv_exp3_data);
        bt_exp3_force_offline = (Button) findViewById(R.id.bt_exp3_force_offline);

        bt_exp3_force_offline.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_exp3_force_offline:
                Intent intent=new Intent("com.shuo747.AndroidCourse.exp3.FORCE_OFFLINE");//发送广播，在广播接收器里实现强制下线的逻辑
                sendBroadcast(intent);
                break;
        }
    }
}
