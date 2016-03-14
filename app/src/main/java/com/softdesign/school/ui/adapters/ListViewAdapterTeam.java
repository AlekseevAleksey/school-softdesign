package com.softdesign.school.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.TeamModel;
import com.softdesign.school.data.storage.models.UserModel;

import java.util.List;

/**
 * Адаптер для выпадающего списка
 */
public class ListViewAdapterTeam extends BaseAdapter {

    private final Context mContext;
    private List<TeamModel> mTeams;

    public ListViewAdapterTeam(Context mContext, List<TeamModel> teams) {
        this.mContext = mContext;
        this.mTeams = teams;
    }

    @Override
    public int getCount() {
        return this.mTeams.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mTeams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * берем элемент массива, надуваем вьюху и вставляем в поле
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, null);
            ViewHolder holder = new ViewHolder();
            holder.team = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        }
        TeamModel teams = mTeams.get(position);
        if (teams != null) {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.team.setText(teams.toString());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView team;
    }

}
