package com.example.lulu.administrator.liwushuohomework.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.lulu.administrator.liwushuohomework.R;

/**
 * 礼物说引导页
 */
public class WelcomePageActivity extends AppCompatActivity {
    private Context context;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        actionBar=getSupportActionBar();
        actionBar.hide();
        context=this;
        mHandler.sendEmptyMessageDelayed(1,2000);
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Intent intent=new Intent(context, HomePageActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
