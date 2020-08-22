package com.shebut_dev.tele2marketreinvented.data.model;

import java.util.List;

public class GBDailyStatsModel {
    public double currentAveragePrice;
    public long currentGBAmount;
    public long currentLotsCount;
    public List<DayPointModel> monthlyTimeline;

    public GBDailyStatsModel(double currentAveragePrice, long currentGBAmount,
                             long currentLotsCount, List<DayPointModel> monthlyTimeline){
        this.currentAveragePrice = currentAveragePrice;
        this.currentGBAmount = currentGBAmount;
        this.currentLotsCount = currentLotsCount;
        this.monthlyTimeline = monthlyTimeline;
    }

    public GBDailyStatsModel(){

    }
}
