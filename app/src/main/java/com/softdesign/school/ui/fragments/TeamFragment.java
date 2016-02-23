package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.MainActivity;

/**
 * Created by Glyuk on 04.02.2016.
 */
public class TeamFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_team, null, false);
        CollapsingToolbarLayout mCollapsingToolBar = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        mCollapsingToolBar.setTitle(getResources().getString(R.string.drawer_team));
        ((MainActivity)getActivity()).lockAppBar(true);
        return convertView;
    }
}
