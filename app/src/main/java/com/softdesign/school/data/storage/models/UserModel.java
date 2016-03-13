package com.softdesign.school.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Users")
public class UserModel extends Model {

    @Column (name = "firstName")
    public String firstName;

    @Column(name = "lastName")
    public String lastName;

    @Column (name = "team")
    public TeamModel team;

    public UserModel() {
        super();
    }

    public UserModel(String firstName, String lastName, TeamModel team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    public static List<UserModel> getDataUser(){
        return new Select()
                .from(UserModel.class)
                .execute();
    }

    public static void createNew(String firstName, String lastName, TeamModel team){
        (new UserModel(firstName, lastName, team)).save();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public TeamModel getTeam() {
        return team;
    }
}
