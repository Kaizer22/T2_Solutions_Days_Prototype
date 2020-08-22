package com.shebut_dev.tele2marketreinvented.data.data_manager;

import com.shebut_dev.tele2marketreinvented.data.model.GBDailyStatsModel;
import com.shebut_dev.tele2marketreinvented.data.model.LotModel;
import com.shebut_dev.tele2marketreinvented.data.model.UserModel;

import java.util.List;

public interface DataManager {


    interface GetUserByIDCallback{
        void onFinish(UserModel userModel);
        void onError(Exception e);
    }

    interface GetGbStatisticsCallback{
        void onFinish(GBDailyStatsModel gbDailyStatsModel);
        void onError(Exception e);
    }

    interface GetUserLotsCallback{
        void onFinish(List<LotModel> lotModels);
        void onError(Exception e);
    }

    interface TestCallback{
        void onFinish(String result);
        void onError(Exception e);
    }

    interface PostUserCallback{
        void onFinish(String userID);
        void onError(Exception e);
    }

    interface  PostLotCallback{
        void onFinish();
        void onError(Exception e);
    }

    void testReq(String data,
                 TestCallback testCallback );

    void getUserByID(String userID,
                            GetUserByIDCallback callback);

    void getUserLots(String userID,
                     GetUserLotsCallback callback);

    void postUser(UserModel userModel,
                  PostUserCallback callback);

    void getGbStatistics(GetGbStatisticsCallback callback);

    void postLot(String userId, LotModel lotModel,
                 PostLotCallback callback);

    void deleteLot();




}
