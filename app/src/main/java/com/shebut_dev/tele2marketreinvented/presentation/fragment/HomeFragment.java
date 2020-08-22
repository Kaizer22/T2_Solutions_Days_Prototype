package com.shebut_dev.tele2marketreinvented.presentation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.data.mapper.LineGraphSeriesMapper;
import com.shebut_dev.tele2marketreinvented.data.model.DayPoint;
import com.shebut_dev.tele2marketreinvented.data.model.GBDailyStats;
import com.shebut_dev.tele2marketreinvented.data.model.Lot;
import com.shebut_dev.tele2marketreinvented.data.model.UserModel;
import com.shebut_dev.tele2marketreinvented.presentation.activity.MainActivity;
import com.shebut_dev.tele2marketreinvented.presentation.adapter.MyLotsAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private String currentUserID;

    public HomeFragment(String currentUserID){
        this.currentUserID = currentUserID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        MainActivity baseActivity = (MainActivity) getActivity();
        initStats(root, baseActivity);
        initTabs(root);
        initInteractions(root, baseActivity);
        initRecyclerView(root, baseActivity);
        return root;
    }

    private void initInteractions(View root, MainActivity baseActivity){

        ImageButton button = root.findViewById(R.id.button_test_request);
        ImageButton button2 = root.findViewById(R.id.button_test_request_2);
        Button toLotCreation = root.findViewById(R.id.button_to_create_lot);

        toLotCreation.setOnClickListener(l -> {
            baseActivity.navigateToCreateLot();
        });

        button2.setOnClickListener(l -> baseActivity
                .getDataManager().testReq("TEST", new DataManager.TestCallback() {
            @Override
            public void onFinish(String result) {

            }

            @Override
            public void onError(Exception e) {

            }
        }));
        button.setOnClickListener(l -> baseActivity
                .getDataManager().getUserByID("test",
                new DataManager.GetUserByIDCallback() {
                    @Override
                    public void onFinish(UserModel userModel) {
                        //Пока null
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                }));
    }

    private void initStats(View root, MainActivity baseActivity){
        TextView averagePrice = root.findViewById(R.id.average_price);
        TextView totalLotCount = root.findViewById(R.id.total_lot_count);
        TextView totalAmount = root.findViewById(R.id.total_amount);
        baseActivity.getDataManager().getGbStatistics(new DataManager.GetGbStatisticsCallback() {
            @Override
            public void onFinish(GBDailyStats gbDailyStats) {
                averagePrice.setText(String.valueOf(gbDailyStats.currentAveragePrice));
                totalLotCount.setText(String.valueOf(gbDailyStats.currentLotsCount));
                totalAmount.setText(String.valueOf(gbDailyStats.currentGBAmount));
                initGraph(root, gbDailyStats.monthlyTimeline);
                Log.d("TEST", String.valueOf(gbDailyStats.monthlyTimeline.get(0).number));
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void initRecyclerView(View root, MainActivity baseActivity){
        RecyclerView lotsList = root.findViewById(R.id.my_lots_list);
        lotsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyLotsAdapter lotsAdapter = new MyLotsAdapter(getContext());
        lotsList.setAdapter(lotsAdapter);
        baseActivity.getDataManager().getUserLots(currentUserID, new DataManager.GetUserLotsCallback() {
            @Override
            public void onFinish(List<Lot> lots) {
                lotsAdapter.setLots(lots);

            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });

        lotsList.setAdapter(lotsAdapter);

    }

    private void initGraph(View root, List<DayPoint> timeline){
        GraphView graphView = root.findViewById(R.id.average_price_graph);
        LineGraphSeriesMapper mapper = new LineGraphSeriesMapper();
        LineGraphSeries<DataPoint> data = mapper.map2((ArrayList<DayPoint>) timeline);
        graphView.removeAllSeries();
        graphView.addSeries(data);

    }
    private void initTabs(View root){
        //tabHost = root.findViewById(R.id.tabHost);
        //tabHost.setup();

        //TabHost.TabSpec tabSpec =

                //tabHost.newTabSpec("average_price_gb");
        //tabSpec.setContent(R.id.average_gb);
        //tabSpec.setIndicator("Гигабайты");
        //tabHost.addTab(tabSpec);

        //tabHost.newTabSpec("average_price_min");
        //tabSpec.setContent(R.id.average_minutes);
        //tabSpec.setIndicator("Минуты");
        //tabHost.addTab(tabSpec);

        //tabHost.newTabSpec("average_price_sms");
        //tabSpec.setContent(R.id.average_sms);
        //tabSpec.setIndicator("СМС");
        //tabHost.addTab(tabSpec);

        //tabHost.newTabSpec("average_price_my_lots");
        //tabSpec.setContent(R.id.my_lots);
        //tabSpec.setIndicator("Мои лоты");
        //tabHost.addTab(tabSpec);

        //tabHost.setCurrentTab(0);
    }
}
