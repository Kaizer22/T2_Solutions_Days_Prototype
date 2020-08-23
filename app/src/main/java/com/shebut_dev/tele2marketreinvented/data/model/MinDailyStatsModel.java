package com.shebut_dev.tele2marketreinvented.data.model;

import java.util.List;

public class MinDailyStatsModel {
    public double currentAveragePrice;
    public long currentMINAmount;
    public long currentLotsCount;
    public List<DayPointModel> monthlyTimeline;

    public MinDailyStatsModel(double currentAveragePrice, long currentMinAmount,
                             long currentLotsCount, List<DayPointModel> monthlyTimeline){
        this.currentAveragePrice = currentAveragePrice;
        this.currentMINAmount = currentMinAmount;
        this.currentLotsCount = currentLotsCount;
        this.monthlyTimeline = monthlyTimeline;
    }

    public MinDailyStatsModel(){

    }
}
