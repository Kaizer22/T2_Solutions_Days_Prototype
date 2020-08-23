package com.shebut_dev.tele2marketreinvented.presentation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
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
import com.shebut_dev.tele2marketreinvented.data.model.DayPointModel;
import com.shebut_dev.tele2marketreinvented.data.model.GBDailyStatsModel;
import com.shebut_dev.tele2marketreinvented.data.model.LotModel;
import com.shebut_dev.tele2marketreinvented.data.model.MinDailyStatsModel;
import com.shebut_dev.tele2marketreinvented.data.model.SMSDailyStatsModel;
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
        initTabs(root);
        initInteractions(root, baseActivity);
        initRecyclerView(root, baseActivity);
        return root;
    }

    private void initInteractions(View root, MainActivity baseActivity){

        ImageButton button = root.findViewById(R.id.button_test_request);
        Button toLotCreation = root.findViewById(R.id.button_to_create_lot);

        RadioGroup radioGroup = root.findViewById(R.id.value_type_radio);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            initStats(checkedId,
                    root, baseActivity);
        });
        radioGroup.check(R.id.radio_button_gb);
        initStats(R.id.radio_button_gb, root, baseActivity);

        toLotCreation.setOnClickListener(l -> {
            baseActivity.navigateToCreateLot();
        });

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



    private void initStats(int checkedRadioButton, View root, MainActivity baseActivity){
        TextView averagePrice = root.findViewById(R.id.average_price);
        TextView totalLotCount = root.findViewById(R.id.total_lot_count);
        TextView totalAmount = root.findViewById(R.id.total_amount);

        switch (checkedRadioButton){
            case R.id.radio_button_gb:{
                baseActivity.getDataManager().getGbStatistics(new DataManager.GetGbStatisticsCallback() {
                    @Override
                    public void onFinish(GBDailyStatsModel gbDailyStatsModel) {
                        averagePrice.setText(String.valueOf(gbDailyStatsModel.currentAveragePrice));
                        totalLotCount.setText(String.valueOf(gbDailyStatsModel.currentLotsCount));
                        totalAmount.setText(String.valueOf(gbDailyStatsModel.currentGBAmount));
                        initGraph(root, gbDailyStatsModel.monthlyTimeline);
                        Log.d("TEST", String.valueOf(gbDailyStatsModel.monthlyTimeline.get(0).number));
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
            }
            case R.id.radio_button_min:{
                baseActivity.getDataManager().getMinStatistics(new DataManager.GetMinStatisticsCallback() {
                    @Override
                    public void onFinish(MinDailyStatsModel smsDailyStatsModel) {
                        averagePrice.setText(String.valueOf(smsDailyStatsModel.currentAveragePrice));
                        totalLotCount.setText(String.valueOf(smsDailyStatsModel.currentLotsCount));
                        totalAmount.setText(String.valueOf(smsDailyStatsModel.currentMINAmount));
                        initGraph(root, smsDailyStatsModel.monthlyTimeline);
                        Log.d("TEST", String.valueOf(smsDailyStatsModel.monthlyTimeline.get(0).number));
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
            }
            case R.id.radio_button_sms:{
                baseActivity.getDataManager().getSMSStatistics(new DataManager.GetSMSStatisticsCallback() {
                    @Override
                    public void onFinish(SMSDailyStatsModel smsDailyStatsModel) {
                        averagePrice.setText(String.valueOf(smsDailyStatsModel.currentAveragePrice));
                        totalLotCount.setText(String.valueOf(smsDailyStatsModel.currentLotsCount));
                        totalAmount.setText(String.valueOf(smsDailyStatsModel.currentSMSAmount));
                        initGraph(root, smsDailyStatsModel.monthlyTimeline);
                        Log.d("TEST", String.valueOf(smsDailyStatsModel.monthlyTimeline.get(0).number));
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
            }
        }


    }
    private void initRecyclerView(View root, MainActivity baseActivity){
        RecyclerView lotsList = root.findViewById(R.id.my_lots_list);
        lotsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyLotsAdapter lotsAdapter = new MyLotsAdapter(getContext());
        lotsList.setAdapter(lotsAdapter);
        baseActivity.getDataManager().getUserLots(currentUserID, new DataManager.GetUserLotsCallback() {
            @Override
            public void onFinish(List<LotModel> lotModels) {
                lotsAdapter.setLotModels(lotModels);

            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });

        lotsList.setAdapter(lotsAdapter);

    }

    private void initGraph(View root, List<DayPointModel> timeline){
        GraphView graphView = root.findViewById(R.id.average_price_graph);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScrollable(true);
        LineGraphSeriesMapper mapper = new LineGraphSeriesMapper();
        LineGraphSeries<DataPoint> data = mapper.map2((ArrayList<DayPointModel>) timeline);
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
