package com.softdesign.school.utils;


import android.content.AsyncTaskLoader;
import android.content.Context;


import com.softdesign.school.data.storage.models.UserModel;

import java.util.List;

/**
 * При обращении вызывает команду получения из БД списка пользователей и передает их в виде массива
 */
public class AsyncLoader extends AsyncTaskLoader<List<UserModel>> {

    public AsyncLoader(Context context) {
        super(context);
    }

    @Override
    public List<UserModel> loadInBackground() {
        return UserModel.getDataUser();
    }
}
