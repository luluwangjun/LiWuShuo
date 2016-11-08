package com.example.lulu.administrator.liwushuohomework.presenter.basePresenter;

import com.example.lulu.administrator.liwushuohomework.bean.SelectBean;
import com.example.lulu.administrator.liwushuohomework.model.MenuApi;
import com.example.lulu.administrator.liwushuohomework.view.IBaseView;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/11/7.
 */
public class BasePresenter implements IBasePresenter{
    private IBaseView baseView;
    private MenuApi menuApi;
    @Override
    public void setBaseView(IBaseView baseView) {
        this.baseView = baseView;
    }

    public BasePresenter(MenuApi menuApi) {
        this.menuApi = menuApi;
    }

    @Override
    public void querySelectedList(int id, int offset) {
        final List<SelectBean.DataBean.ItemsBean> items = new ArrayList<>();
        menuApi.querySelectedBean(id,offset)
                .flatMap(new Func1<SelectBean, Observable<SelectBean.DataBean.ItemsBean>>() {
                    @Override
                    public Observable<SelectBean.DataBean.ItemsBean> call(SelectBean selectBean) {
                        List<SelectBean.DataBean.ItemsBean> itemsBeen = selectBean.getData().getItems();
                        return Observable.from(itemsBeen);
                    }
                })
                .map(new Func1<SelectBean.DataBean.ItemsBean, List<SelectBean.DataBean.ItemsBean>>() {
                    @Override
                    public List<SelectBean.DataBean.ItemsBean> call(SelectBean.DataBean.ItemsBean itemsBean) {
                        items.add(itemsBean);
                        return items;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SelectBean.DataBean.ItemsBean>>() {
                    @Override
                    public void onCompleted() {
                        baseView.setListData(items);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<SelectBean.DataBean.ItemsBean> itemsBeen) {

                    }
                });
    }
}
