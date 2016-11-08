package com.example.lulu.administrator.liwushuohomework.presenter.selectPresenter;

import com.example.lulu.administrator.liwushuohomework.view.ISelectView;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface ISelectPresenter {
    void setView(ISelectView selectView);
    void querySelectBean(int id,int offset);
}
