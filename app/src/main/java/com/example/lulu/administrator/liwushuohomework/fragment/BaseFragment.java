package com.example.lulu.administrator.liwushuohomework.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    private static final String KEY = "content";
    private Context context;

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
        TextView textView = new TextView(context);
        textView.setText(getArguments().getString(KEY));
        return textView;
    }

}
