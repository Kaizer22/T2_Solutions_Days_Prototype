package com.shebut_dev.tele2marketreinvented.data.mapper;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.shebut_dev.tele2marketreinvented.data.model.DayPoint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LineGraphSeriesMapper extends BaseMapper<ArrayList<DayPoint>, LineGraphSeries<DataPoint>> {


    @Override
    public ArrayList<DayPoint> map1(LineGraphSeries<DataPoint> o2) {
        return null;
    }

    @Override
    public LineGraphSeries<DataPoint> map2(ArrayList<DayPoint> o1) {
        LineGraphSeries<DataPoint> result = new LineGraphSeries<>();
        for (DayPoint day: o1) {
            result.appendData(new DataPoint(day.day, day.number),
                    false, 12);
        }
        return result;
    }
}
