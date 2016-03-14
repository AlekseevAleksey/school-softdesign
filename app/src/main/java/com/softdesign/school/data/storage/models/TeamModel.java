package com.softdesign.school.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Класс который является моделью для "Команды", создаем таблицу Teams с одним полем name
 */
@Table(name = "Teams")
public class TeamModel extends Model {

    /**
     * Создаем поле с названием команды
     */
    @Column(name = "name")
    public String name;

    /**
     * Не понятная магия с конструктром, скуазано что нужно обязательно создаать пустой конструктор если
     * используем конструктор для класса
     */
    public TeamModel() {
        super();
    }

    public TeamModel(String name) {
        this.name = name;
    }

    /**
     * проверка на то что пользователь ввел название команды и его сохранения в базу
     * @param name
     */
    public static void createNew(String name) {
        if (TeamModel.getOneByName(name) == null) {
            (new TeamModel(name)).save();
        }
    }

    /**
     * Метод внутри которого выполняются команды получения списка команд
     * @return
     */
    public static List<TeamModel> getDataTeam() {
        return new Select()
                .from(TeamModel.class)
                .execute();
    }

    /**
     * Проверка на то что введеное название не совпадает с существующими названиями
     * @param name
     * @return
     */
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

    /**
     * Связь с UserModel один к многим
     * @return
     */
    public List<UserModel> getUsers() {
        return getMany(UserModel.class, "team");
    }
}
