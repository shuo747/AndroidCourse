package com.shu747.androidcourse.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.model.BasicData;

import org.litepal.LitePal;

public class homework1Activity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework1);
        BasicData basicData = LitePal.findLast(BasicData.class);
        if(basicData!=null)
            setTitle(basicData.getName());
    }
}
