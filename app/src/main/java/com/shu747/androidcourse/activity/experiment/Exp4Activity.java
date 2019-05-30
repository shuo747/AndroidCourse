package com.shu747.androidcourse.activity.experiment;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.activity.MyActivity;

import java.util.ArrayList;
import java.util.List;

public class Exp4Activity extends MyActivity implements View.OnClickListener {

    private Button create_database;
    private Button add_data;
    private Button update_data;
    private Button delete_data;
    private Button query_data;
    private Button replace_data;
    private LinearLayout linearLayout;
    private TextView tv_exp4_database_log;

    private StringBuffer sbLog = new StringBuffer();

    private Context context = this;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private Button bt_exp4_dialog_insert;
    private EditText et_exp4_dialog_id;
    private EditText et_exp4_dialog_name;
    private EditText et_exp4_dialog_age;
    private EditText et_exp4_dialog_city;
    private RecyclerView rv_exp4_database;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp4);
        initView();
        mySQLiteOpenHelper = mySQLiteOpenHelper.getInstance(context);
    }

    private void initView() {
        create_database = (Button) findViewById(R.id.create_database);
        add_data = (Button) findViewById(R.id.add_data);
        update_data = (Button) findViewById(R.id.update_data);
        delete_data = (Button) findViewById(R.id.delete_data);
        query_data = (Button) findViewById(R.id.query_data);
        replace_data = (Button) findViewById(R.id.replace_data);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        tv_exp4_database_log = (TextView) findViewById(R.id.tv_exp4_database_log);
        tv_exp4_database_log.setMovementMethod(ScrollingMovementMethod.getInstance());

        create_database.setOnClickListener(this);
        add_data.setOnClickListener(this);
        update_data.setOnClickListener(this);
        delete_data.setOnClickListener(this);
        query_data.setOnClickListener(this);
        replace_data.setOnClickListener(this);


        rv_exp4_database = (RecyclerView) findViewById(R.id.rv_exp4_database);
        ArrayList<String>[] lists = new ArrayList[4];
        for(int i = 0; i<lists.length;i++) lists[i] = new ArrayList();
        myAdapter = new MyAdapter(lists);
        rv_exp4_database.setAdapter(myAdapter);
        rv_exp4_database.setNestedScrollingEnabled(false);
        rv_exp4_database.setLayoutManager(new LinearLayoutManager(context));
        rv_exp4_database.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        sbLog.append("log：\n");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_database:
                sbLog.append(MySQLiteOpenHelper.CREATE_STUDENT+"\n");
                break;
            case R.id.add_data:
                showLayoutDialog(1);
                break;
            case R.id.update_data:
                showLayoutDialog(2);
                break;
            case R.id.delete_data:
                showLayoutDialog(3);
                break;
            case R.id.query_data:
                myAdapter.lists = mySQLiteOpenHelper.queryStu();
                myAdapter.notifyDataSetChanged();
                sbLog.append("select * from Student\n");
                break;
            case R.id.replace_data:
                //if (!submit()) return;
                break;

        }

        tv_exp4_database_log.setText(sbLog);
    }


    private void showLayoutDialog(final int flag) {
        /**
         * flag:
         * 1 insert
         * 2 update
         * 3 delete
         *
         */
        String title = "insert";
        switch (flag) {
            case  1:
                title = "insert";
                break;
            case  2:
                title = "update";
                break;
            case  3:
                title = "delete";
                break;
        }

        //加载布局并初始化组件
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_layout_exp4_insert, null);
        et_exp4_dialog_id = (EditText) dialogView.findViewById(R.id.et_exp4_dialog_id);
        et_exp4_dialog_name = (EditText) dialogView.findViewById(R.id.et_exp4_dialog_name);
        et_exp4_dialog_age = (EditText) dialogView.findViewById(R.id.et_exp4_dialog_age);
        et_exp4_dialog_city = (EditText) dialogView.findViewById(R.id.et_exp4_dialog_city);
        bt_exp4_dialog_insert = (Button) dialogView.findViewById(R.id.bt_exp4_dialog_insert);

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setView(dialogView)
                .setPositiveButton(title, null)
                .create();

        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag) {
                    case  1:
                        if (!submit()) return;
                        break;
                    case  2:
                        if (!submit2()) return;
                        break;
                    case  3:
                        if (!submit3()) return;
                        break;
                }



                tv_exp4_database_log.setText(sbLog);
                dialog.dismiss();
            }
        });


    }


    private boolean submit() {
        // validate
        String id = et_exp4_dialog_id.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "输入id", Toast.LENGTH_SHORT).show();
            return false;
        }

        String name = et_exp4_dialog_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "输入name", Toast.LENGTH_SHORT).show();
            return false;
        }

        String age = et_exp4_dialog_age.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "输入age", Toast.LENGTH_SHORT).show();
            return false;
        }

        String city = et_exp4_dialog_city.getText().toString().trim();
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(this, "输入city", Toast.LENGTH_SHORT).show();
            return false;
        }


        // TODO validate success, do something
        Log.e("))))))))", id + name + age + city);
        //Toast.makeText(Exp4Activity.this, id + name + age + city, Toast.LENGTH_SHORT).show();
        sbLog.append("insert into Student (id , name , age , city)"
                + "values ("
                + id + ","
                + name + ","
                + age + ","
                + city
                + ");\n");
        try {
            mySQLiteOpenHelper.insertStu(id, name, age, city);
        } catch (Exception e) {
            //e.printStackTrace();
            Toast.makeText(Exp4Activity.this, "insert failed", Toast.LENGTH_SHORT).show();
            return false;
        }
        //mySQLiteOpenHelper.insertStu("2","3","4","5");


        return true;
    }


    private boolean submit2() {
        // validate
        List<String> conditions = new ArrayList<>();
        List<String> realConditions = new ArrayList<>();
        String id = et_exp4_dialog_id.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "输入id", Toast.LENGTH_SHORT).show();
            return false;
        }

        String name = et_exp4_dialog_name.getText().toString().trim();
        String age = et_exp4_dialog_age.getText().toString().trim();
        String city = et_exp4_dialog_city.getText().toString().trim();
        // TODO validate success, do something
        Log.e("))))))))", id + name + age + city);

        conditions.add(name);
        conditions.add(age);
        conditions.add(city);


        for (int i = 0 ; i< conditions.size();++i){
            if(conditions.get(i).length() >0){
                realConditions.add(" , ");
                realConditions.add(MySQLiteOpenHelper.columns[i+1] + " = " + "'"+conditions.get(i)+"'");
            }
        }
        if(realConditions.size()<=0){
            Toast.makeText(Exp4Activity.this, "至少填写一项修改的值", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(realConditions.size()>0)realConditions.remove(0);

        StringBuffer sbConditions = new StringBuffer();
        for(String s : realConditions)
            sbConditions.append(s);
        sbConditions.append(" where id = "+id);
        //Toast.makeText(Exp4Activity.this, id + name + age + city, Toast.LENGTH_SHORT).show();
        Log.e("))-----)", sbConditions.toString());
        sbLog.append("update Student set "+sbConditions
                + "\n");
        try {
            mySQLiteOpenHelper.updateStu(sbConditions.toString());
        } catch (Exception e) {
            //e.printStackTrace();
            Toast.makeText(Exp4Activity.this, "update failed", Toast.LENGTH_SHORT).show();
            return false;
        }
        //mySQLiteOpenHelper.insertStu("2","3","4","5");


        return true;
    }


    private boolean submit3() {
        // validate
        List<String> conditions = new ArrayList<>();
        List<String> realConditions = new ArrayList<>();
        String id = et_exp4_dialog_id.getText().toString().trim();
        String name = et_exp4_dialog_name.getText().toString().trim();
        String age = et_exp4_dialog_age.getText().toString().trim();
        String city = et_exp4_dialog_city.getText().toString().trim();

        conditions.add(id);
        conditions.add(name);
        conditions.add(age);
        conditions.add(city);


        for (int i = 0 ; i< conditions.size();++i){
            if(conditions.get(i).length() >0){
                realConditions.add("and");
                realConditions.add("where "+MySQLiteOpenHelper.columns[i] + " = " + "'"+conditions.get(i)+"'");
            }
        }

        if(realConditions.size()>0)realConditions.remove(0);


        StringBuffer sbConditions = new StringBuffer();
        for(String s : realConditions)
            sbConditions.append(s);
        // TODO validate success, do something
        Log.e("))))))))", id + name + age + city);
        Log.e("))))))))", sbConditions.toString());
        //Toast.makeText(Exp4Activity.this, id + name + age + city, Toast.LENGTH_SHORT).show();


        sbLog.append("delete from Student "
                +sbConditions+"\n");
        try {
            mySQLiteOpenHelper.deleteStu(sbConditions.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Exp4Activity.this, "delete failed", Toast.LENGTH_SHORT).show();
            return false;
        }
        //mySQLiteOpenHelper.insertStu("2","3","4","5");


        return true;
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        public List[] lists;

        public MyAdapter(List[] lists) {
            this.lists = lists;
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exp4_rv_database, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.item_tv_exp4_id.setText(lists[0].get(position).toString());
            holder.item_tv_exp4_name.setText(lists[1].get(position).toString());
            holder.item_tv_exp4_age.setText(lists[2].get(position).toString());
            holder.item_tv_exp4_city.setText(lists[3].get(position).toString());
        }

        @Override
        public int getItemCount() {
            return lists[0].size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
            public View rootView;
            public TextView item_tv_exp4_id;
            public TextView item_tv_exp4_name;
            public TextView item_tv_exp4_age;
            public TextView item_tv_exp4_city;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.item_tv_exp4_id = (TextView) rootView.findViewById(R.id.item_tv_exp4_id);
                this.item_tv_exp4_name = (TextView) rootView.findViewById(R.id.item_tv_exp4_name);
                this.item_tv_exp4_age = (TextView) rootView.findViewById(R.id.item_tv_exp4_age);
                this.item_tv_exp4_city = (TextView) rootView.findViewById(R.id.item_tv_exp4_city);
            }
        }
    }
}
