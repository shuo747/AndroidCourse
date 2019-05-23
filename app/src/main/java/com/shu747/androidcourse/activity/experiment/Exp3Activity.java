package com.shu747.androidcourse.activity.experiment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.activity.MyActivity;

public class Exp3Activity extends MyActivity implements View.OnClickListener {

    private EditText et_exp3_username;
    private EditText et_exp3_password;
    private Button bt_exp3_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp3);
        initView();
    }

    private void initView() {
        et_exp3_username = (EditText) findViewById(R.id.et_exam1_username);
        et_exp3_password = (EditText) findViewById(R.id.et_exam1_password);
        bt_exp3_login = (Button) findViewById(R.id.bt_exam1_login);

        et_exp3_username.setText("test");
        et_exp3_password.setText("test");
        bt_exp3_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_exam1_login:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String username = et_exp3_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_exp3_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        Intent intent = new Intent(this, Exp3AssistActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        this.startActivity(intent);


    }
}
