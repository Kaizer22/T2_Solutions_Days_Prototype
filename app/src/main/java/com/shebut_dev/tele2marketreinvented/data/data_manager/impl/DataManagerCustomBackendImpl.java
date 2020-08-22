package com.shebut_dev.tele2marketreinvented.data.data_manager.impl;

import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.custom_usecases.GetUserById.GetUserByIdUseCase;
import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.custom_usecases.UseCaseStateCallback;

public class DataManagerCustomBackendImpl implements DataManager {
    GetUserByIdUseCase getUserByIdUseCase;
    @Override
    public void getUserByID(String userID, GetUserByIDCallback callback) {
        getUserByIdUseCase = new GetUserByIdUseCase(new UseCaseStateCallback() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onFinish(String s) {

                callback.onFinish(null);
            }
        });
        getUserByIdUseCase.execute();
    }

    @Override
    public void getGbStatistics(GetGbStatisticsCallback callback) {

    }
}
