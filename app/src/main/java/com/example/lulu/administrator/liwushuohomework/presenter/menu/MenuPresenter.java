package com.example.lulu.administrator.liwushuohomework.presenter.menu;

import com.example.lulu.administrator.liwushuohomework.bean.MenuBean;
import com.example.lulu.administrator.liwushuohomework.model.MenuApi;
import com.example.lulu.administrator.liwushuohomework.presenter.IMenuPresenter;
import com.example.lulu.administrator.liwushuohomework.tools.LogUtils;
import com.example.lulu.administrator.liwushuohomework.view.IMenuView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 礼物说MVP模式中的P层
 * Created by Administrator on 2016/11/6.
 */
public class MenuPresenter implements IMenuPresenter {
    private MenuApi menuApi;
    private IMenuView menuView;

    public MenuPresenter(MenuApi menuApi) {
        this.menuApi = menuApi;
    }

    @Override
    public void setView(IMenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void queryMenuBean() {
        final List<String> titles = new ArrayList<>();
        final List<Integer> ids = new ArrayList<>();
        menuApi.queryMenuBena()
                .flatMap(new Func1<MenuBean, Observable<MenuBean.DataBean.ChannelsBean>>() {
                    @Override
                    public Observable<MenuBean.DataBean.ChannelsBean> call(MenuBean menuBean) {
                        List<MenuBean.DataBean.ChannelsBean> items = menuBean.getData().getChannels();
                        return Observable.from(items);
                    }
                })
                .map(new Func1<MenuBean.DataBean.ChannelsBean, List<String>>() {
                    @Override
                    public List<String> call(MenuBean.DataBean.ChannelsBean channelsBean) {

                        String name = channelsBean.getName();
                        LogUtils.log(MenuPresenter.class,"--->"+name);
                        int id = channelsBean.getId();
                        titles.add(name);
                        ids.add(id);
                        return titles;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {
                        menuView.refreshAdapter(titles,ids);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {

                    }
                });
    }
}
