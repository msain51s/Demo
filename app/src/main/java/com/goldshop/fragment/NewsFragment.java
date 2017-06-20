package com.goldshop.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldshop.R;

/**
 * Created by bhanwar on 15/06/2017.
 */

public class NewsFragment extends Fragment {

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_news_fragment, container, false);

        return view;
    }
}
