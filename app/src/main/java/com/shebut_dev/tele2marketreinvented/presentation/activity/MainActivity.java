package com.shebut_dev.tele2marketreinvented.presentation.activity;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.data.auth_manager.AuthManager;
import com.shebut_dev.tele2marketreinvented.data.auth_manager.impl.AuthManagerFirebaseImpl;
import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.presentation.BaseActivity;
import com.shebut_dev.tele2marketreinvented.presentation.fragment.HomeFragment;
import com.shebut_dev.tele2marketreinvented.presentation.fragment.StartFragment;

import java.util.concurrent.TimeUnit;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        addFragment(R.id.main_container, new StartFragment());
    }

    public void navigateToMainScreen(){
        replaceFragment(R.id.main_container, new HomeFragment(
                authManager.getCurrentUserID()));
    }

    public AuthManager getAuthManager(){
        return authManager;
    }

    public DataManager getDataManager(){
        return dataManager;
    }
}