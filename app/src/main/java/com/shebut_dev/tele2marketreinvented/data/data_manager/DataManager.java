package com.shebut_dev.tele2marketreinvented.data.data_manager;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.shebut_dev.tele2marketreinvented.data.model.UserModel;

public interface DataManager {


    interface GetUserByIDCallback{
        void onFinish(UserModel userModel);
        void onError(Exception e);
    }

    interface GetGbStatisticsCallback{
        void onFinish(LineGraphSeries<DataPoint> graphViewSeries);
        void onError(Exception e);
    }

    void getUserByID(String userID,
                            GetUserByIDCallback callback);

    void getGbStatistics(GetGbStatisticsCallback callback);


}
