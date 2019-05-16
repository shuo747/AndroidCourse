package com.shu747.androidcourse.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shu747.androidcourse.MyApplication;
import com.shu747.androidcourse.R;
import com.shu747.androidcourse.model.Matter;

import org.litepal.LitePal;

import java.util.List;

public class Homework4Activity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_homework4_back;
    private Button bt_homework4_finish;
    private EditText et_homework4_title_add;
    private EditText et_homework4_content_add;
    private LinearLayout ll_homework4_1;
    private RecyclerView rv_homework4;
    private LinearLayout ll_homework4_2;
    private Button bt_homework4_add;
    private Context context = this;
    MyAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework4);
        initView();
        initData();
    }

    private void initData() {
        List<Matter> matters = LitePal.findAll(Matter.class);
        myAdapter = new MyAdapter(matters);
        rv_homework4.setLayoutManager(new LinearLayoutManager(context));
        rv_homework4.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv_homework4.setAdapter(myAdapter);
    }

    private void updateData() {
        List<Matter> matters = LitePal.findAll(Matter.class);
        myAdapter.list = matters;
        myAdapter.notifyDataSetChanged();
    }

    private void initView() {
        bt_homework4_back = (Button) findViewById(R.id.bt_homework4_back);
        bt_homework4_finish = (Button) findViewById(R.id.bt_homework4_finish);
        et_homework4_title_add = (EditText) findViewById(R.id.et_homework4_title_add);
        et_homework4_content_add = (EditText) findViewById(R.id.et_homework4_content_add);
        ll_homework4_1 = (LinearLayout) findViewById(R.id.ll_homework4_1);
        rv_homework4 = (RecyclerView) findViewById(R.id.rv_homework4);
        ll_homework4_2 = (LinearLayout) findViewById(R.id.ll_homework4_2);
        bt_homework4_add = (Button) findViewById(R.id.bt_homework4_add);

        bt_homework4_back.setOnClickListener(this);
        bt_homework4_finish.setOnClickListener(this);
        bt_homework4_add.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_homework4_back:
                hideLL1();
                break;
            case R.id.bt_homework4_finish:
                submit();
                break;
            case R.id.bt_homework4_add:
                ll_homework4_1.setVisibility(View.VISIBLE);
                ll_homework4_2.setVisibility(View.GONE);
                bt_homework4_add.setVisibility(View.GONE);
                break;
        }
    }

    private void hideLL1() {
        ll_homework4_1.setVisibility(View.GONE);
        ll_homework4_2.setVisibility(View.VISIBLE);
        bt_homework4_add.setVisibility(View.VISIBLE);
    }

    private void submit() {
        // validate
        String title = et_homework4_title_add.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
            return;
        }

        String content = et_homework4_content_add.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入详细内容", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        Matter matter = new Matter(title,content);
        matter.save();
        Toast.makeText(Homework4Activity.this, "新建"+title, Toast.LENGTH_SHORT).show();


        hideLL1();
        updateData();


    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        public List<Matter> list;
        public MyAdapter my;
        public MyAdapter(List list) {
            this.list = list;
            my = this;
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
            viewHolder.mTextView.setText(list.get(position).getTitle());
            final int tempPos = position;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(context);
                    normalDialog.setTitle(list.get(tempPos).getTitle())
                            .setMessage(list.get(tempPos).getContent());
                    // 创建实例并显示
                    normalDialog.show();
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(context);
                    normalDialog.setTitle("提示")
                            .setMessage("确定删除 "+list.get(tempPos).getTitle());
                    normalDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LitePal.delete(Matter.class,list.get(tempPos).getId());
                                    list.remove(tempPos);
                                    my.notifyDataSetChanged();
                                }
                            });
                    normalDialog.setNegativeButton("关闭",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //...To-do
                                }
                            });
                    normalDialog.show();
                    //Toast.makeText(context, list.get(tempPos).getId()+"", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
        //获取数据的数量
        @Override
        public int getItemCount() {
            return  list.size();
        }
        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public  class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(View view){
                super (view);
                mTextView = (TextView) view.findViewById(R.id.item_tv);

            }
        }
    }
}
