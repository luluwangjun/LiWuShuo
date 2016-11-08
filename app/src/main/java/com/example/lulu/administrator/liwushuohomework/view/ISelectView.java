package com.example.lulu.administrator.liwushuohomework.view;

import com.example.lulu.administrator.liwushuohomework.bean.SelectBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface ISelectView {
    void refreshAdapter(List<String> groupList, Map<String,List<SelectBean.DataBean.ItemsBean>> map);

}
