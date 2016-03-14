package com.softdesign.school.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Класс модель для таблицы пользователей, с тремя полями "Имя"б "Фамилия", "Команда"
 */
@Table(name = "Users")
public class UserModel extends Model {

    //поле Имя
    @Column (name = "firstName")
    public String firstName;

    //поле Фамилия
    @Column(name = "lastName")
    public String lastName;

    // поле команда, получаем из TeamModel
    @Column (name = "team")
    public TeamModel team;

    /**
     * Снова магия с конструкторами
     */
    public UserModel() {
        super();
    }

    public UserModel(String firstName, String lastName, TeamModel team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    /**
     * Метод внутри которого выполняются команды получения списка пользователей
     * @return
     */
    public static List<UserModel> getDataUser(){
        return new Select()
                .from(UserModel.class)
                .execute();
    }

    /**
     * Метод сохранения нового пользователя в базу
     * @param firstName
     * @param lastName
     * @param team
     */
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
