package com.shebut_dev.tele2marketreinvented.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.presentation.activity.MainActivity;

public class ApprovalFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_approval, container,false);
        MainActivity baseActivity = (MainActivity) getActivity();
        initInteractions(root, baseActivity);
        return root;
    }

    private void initInteractions(View root, MainActivity baseActivity) {
        Button close = root.findViewById(R.id.button_close);

        close.setOnClickListener(l ->
                baseActivity.navigateToMainScreen());
    }
}
