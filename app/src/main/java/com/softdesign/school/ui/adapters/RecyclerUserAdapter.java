package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.utils.Lg;

import java.util.ArrayList;

/**
 * Created by Glyuk on 18.02.2016.
 */
public class RecyclerUserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private static final String TAG = "";

    ArrayList<User> mUsers;

    public RecyclerUserAdapter(ArrayList<User> mUsers) {
        Lg.e(TAG, "Adapter");
        this.mUsers = mUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Lg.e(TAG, "UserViewHolder");
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_card, parent, false);
        return new UserViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Lg.e(TAG, "Bind");
        User user = mUsers.get(position);
        holder.fullName.setText(user.getmFirstName() + " " + user.getmLastName());
        holder.avatar.setImageDrawable(user.getmImage());

    }

    @Override
    public int getItemCount() {
        Lg.e(TAG, "ItemCount");
        return mUsers.size();
    }
}
