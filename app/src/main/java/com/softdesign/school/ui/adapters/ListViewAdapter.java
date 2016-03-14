package com.softdesign.school.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.UserModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Адаптер для полбзователей, вставляющий их в список
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private final Context mContext;
    private List<UserModel> mUsers;

    public ListViewAdapter(Context context, List<UserModel> users) {
        this.mContext = context;
        this.mUsers = users;
    }

    /**
     * создается элемент списка внутрь которого вставляется вьюха с данными пользователя
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.item_user_list, null);
        return new ViewHolder(convertView);
    }

    /**
     * получение данных из модели вставка их во вьюху
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModel userModel = mUsers.get(position);
        holder.name.setText(userModel.getFirstName() + " " + userModel.getLastName());
        holder.team.setText(userModel.getTeam().toString());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    /**
     * в рамках домашки не стал выносить в отдельный класс
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.user_name_list) TextView name;
        @Bind(R.id.user_team_list) TextView team;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
