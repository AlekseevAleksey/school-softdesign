package com.softdesign.school.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Teams")
public class TeamModel extends Model {

    @Column(name = "name")
    public String name;

    public TeamModel() {
        super();
    }

    public TeamModel(String name) {
        this.name = name;
    }

    public static void createNew(String name) {
        if (TeamModel.getOneByName(name) == null) {
            (new TeamModel(name)).save();
        }
    }

    public static List<TeamModel> getDataTeam() {
        return new Select()
                .from(TeamModel.class)
                .execute();
    }

    public static TeamModel getOneByName(String name){
        List<TeamModel> teamsList = new Select()
                .from(TeamModel.class)
                .where("name = ?", name)
                .execute();

        if(!teamsList.isEmpty()){
            return teamsList.get(0);
        } else {
            return null;
        }
    }

    public String toString() {
        return name;
    }

    public List<UserModel> getUsers() {
        return getMany(UserModel.class, "team");
    }
}
