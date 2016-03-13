package com.softdesign.school.ui.activities;



import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.UserModel;
import com.softdesign.school.ui.adapters.ListViewAdapter;
import com.softdesign.school.ui.fragments.TeamCreateFragment;
import com.softdesign.school.ui.fragments.UserCreateFragment;
import com.softdesign.school.utils.AsyncLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.softdesign.school.ui.fragments.TeamCreateFragment.*;


public class ActivityBD extends AppCompatActivity implements LoaderManager.LoaderCallbacks <List<UserModel>>{

    @Bind(R.id.list_view_a)
    RecyclerView mUserListView;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<UserModel> mUserList = new ArrayList<UserModel>();
    private ListViewAdapter mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);
        ButterKnife.bind(this);


        getLoaderManager().initLoader(0, null, this);
        refreshUserList();
    }

    @OnClick(R.id.create_user)
    public void addUser(){
        FragmentManager fm = getSupportFragmentManager();
        String title = getResources().getString(R.string.title_create_user);

        UserCreateFragment addUser = UserCreateFragment.create(title);
        addUser.show(fm, "add_user");
    }

    @OnClick(R.id.create_team)
    public void addTeam() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String title = getResources().getString(R.string.title_create_team);

        TeamCreateFragment addTeam = (TeamCreateFragment) TeamCreateFragment.create(title);
        addTeam.show(fragmentManager, "add_team");
    }


    @Override
    public Loader<List<UserModel>> onCreateLoader(int id, Bundle args) {
        return new AsyncLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<UserModel>> loader, List<UserModel> data) {
        mUserList.clear();
        mUserList.addAll(data);
        if(mUserAdapter == null){
            mUserAdapter = new ListViewAdapter(this, mUserList);
            mLayoutManager = new LinearLayoutManager(this);
            mUserListView.setLayoutManager(mLayoutManager);
            mUserListView.setAdapter(mUserAdapter);
        } else {
            mUserAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<UserModel>> loader) {

    }

    public void refreshUserList() {
        getLoaderManager().getLoader(0).forceLoad();
    }
}

