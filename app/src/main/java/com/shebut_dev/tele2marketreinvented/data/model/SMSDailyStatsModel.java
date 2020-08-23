package com.shebut_dev.tele2marketreinvented.data.model;

import java.util.List;

public class SMSDailyStatsModel {
    public double currentAveragePrice;
    public long currentSMSAmount;
    public long currentLotsCount;
    public List<DayPointModel> monthlyTimeline;

    public SMSDailyStatsModel(double currentAveragePrice, long currentSMSAmount,
                             long currentLotsCount, List<DayPointModel> monthlyTimeline){
        this.currentAveragePrice = currentAveragePrice;
        this.currentSMSAmount = currentSMSAmount;
        this.currentLotsCount = currentLotsCount;
        this.monthlyTimeline = monthlyTimeline;
    }

    public SMSDailyStatsModel(){

    }
}
