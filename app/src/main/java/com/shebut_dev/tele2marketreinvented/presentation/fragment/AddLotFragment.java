package com.shebut_dev.tele2marketreinvented.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.presentation.activity.MainActivity;

public class AddLotFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_lot, container, false);
        MainActivity baseActivity = (MainActivity) getActivity();
        initInteractions(root, baseActivity);
        return root;
    }

    private void initInteractions(View root, MainActivity baseActivity){
        ImageButton backToHome = root.findViewById(R.id.button_back_to_home_screen);
        Button createLot = root.findViewById(R.id.button_create_lot);

        backToHome.setOnClickListener(l ->
                baseActivity.navigateToMainScreen());
        createLot.setOnClickListener(l ->
                baseActivity.navigateToApproval());
    }
}
