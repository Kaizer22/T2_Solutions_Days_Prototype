package com.shebut_dev.tele2marketreinvented.presentation.fragment;

import android.os.Bundle;
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

import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.data.model.GBDailyStatsModel;
import com.shebut_dev.tele2marketreinvented.data.model.LotModel;
import com.shebut_dev.tele2marketreinvented.presentation.activity.MainActivity;

import java.util.Date;
import java.util.Random;

public class AddLotFragment extends Fragment {

    private double shownPrice;
    private int shownCount = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_lot, container, false);
        MainActivity baseActivity = (MainActivity) getActivity();
        initAverageMarketPrice(root, baseActivity);
        initInteractions(root, baseActivity);
        updateTextFields(root);
        return root;
    }

    private void updateTextFields(View root){
        TextView count = root.findViewById(R.id.current_shown_count);
        TextView price = root.findViewById(R.id.current_shown_price);

        count.setText(shownCount + " Гб");
        price.setText(shownPrice + " " + getResources().getString(R.string.extra_t_symbol));

    }

    private void initAverageMarketPrice(View root, MainActivity baseActivity){
        baseActivity.getDataManager().getGbStatistics(new DataManager.GetGbStatisticsCallback() {
            @Override
            public void onFinish(GBDailyStatsModel gbDailyStatsModel) {
                shownPrice = gbDailyStatsModel.currentAveragePrice;
                updateTextFields(root);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void initInteractions(View root, MainActivity baseActivity){
        ImageButton backToHome = root.findViewById(R.id.button_back_to_home_screen);
        Button createLot = root.findViewById(R.id.button_create_lot);

        ImageButton increase = root.findViewById(R.id.button_increase_count);
        ImageButton decrease = root.findViewById(R.id.button_decrease_count);

        increase.setOnClickListener(l -> {shownCount++; updateTextFields(root);});
        decrease.setOnClickListener(l -> { if(shownCount !=  0) shownCount--;
                                            updateTextFields(root);});

        backToHome.setOnClickListener(l ->
                baseActivity.navigateToMainScreen());
        createLot.setOnClickListener(l ->
                createLot(root, baseActivity));
    }

    private void createLot(View root, MainActivity baseActivity) {
        Random random = new Random();
        RadioGroup valueType = root.findViewById(R.id.value_type_radio);
        RadioGroup lotType = root.findViewById(R.id.lot_type_radio);
        LotModel lot = new LotModel();

        Date d = new Date(System.currentTimeMillis());
        lot.creationDate = d.toString();
        lot.nominal = shownCount;
        lot.price = shownPrice;
        lot.lotID = "lot" + random.nextInt(1000000) ;

        switch (lotType.getCheckedRadioButtonId()){
            case R.id.radio_button_sell:
                lot.lotType = "SELL";
                break;
            case R.id.radio_button_buy:
                lot.lotType = "BUY";
                break;
        }

        switch (valueType.getCheckedRadioButtonId()){
            case R.id.radio_button_gb:
                lot.itemType = "GB";
                break;
            case R.id.radio_button_min:
                lot.itemType = "MIN";
                break;
            case R.id.radio_button_sms:
                lot.itemType = "SMS";
                break;
        }

        baseActivity.getDataManager().postLot(baseActivity.getAuthManager().getCurrentUserID(), lot,
                new DataManager.PostLotCallback() {
                    @Override
                    public void onFinish() {
                        baseActivity.navigateToApproval();
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
