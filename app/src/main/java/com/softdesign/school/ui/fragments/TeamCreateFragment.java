package com.softdesign.school.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;

import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.TeamModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Диалоговое окно добавления команды
 */
public class TeamCreateFragment extends DialogFragment {

    @Bind(R.id.team_name)
    TextView mTeamName;

    public TeamCreateFragment() {}


    public static TeamCreateFragment create(String title){
        TeamCreateFragment frag = new TeamCreateFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View addTeam = getActivity().getLayoutInflater().inflate(R.layout.dialog_aad_team, null);
        ButterKnife.bind(this, addTeam);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle(getArguments().getString("title"))
                .setView(addTeam)
                .setPositiveButton(R.string.button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String teamName = mTeamName.getText().toString();
                        if(!teamName.isEmpty()){
                            TeamModel.createNew(teamName);
                        }
                    }
                })
                .setNegativeButton(R.string.button_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        TeamCreateFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

}
