package com.shebut_dev.tele2marketreinvented.data.data_manager.impl;

import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.custom_usecases.get.GetUserByIdUseCase;
import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.custom_usecases.UseCaseStateCallback;
import com.shebut_dev.tele2marketreinvented.data.model.LotModel;
import com.shebut_dev.tele2marketreinvented.data.model.UserModel;

public class DataManagerCustomBackendImpl implements DataManager {
    GetUserByIdUseCase getUserByIdUseCase;

    @Override
    public void testReq(String data, TestCallback testCallback) {

    }

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
    public void getUserLots(String userID, GetUserLotsCallback callback) {

    }

    @Override
    public void postUser(UserModel userModel, PostUserCallback callback) {

    }



    @Override
    public void getGbStatistics(GetGbStatisticsCallback callback) {

    }

    @Override
    public void postLot(String userID, LotModel lotModel, PostLotCallback callback) {

    }

    @Override
    public void getLot(String userId, String lotID, GetLotCallback callback) {

    }

    @Override
    public void editLot(String userID, LotModel lotModel, EditLotCallback callback) {

    }


    @Override
    public void deleteLot() {

    }
}
