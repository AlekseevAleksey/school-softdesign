package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.RecyclerUserAdapter;
import com.softdesign.school.utils.Lg;

import java.util.ArrayList;

/**
 * Created by Glyuk on 04.02.2016.
 */
public class ContactsFragment extends Fragment {

    private static final String TAG = "";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<User> mUsers = new ArrayList<User>();

    View convertView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_contacts, container, false);
        }

        CollapsingToolbarLayout mCollapsingToolBar = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        mCollapsingToolBar.setTitle(getResources().getString(R.string.drawer_contacts));

        generationData();

        mRecyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerUserAdapter(mUsers);
        mRecyclerView.setAdapter(mAdapter);

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
        params.setAnchorId(R.id.coordinator_layout);
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT;
        mFloatButton.setLayoutParams(params);
        mFloatButton.setImageResource(R.drawable.ic_add_24dp);
        /**
         * состояние Collapsing Bar свернут
         */
        ((MainActivity) getActivity()).lockAppBar(true);
    }

    /**
     * Генирация пользователей
     */
    private void generationData() {
        Lg.e(TAG, "получение юзеров");
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Петя", "Петров"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Вася", "Васильев"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Иван", "Иванов"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Коля", "Николаев"));
    }

}

