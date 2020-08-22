package com.shebut_dev.tele2marketreinvented.data.mapper;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.shebut_dev.tele2marketreinvented.data.model.DayPointModel;

import java.util.ArrayList;

public class LineGraphSeriesMapper extends BaseMapper<ArrayList<DayPointModel>, LineGraphSeries<DataPoint>> {


    @Override
    public ArrayList<DayPointModel> map1(LineGraphSeries<DataPoint> o2) {
        return null;
    }

    @Override
    public LineGraphSeries<DataPoint> map2(ArrayList<DayPointModel> o1) {
        LineGraphSeries<DataPoint> result = new LineGraphSeries<>();
        for (DayPointModel day: o1) {
            result.appendData(new DataPoint(day.day, day.number),
                    false, 12);
        }
        return result;
    }
}
