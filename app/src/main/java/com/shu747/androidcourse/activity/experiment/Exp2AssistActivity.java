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

public class Exp2AssistActivity extends MyActivity implements View.OnClickListener {

    private TextView tv_exp2_show;
    private EditText et_exp2_data2;
    private Button bt_exp2_explicit2;
    private TextView tv_exp2_data2;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp2_assist);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if(extras == null) return;
        String data = extras.getString("data");
        String ori = tv_exp2_data2.getText().toString()+"\n";
        tv_exp2_data2.setText(ori+data);
    }

    private void initView() {
        tv_exp2_show = (TextView) findViewById(R.id.tv_exp2_show);
        et_exp2_data2 = (EditText) findViewById(R.id.et_exp2_data2);
        bt_exp2_explicit2 = (Button) findViewById(R.id.bt_exp2_explicit2);
        tv_exp2_data2 = (TextView) findViewById(R.id.tv_exp2_data2);

        bt_exp2_explicit2.setOnClickListener(this);
        StringBuffer sb = new StringBuffer();
        sb.append("说明：\n");
        sb.append("<action android:name=\"com.shuo747.AndroidCourse.exp2assist.ACTION\"></action>\n" +
                "<category android:name=\"android.intent.category.DEFAULT\"></category>");
        tv_exp2_show.setText(sb);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_exp2_explicit2:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String data2 = et_exp2_data2.getText().toString().trim();
        if (TextUtils.isEmpty(data2)) {
            Toast.makeText(this, "要传递的数据", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        Intent intent = new Intent(context, Exp2Activity.class);
        intent.putExtra("data",data2);
        context.startActivity(intent);

    }
}
