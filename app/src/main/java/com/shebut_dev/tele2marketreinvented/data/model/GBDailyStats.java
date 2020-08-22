package com.shebut_dev.tele2marketreinvented.data.model;

import java.util.List;

public class GBDailyStats {
    public double currentAveragePrice;
    public long currentGBAmount;
    public long currentLotsCount;
    public List<DayPoint> monthlyTimeline;

    public GBDailyStats(double currentAveragePrice, long currentGBAmount,
                        long currentLotsCount, List<DayPoint> monthlyTimeline){
        this.currentAveragePrice = currentAveragePrice;
        this.currentGBAmount = currentGBAmount;
        this.currentLotsCount = currentLotsCount;
        this.monthlyTimeline = monthlyTimeline;
    }

    public GBDailyStats(){

    }
}
