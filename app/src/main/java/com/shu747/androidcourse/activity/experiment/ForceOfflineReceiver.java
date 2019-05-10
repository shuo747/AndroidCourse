package com.shu747.androidcourse.activity.experiment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import com.shu747.androidcourse.activity.experiment.util.ActivityCollector;

public class ForceOfflineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        //构建一个对话框
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("警告");
        dialogBuilder.setMessage("你的账号被强制下线，请重新登录");
        dialogBuilder.setCancelable(false); //并且对话框不可被取消 //给对话框注册确定按钮
        dialogBuilder.setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll(); //销毁所有活动
                Intent i = new Intent(context, Exp3Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //在广播接收器中启动活动 因此需要加上这个标志
                context.startActivity(i); //重新启动登录界面
            }
        });
        AlertDialog alertDialog = dialogBuilder.create(); //设置AlertDialog 的类型 保证广播接收器中可以正常的弹出 //系统级别的对话框 因此需要在AndroidManifest中进行声明
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();

    }
}
