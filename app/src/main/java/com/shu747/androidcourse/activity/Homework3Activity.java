package com.shu747.androidcourse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.model.BasicData;
import com.tencent.stat.StatService;

import org.litepal.LitePal;

public class Homework3Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_homework3_username;
    private EditText et_homework3_password;
    private RadioGroup rg_homework3;
    private Button bt_homework3_login;
    private Button bt_homework3_cancel;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework3);
        initView();
        BasicData basicData = LitePal.findLast(BasicData.class);
        if (basicData != null)
            setTitle(basicData.getName());
    }

    private void initView() {
        et_homework3_username = (EditText) findViewById(R.id.et_homework3_username);
        et_homework3_password = (EditText) findViewById(R.id.et_homework3_password);
        rg_homework3 = (RadioGroup) findViewById(R.id.rg_homework3);
        bt_homework3_login = (Button) findViewById(R.id.bt_homework3_login);
        bt_homework3_cancel = (Button) findViewById(R.id.bt_homework3_cancel);

        bt_homework3_login.setOnClickListener(this);
        bt_homework3_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_homework3_login:
                submit();
                break;
            case R.id.bt_homework3_cancel:
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String username = et_homework3_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_homework3_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = rg_homework3.getCheckedRadioButtonId();
        String gender = "男";
        if(id==1) gender = "女";

        // TODO validate success, do something


        Intent intent = new Intent(context, Homework3AssistActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        intent.putExtra("gender",gender);
        context.startActivity(intent);


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
