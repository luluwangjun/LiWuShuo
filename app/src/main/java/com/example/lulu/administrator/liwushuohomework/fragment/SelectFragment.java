package com.example.lulu.administrator.liwushuohomework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.lulu.administrator.liwushuohomework.R;
import com.example.lulu.administrator.liwushuohomework.bean.SelectBean;
import com.example.lulu.administrator.liwushuohomework.dagger.DaggerAppMenuComponent;
import com.example.lulu.administrator.liwushuohomework.presenter.adapter.SelectAdapter;
import com.example.lulu.administrator.liwushuohomework.presenter.selectPresenter.ISelectPresenter;
import com.example.lulu.administrator.liwushuohomework.view.ISelectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFragment extends Fragment implements ISelectView{

    private static final String KEY = "content";
    private Context context;
    private Map<String,List<SelectBean.DataBean.ItemsBean>> map=new ArrayMap<>();
    private List<String> dataList = new ArrayList<>();
    private SelectAdapter selectAdapter;

    @Inject
    ISelectPresenter selectPresenter;
    @BindView(R.id.select_Expandable_listView)
    ExpandableListView listView;

    public SelectFragment() {
        // Required empty public constructor
    }
    public static SelectFragment newInstance(String content){
        SelectFragment selectFragment = new SelectFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,content);
        selectFragment.setArguments(bundle);
        return selectFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select,null);
        ButterKnife.bind(this,view);
        DaggerAppMenuComponent.create().inject(this);
        selectAdapter = new SelectAdapter(getActivity(),dataList,map);
        listView.setAdapter(selectAdapter);
        selectPresenter.querySelectBean(Integer.parseInt(getArguments().getString(KEY)),0);
        selectPresenter.setView(this);
        return view;
    }

    @Override
    public void refreshAdapter(List<String> groupList, Map<String, List<SelectBean.DataBean.ItemsBean>> map) {
        dataList.addAll(groupList);
        this.map.putAll(map);
        selectAdapter.notifyDataSetChanged();
        for (int i = 0; i < dataList.size(); i++) {
            listView.expandGroup(i);
        }
    }
}
