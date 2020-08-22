package com.shebut_dev.tele2marketreinvented.data.data_manager;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.shebut_dev.tele2marketreinvented.data.model.GBDailyStats;
import com.shebut_dev.tele2marketreinvented.data.model.Lot;
import com.shebut_dev.tele2marketreinvented.data.model.UserModel;

import java.util.List;

public interface DataManager {


    interface GetUserByIDCallback{
        void onFinish(UserModel userModel);
        void onError(Exception e);
    }

    interface GetGbStatisticsCallback{
        void onFinish(GBDailyStats gbDailyStats);
        void onError(Exception e);
    }

    interface GetUserLotsCallback{
        void onFinish(List<Lot> lots);
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

    void testReq(String data,
                 TestCallback testCallback );

    void getUserByID(String userID,
                            GetUserByIDCallback callback);

    void getUserLots(String userID,
                     GetUserLotsCallback callback);

    void postUser(UserModel userModel,
                  PostUserCallback callback);

    void getGbStatistics(GetGbStatisticsCallback callback);

    void postLot();

    void deleteLot();




}
