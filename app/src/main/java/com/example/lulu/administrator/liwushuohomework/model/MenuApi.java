package com.example.lulu.administrator.liwushuohomework.model;

import com.example.lulu.administrator.liwushuohomework.bean.MenuBean;
import com.example.lulu.administrator.liwushuohomework.bean.SelectBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 礼物说MVP模式中的M层
 * Created by Administrator on 2016/11/6.
 */
public interface MenuApi {
    /**

     * 礼物说首页界面菜单栏接口
     * @return
     */
    @GET("v2/channels/preset?gender=1&generation=2")
    Observable<MenuBean> queryMenuBena();
    /**
     * 礼物说首页界面接口
     * @return
     */
    @GET("v2/channels/{id}/items?ad=2&gender=1&generation=2&limit=20")
    Observable<SelectBean> querySelectedBean(@Path("id") int id, @Query("offset") int offset);
}
