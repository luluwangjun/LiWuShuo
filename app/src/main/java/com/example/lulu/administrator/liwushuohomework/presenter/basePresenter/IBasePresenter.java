package com.example.lulu.administrator.liwushuohomework.presenter.basePresenter;

import com.example.lulu.administrator.liwushuohomework.view.IBaseView;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface IBasePresenter {
    void setBaseView(IBaseView baseView);
    void querySelectedList(int id,int offset);
}
