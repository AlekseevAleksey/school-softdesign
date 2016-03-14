package com.softdesign.school.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.TeamModel;
import com.softdesign.school.data.storage.models.UserModel;
import com.softdesign.school.ui.activities.ActivityBD;
import com.softdesign.school.ui.adapters.ListViewAdapterTeam;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Диалоговое окно добавления пользователя
 */
public class UserCreateFragment extends DialogFragment {

    @Bind(R.id.first_name)TextView mFirstName;
    @Bind(R.id.last_name)TextView mLastName;
    @Bind(R.id.spinner)Spinner mTeamSpinner;

    public UserCreateFragment() {

    }

    public static UserCreateFragment create(String title){
        UserCreateFragment frag = new UserCreateFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View addUser = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_user, null);
        ButterKnife.bind(this, addUser);
        TeamModel.getDataTeam();
        List<TeamModel> teams = TeamModel.getDataTeam();
        ListViewAdapterTeam teamAdapter = new ListViewAdapterTeam(getActivity(), teams);
        mTeamSpinner.setAdapter(teamAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle(getArguments().getString("title"))
                .setView(addUser)
                .setPositiveButton(R.string.button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String firstName = mFirstName.getText().toString();
                        String lastName = mLastName.getText().toString();
                        String teamName = mTeamSpinner.getSelectedItem().toString();
                        TeamModel team = TeamModel.getOneByName(teamName);
                        if (!firstName.isEmpty() && !lastName.isEmpty() && team != null) {
                            UserModel.createNew(firstName, lastName, team);
                            ((ActivityBD) getActivity()).refreshUserList();
                        }
                    }
                })
                .setNegativeButton(R.string.button_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        UserCreateFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

}
