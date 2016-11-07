package com.example.lulu.administrator.liwushuohomework.presenter.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 礼物说首页界面菜单栏适配器
 * Created by Administrator on 2016/11/6.
 */
public class MenuAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titles;

    public MenuAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
