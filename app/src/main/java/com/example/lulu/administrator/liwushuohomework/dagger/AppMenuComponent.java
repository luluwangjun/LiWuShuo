package com.example.lulu.administrator.liwushuohomework.dagger;

import com.example.lulu.administrator.liwushuohomework.fragment.BaseFragment;
import com.example.lulu.administrator.liwushuohomework.fragment.SelectFragment;
import com.example.lulu.administrator.liwushuohomework.ui.HomePageActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/11/6.
 */
@Component(modules = {AppMenuModule.class})
public interface AppMenuComponent {
    void inject(HomePageActivity activity);
    void inject(SelectFragment selectFragment);
    void inject(BaseFragment baseFragment);
}
