package com.shu747.androidcourse.activity.experiment.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shuo747
 * @Date: 2019/5/10 0:09
 */
public class ActivityCollector {
    public static List<Activity> activityList=new ArrayList<Activity>();
    //将Activity加入集合
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    //将Activity移除集合
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    //关闭所有Activity
    public static void finishAll(){
        for(Activity activity:activityList){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
