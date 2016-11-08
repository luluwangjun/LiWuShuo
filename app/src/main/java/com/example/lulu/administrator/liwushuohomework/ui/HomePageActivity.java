package com.example.lulu.administrator.liwushuohomework.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.lulu.administrator.liwushuohomework.R;
import com.example.lulu.administrator.liwushuohomework.dagger.DaggerAppMenuComponent;
import com.example.lulu.administrator.liwushuohomework.fragment.BaseFragment;
import com.example.lulu.administrator.liwushuohomework.fragment.SelectFragment;
import com.example.lulu.administrator.liwushuohomework.presenter.IMenuPresenter;
import com.example.lulu.administrator.liwushuohomework.presenter.adapter.MenuAdapter;
import com.example.lulu.administrator.liwushuohomework.tools.LogUtils;
import com.example.lulu.administrator.liwushuohomework.view.IMenuView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 礼物说首页界面
 */
public class HomePageActivity extends AppCompatActivity implements IMenuView{

    @BindView(R.id.main_tab)
    TabLayout tabLayout;
    @BindView(R.id.main_vp)
    ViewPager viewPager;
    @Inject
    IMenuPresenter menuPresenter;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        DaggerAppMenuComponent.create().inject(this);
        menuAdapter = new MenuAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager.setAdapter(menuAdapter);
        menuPresenter.queryMenuBean();
        menuPresenter.setView(this);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.BLACK, Color.RED);
    }

    @Override
    public void refreshAdapter(List<String> beanList,List<Integer> integers) {
        LogUtils.log(HomePageActivity.class,"--->"+beanList);
        titles.addAll(beanList);
//        menuAdapter.notifyDataSetChanged();
        fragmentList.add(SelectFragment.newInstance(String.valueOf(integers.get(0))));
        for (int i = 1, len = titles.size(); i < len; i++) {
            fragmentList.add(BaseFragment.newInstance(String.valueOf(integers.get(i))));
        }
        menuAdapter.notifyDataSetChanged();
    }
}
