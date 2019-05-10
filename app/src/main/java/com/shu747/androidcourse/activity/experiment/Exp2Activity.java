package com.shu747.androidcourse.activity.experiment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.activity.Homework3AssistActivity;
import com.shu747.androidcourse.activity.MyActivity;

public class Exp2Activity extends MyActivity implements View.OnClickListener {

    private EditText et_exp2_data;
    private Button bt_exp2_implicit;
    private Button bt_exp2_explicit;
    private TextView tv_exp2_data;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp2);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if(extras == null) return;
        String data = extras.getString("data");
        String ori = tv_exp2_data.getText().toString()+"\n";
        tv_exp2_data.setText(ori+data);
    }

    private void initView() {
        et_exp2_data = (EditText) findViewById(R.id.et_exp2_data);
        bt_exp2_implicit = (Button) findViewById(R.id.bt_exp2_implicit);
        bt_exp2_explicit = (Button) findViewById(R.id.bt_exp2_explicit);
        tv_exp2_data = (TextView) findViewById(R.id.tv_exp2_data);

        bt_exp2_implicit.setOnClickListener(this);
        bt_exp2_explicit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_exp2_implicit:
                Intent intent = new Intent("com.shuo747.AndroidCourse.exp2assist.ACTION");
                startActivity(intent);
                break;
            case R.id.bt_exp2_explicit:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String data = et_exp2_data.getText().toString().trim();
        if (TextUtils.isEmpty(data)) {
            Toast.makeText(this, "填写传递的数据", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        Intent intent = new Intent(context, Exp2AssistActivity.class);
        intent.putExtra("data",data);
        context.startActivity(intent);

    }
}
