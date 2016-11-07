package com.example.lulu.administrator.liwushuohomework.presenter;

import com.example.lulu.administrator.liwushuohomework.view.IMenuView;

/**
 * 礼物说MVP模式中的P层
 * Created by Administrator on 2016/11/6.
 */
public interface IMenuPresenter {
    void setView(IMenuView menuView);
    void queryMenuBean();
}
