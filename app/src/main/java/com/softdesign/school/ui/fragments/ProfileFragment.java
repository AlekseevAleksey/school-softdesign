package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.MainActivity;

/**
 * Created by Glyuk on 04.02.2016.
 */
public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_profile, null, false);
        getActivity().setTitle(getResources().getString(R.string.drawer_profile));

        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * Устанваливаем кнопку Floating Action Button
         */
        FloatingActionButton mFloatButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatButton.getLayoutParams();
        params.setAnchorId(R.id.appbar_layout);
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT;
        mFloatButton.setLayoutParams(params);
        mFloatButton.setImageResource(R.drawable.ic_create_24dp);
        /**
         * состояние Collapsing Bar свернут
         */
        ((MainActivity) getActivity()).lockAppBar(false);
    }
}
