package com.shu747.androidcourse.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.model.User;

import org.litepal.LitePal;

import java.util.List;

public class Exam1Activity extends MyActivity implements View.OnClickListener {

    private TextView tv_exam1_username;
    private EditText et_exam1_username;
    private TextView tv_exam1_password;
    private EditText et_exam1_password;
    private Button bt_exam1_login;
    private Button bt_exam1_regist;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam1);
        initView();
    }

    private void initView() {
        tv_exam1_username = (TextView) findViewById(R.id.tv_exam1_username);
        et_exam1_username = (EditText) findViewById(R.id.et_exam1_username);
        tv_exam1_password = (TextView) findViewById(R.id.tv_exam1_password);
        et_exam1_password = (EditText) findViewById(R.id.et_exam1_password);
        bt_exam1_login = (Button) findViewById(R.id.bt_exam1_login);
        bt_exam1_regist = (Button) findViewById(R.id.bt_exam1_regist);

        bt_exam1_login.setOnClickListener(this);
        bt_exam1_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_exam1_login:
                submit();
                break;
            case R.id.bt_exam1_regist:
                submit2();
                break;
        }
    }

    private void submit() {
        // validate
        String username = et_exam1_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_exam1_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }



        // TODO validate success, do something

        List<User> res = LitePal.select("username","password")
                .where("username = ?", username)
                .where("password = ?", password)
                .find(User.class);

        if(res.size() > 0){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("用户名：")
                    .append(username)
                    .append("\n密码：")
                    .append(password);
            AlertDialog.Builder normalDialog =
                    new AlertDialog.Builder(context);
            normalDialog.setTitle("登录成功")
                    .setMessage(stringBuffer);
            // 创建实例并显示
            normalDialog.show();
            return;
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("用户名：")
                .append(username)
                .append("\n密码：")
                .append(password);
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setTitle("登录失败")
                .setMessage(stringBuffer);
        // 创建实例并显示
        normalDialog.show();

    }


    private void submit2() {
        // validate
        String username = et_exam1_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_exam1_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


        List<User> res = LitePal.select("username","password")
                .where("username = ?", username)
                .find(User.class);


        if(res.size() > 0){
            Toast.makeText(this, "用户名"+res.get(0).getUsername()+"已存在", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(username,password);
        user.save();
        Toast.makeText(this, "注册完成", Toast.LENGTH_SHORT).show();



    }
}
