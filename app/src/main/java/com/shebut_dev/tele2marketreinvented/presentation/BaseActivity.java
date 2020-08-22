package com.shebut_dev.tele2marketreinvented.presentation;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.shebut_dev.tele2marketreinvented.data.auth_manager.AuthManager;
import com.shebut_dev.tele2marketreinvented.data.auth_manager.impl.AuthManagerFirebaseImpl;
import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.DataManagerCustomBackendImpl;
import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.DataManagerFirebaseImpl;

public abstract class BaseActivity extends AppCompatActivity {

    protected AuthManager authManager;
    protected DataManager dataManager;
    protected abstract void initializeActivity(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity(savedInstanceState);
        authManager = new AuthManagerFirebaseImpl();
        dataManager = new DataManagerFirebaseImpl();
    }


    public void addFragment(int containerID, Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null);
        if (fragment != null) {
            fragmentTransaction.add(containerID, fragment);
        }else{
            Toast.makeText(this, "NULL_FRAGMENT", Toast.LENGTH_SHORT)
                    .show();
        }
        fragmentTransaction.commit();
    }

    public void replaceFragment(int containerID, Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null);
        if (fragment != null) {
            fragmentTransaction.replace(containerID, fragment);
        }else{
            Toast.makeText(this, "NULL_FRAGMENT", Toast.LENGTH_SHORT)
                    .show();
        }
        fragmentTransaction.commit();
    }
}
