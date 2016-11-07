package com.example.lulu.administrator.liwushuohomework.dagger;

import com.example.lulu.administrator.liwushuohomework.model.MenuApi;
import com.example.lulu.administrator.liwushuohomework.presenter.IMenuPresenter;
import com.example.lulu.administrator.liwushuohomework.presenter.MenuPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/6.
 */
@Module
public class AppMenuModule {
    @Provides
    public MenuApi provideModule(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(MenuApi.class);
    }
    @Provides
    public IMenuPresenter provideMenuPresenter(MenuApi menuApi){
        return new MenuPresenter(menuApi);
    }
}
