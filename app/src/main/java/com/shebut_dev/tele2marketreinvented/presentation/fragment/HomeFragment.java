package com.shebut_dev.tele2marketreinvented.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.DataManagerCustomBackendImpl;
import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.DataManagerFirebaseImpl;
import com.shebut_dev.tele2marketreinvented.data.model.UserModel;
import com.shebut_dev.tele2marketreinvented.presentation.activity.MainActivity;

public class HomeFragment extends Fragment {
    private String currentUserID;
    private TabHost tabHost;

    public HomeFragment(String currentUserID){
        this.currentUserID = currentUserID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initTabs(root);
        initInteractions(root);
        return root;
    }

    private void initInteractions(View root){
        MainActivity baseActivity = (MainActivity) getActivity();

        ImageButton button = root.findViewById(R.id.button_test_request);
        ImageButton button2 = root.findViewById(R.id.button_test_request_2);

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

    private void initRecyclerViews(View root){

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
