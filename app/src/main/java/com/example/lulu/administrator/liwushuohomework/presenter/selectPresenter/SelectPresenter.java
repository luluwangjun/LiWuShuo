package com.example.lulu.administrator.liwushuohomework.presenter.selectPresenter;

import android.support.v4.util.ArrayMap;

import com.example.lulu.administrator.liwushuohomework.bean.SelectBean;
import com.example.lulu.administrator.liwushuohomework.model.MenuApi;
import com.example.lulu.administrator.liwushuohomework.tools.Utils;
import com.example.lulu.administrator.liwushuohomework.view.ISelectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/7.
 */
public class SelectPresenter implements ISelectPresenter{
    private MenuApi menuApi;
    private ISelectView selectView;

    public SelectPresenter(MenuApi menuApi) {
        this.menuApi = menuApi;
    }

    @Override
    public void setView(ISelectView selectView) {
        this.selectView = selectView;
    }

    @Override
    public void querySelectBean(int id, int offset) {
        final Map<String,List<SelectBean.DataBean.ItemsBean>> map = new ArrayMap<>();
        final List<String> dataList = new ArrayList<>();
        menuApi.querySelectedBean(id,offset)
                .flatMap(new Func1<SelectBean, Observable<SelectBean.DataBean.ItemsBean>>() {
                    @Override
                    public Observable<SelectBean.DataBean.ItemsBean> call(SelectBean selectBean) {
                        List<SelectBean.DataBean.ItemsBean> items = selectBean.getData().getItems();
                        return Observable.from(items);
                    }
                })
                .map(new Func1<SelectBean.DataBean.ItemsBean, Map<String,List<SelectBean.DataBean.ItemsBean>>>() {
                    @Override
                    public Map<String, List<SelectBean.DataBean.ItemsBean>> call(SelectBean.DataBean.ItemsBean itemsBean) {
                        int published_at = itemsBean.getPublished_at();
                        String formatDate = Utils.formatDate(published_at * 1000);
                        List<SelectBean.DataBean.ItemsBean> items = map.get(formatDate);
                        if (items == null){
                            items = new ArrayList<SelectBean.DataBean.ItemsBean>();
                            dataList.add(formatDate);
                            map.put(formatDate,items);
                        }
                        items.add(itemsBean);
                        return map;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String, List<SelectBean.DataBean.ItemsBean>>>() {
                    @Override
                    public void onCompleted() {
                        selectView.refreshAdapter(dataList,map);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Map<String, List<SelectBean.DataBean.ItemsBean>> map) {

                    }
                });
    }
}
