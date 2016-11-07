package com.example.lulu.administrator.liwushuohomework.ui;

import android.app.Application;

/**
 * Created by Administrator on 2016/11/6.
 */
public class AppMenu extends Application{
    private static AppMenu instance;
    private static AppMenu getInstance(){
        return  instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance=this;
    }
}
