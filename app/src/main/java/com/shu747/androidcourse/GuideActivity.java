package com.shu747.androidcourse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shu747.androidcourse.activity.Exam1Activity;
import com.shu747.androidcourse.activity.Homework2Activity;
import com.shu747.androidcourse.activity.Homework3Activity;
import com.shu747.androidcourse.activity.Homework4Activity;
import com.shu747.androidcourse.activity.experiment.Exp1Activity;
import com.shu747.androidcourse.activity.experiment.Exp2Activity;
import com.shu747.androidcourse.activity.experiment.Exp3Activity;
import com.shu747.androidcourse.activity.experiment.Exp4Activity;
import com.shu747.androidcourse.activity.homework1Activity;
import com.shu747.androidcourse.model.BasicData;
import com.tencent.stat.StatService;

import org.litepal.LitePal;

import java.util.Properties;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_guide_name;
    private Button bt_guide_1;
    private Button bt_guide_2;
    private Button bt_guide_3;
    private Context context = this;
    private Button bt_guide_4;
    private Button bt_guide_5;
    private Button bt_guide_6;
    private Button bt_guide_7;
    private Button bt_guide_8;
    private Button bt_guide_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
    }

    private void initData() {
        BasicData basicData = LitePal.findLast(BasicData.class);
        if (basicData != null)
            et_guide_name.setText(basicData.getName());
    }

    private void initView() {
        et_guide_name = (EditText) findViewById(R.id.et_guide_name);
        bt_guide_1 = (Button) findViewById(R.id.bt_guide_1);
        bt_guide_1.setOnClickListener(this);
        bt_guide_2 = (Button) findViewById(R.id.bt_guide_2);
        bt_guide_2.setOnClickListener(this);
        bt_guide_3 = (Button) findViewById(R.id.bt_guide_3);
        bt_guide_3.setOnClickListener(this);
        bt_guide_4 = (Button) findViewById(R.id.bt_guide_4);
        bt_guide_4.setOnClickListener(this);
        bt_guide_5 = (Button) findViewById(R.id.bt_guide_5);
        bt_guide_5.setOnClickListener(this);
        bt_guide_6 = (Button) findViewById(R.id.bt_guide_6);
        bt_guide_6.setOnClickListener(this);
        bt_guide_7 = (Button) findViewById(R.id.bt_guide_7);
        bt_guide_7.setOnClickListener(this);
        bt_guide_8 = (Button) findViewById(R.id.bt_guide_8);
        bt_guide_8.setOnClickListener(this);
        bt_guide_9 = (Button) findViewById(R.id.bt_guide_9);
        bt_guide_9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        submit();
        switch (v.getId()) {
            case R.id.bt_guide_1:
                startA(homework1Activity.class);
                break;
            case R.id.bt_guide_2:
                startA(Homework2Activity.class);
                break;
            case R.id.bt_guide_3:
                startA(Homework3Activity.class);
            case R.id.bt_guide_4:
                startA(Exp1Activity.class);
                break;
            case R.id.bt_guide_5:
                startA(Exp2Activity.class);
                break;
            case R.id.bt_guide_6:
                startA(Exp3Activity.class);
                break;
            case R.id.bt_guide_7:
                startA(Exp4Activity.class);
                break;
            case R.id.bt_guide_8:
                startA(Homework4Activity.class);
                break;
            case R.id.bt_guide_9:
                startA(Exam1Activity.class);
                break;
        }
    }

    private void startA(Class activity) {
        if (submit()) {
            Properties prop = new Properties();
            prop.setProperty("userinfo", et_guide_name.getText().toString().trim());
            prop.setProperty("activity", activity.getName());
            StatService.trackCustomKVEvent(this, "enter", prop);
            startActivity(new Intent(context, activity));
        }

    }

    private boolean submit() {
        // validate
        String name = et_guide_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        BasicData basicData = new BasicData(name);
        basicData.saveOrUpdate();
        return true;

        // TODO validate success, do something


    }
}
