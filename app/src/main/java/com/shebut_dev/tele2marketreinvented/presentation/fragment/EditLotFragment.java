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

public class EditLotFragment extends Fragment {
    private String lotID;

    private double shownPrice;
    private int shownCount = 1;

    public EditLotFragment(String lotID){
        this.lotID = lotID;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_lot, container, false);
        MainActivity baseActivity = (MainActivity) getActivity();
        initInteractions(root, baseActivity);
        updateTextFields(root);
        initLot(root, baseActivity);
        return root;
    }

    private void initLot(View root, MainActivity baseActivity){
        RadioGroup valueType = root.findViewById(R.id.value_type_radio);
        RadioGroup lotType = root.findViewById(R.id.lot_type_radio);

        baseActivity.getDataManager().getLot(
                baseActivity.getAuthManager().getCurrentUserID(),
                lotID,
                new DataManager.GetLotCallback() {
                    @Override
                    public void onFinish(LotModel lotModel) {

                        shownCount = lotModel.nominal;
                        shownPrice = lotModel.price;
                        updateTextFields(root);
                        int valueTypeNum = lotModel.itemType.equals("GB") ? R.id.radio_button_gb :
                                lotModel.itemType.equals("MIN")  ? R.id.radio_button_min :
                                        lotModel.itemType.equals("SMS")  ? R.id.radio_button_min : 0;
                        int lotTypeNum = lotModel.lotType.equals("SELL") ? R.id.radio_button_sell :
                                lotModel.lotType.equals("BUY") ? R.id.radio_button_buy : 0;

                        valueType.check(valueTypeNum);
                        lotType.check(lotTypeNum);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }
    private void updateTextFields(View root) {
        TextView count = root.findViewById(R.id.current_shown_count);
        TextView price = root.findViewById(R.id.current_shown_price);

        count.setText(shownCount + " Гб");
        price.setText(shownPrice + " " + getResources().getString(R.string.extra_t_symbol));

    }


    private void initInteractions(View root, MainActivity baseActivity) {
        ImageButton backToHome = root.findViewById(R.id.button_back_to_home_screen);
        Button editLot = root.findViewById(R.id.button_edit_lot);

        ImageButton increase = root.findViewById(R.id.button_increase_count);
        ImageButton decrease = root.findViewById(R.id.button_decrease_count);

        increase.setOnClickListener(l -> {
            shownCount++;
            updateTextFields(root);
        });
        decrease.setOnClickListener(l -> {
            if (shownCount != 0) shownCount--;
            updateTextFields(root);
        });

        backToHome.setOnClickListener(l ->
                baseActivity.navigateToMainScreen());
        editLot.setOnClickListener(l ->
                editLot(root, baseActivity));
    }

    private void editLot(View root, MainActivity baseActivity) {
        RadioGroup valueType = root.findViewById(R.id.value_type_radio);
        RadioGroup lotType = root.findViewById(R.id.lot_type_radio);
        LotModel lot = new LotModel();

        Date d = new Date(System.currentTimeMillis());
        lot.creationDate = d.toString();
        lot.nominal = shownCount;
        lot.price = shownPrice;
        lot.lotID = lotID;

        switch (lotType.getCheckedRadioButtonId()) {
            case R.id.radio_button_sell:
                lot.lotType = "SELL";
                break;
            case R.id.radio_button_buy:
                lot.lotType = "BUY";
                break;
        }

        switch (valueType.getCheckedRadioButtonId()) {
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

        baseActivity.getDataManager().editLot(baseActivity.getAuthManager().getCurrentUserID(), lot,
                new DataManager.EditLotCallback() {
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
