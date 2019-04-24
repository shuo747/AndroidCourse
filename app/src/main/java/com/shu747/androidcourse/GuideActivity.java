package com.shu747.androidcourse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shu747.androidcourse.activity.Homework2Activity;
import com.shu747.androidcourse.activity.homework1Activity;
import com.shu747.androidcourse.model.BasicData;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_guide_name;
    private Button bt_guide_1;
    private Button bt_guide_2;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
    }

    private void initData() {
        BasicData basicData = LitePal.findLast(BasicData.class);
        if(basicData!=null)
            et_guide_name.setText(basicData.getName());
    }

    private void initView() {
        et_guide_name = (EditText) findViewById(R.id.et_guide_name);
        bt_guide_1 = (Button) findViewById(R.id.bt_guide_1);
        bt_guide_1.setOnClickListener(this);
        bt_guide_2 = (Button) findViewById(R.id.bt_guide_2);
        bt_guide_2.setOnClickListener(this);
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
        }
    }

    private void startA(Class activity) {
        if(submit())
            startActivity(new Intent(context,activity));
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
