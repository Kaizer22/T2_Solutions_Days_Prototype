package com.shebut_dev.tele2marketreinvented.data.data_manager;

import com.shebut_dev.tele2marketreinvented.data.model.UserModel;

public interface DataManager {


    interface GetUserByIDCallback{
        void onFinish(UserModel userModel);
        void onError(Exception e);
    }
    public void getUserByID(String userID,
                            GetUserByIDCallback callback);
}
