package com.example.lulu.administrator.liwushuohomework.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lulu.administrator.liwushuohomework.R;
import com.example.lulu.administrator.liwushuohomework.bean.SelectBean;
import com.example.lulu.administrator.liwushuohomework.dagger.DaggerAppMenuComponent;
import com.example.lulu.administrator.liwushuohomework.presenter.adapter.Base_Adapter;
import com.example.lulu.administrator.liwushuohomework.presenter.basePresenter.IBasePresenter;
import com.example.lulu.administrator.liwushuohomework.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements IBaseView{

    private static final String KEY = "content";
    private Context context;
    private List<SelectBean.DataBean.ItemsBean> items = new ArrayList<>();

    @BindView(R.id.base_lv)
    ListView listView;
    @Inject
    IBasePresenter presenter;

    private Base_Adapter base_adapter;

    public BaseFragment() {
        // Required empty public constructor
    }

    public static BaseFragment newInstance(String content){
        BaseFragment baseFragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,content);
        baseFragment.setArguments(bundle);
        return baseFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base,null);
        DaggerAppMenuComponent.create().inject(this);
        ButterKnife.bind(this,view);
        base_adapter = new Base_Adapter(items, getActivity()) {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }
        };
        listView.setAdapter(base_adapter);
        presenter.querySelectedList(Integer.parseInt(getArguments().getString(KEY)),0);
        presenter.setBaseView(this);
        return view;
    }

    @Override
    public void setListData(List<SelectBean.DataBean.ItemsBean> itemsBeen) {
        items.addAll(itemsBeen);
        base_adapter.notifyDataSetChanged();
    }
}
