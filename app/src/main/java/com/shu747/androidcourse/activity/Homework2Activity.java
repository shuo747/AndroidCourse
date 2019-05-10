package com.shu747.androidcourse.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.model.BasicData;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class Homework2Activity extends MyActivity implements View.OnClickListener {

    private Button bt_homework2_add;
    private RecyclerView rv_homework2_main;
    List<Double> list;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2);
        initView();
        BasicData basicData = LitePal.findLast(BasicData.class);
        if (basicData != null)
            setTitle(basicData.getName());


    }

    private void initView() {
        bt_homework2_add = (Button) findViewById(R.id.bt_homework2_add);
        rv_homework2_main = (RecyclerView) findViewById(R.id.rv_homework2_main);
        rv_homework2_main.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        int n = (int) (Math.random()*10);
        while (n-->0) list.add(Math.random());
        myAdapter = new MyAdapter(list);
        rv_homework2_main.setAdapter(myAdapter);

        bt_homework2_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_homework2_add:

                list.add(Math.random());
                myAdapter.notifyDataSetChanged();
                break;
        }
    }


    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        public List list;
        public MyAdapter(List list) {
            this.list = list;
        }
        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_homework2_rv,viewGroup, false );
            ViewHolder vh =  new ViewHolder(view);
            return  vh;
        }
        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setText(list.get(position).toString());
        }
        //获取数据的数量
        @Override
        public int getItemCount() {
            return  list.size();
        }
        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(View view){
                super (view);
                mTextView = (TextView) view.findViewById(R.id.item_tv);
            }
        }
    }

}
