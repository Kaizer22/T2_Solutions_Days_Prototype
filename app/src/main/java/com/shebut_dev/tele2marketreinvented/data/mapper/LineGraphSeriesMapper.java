package com.shebut_dev.tele2marketreinvented.data.mapper;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class LineGraphSeriesMapper extends BaseMapper<Integer[][], LineGraphSeries<DataPoint>> {

    @Override
    public Integer[][] map1(LineGraphSeries<DataPoint> o2) {
        return new Integer[0][];
    }

    @Override
    public LineGraphSeries<DataPoint> map2(Integer[][] o1) {
        return null;
    }
}
