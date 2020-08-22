package com.shebut_dev.tele2marketreinvented.data.auth_manager.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.shebut_dev.tele2marketreinvented.data.auth_manager.AuthManager;

public class AuthManagerFirebaseImpl implements AuthManager {
    private String LANGUAGE_CODE = "ru";
    private String currentUserID = "";
    private String currentUserPhoneNumber = "";


    private FirebaseAuth auth;


    public AuthManagerFirebaseImpl(){
        auth = FirebaseAuth.getInstance();
        auth.setLanguageCode(LANGUAGE_CODE);
    }

    @Override
    public void signInWithPhoneNumber(PhoneAuthCredential credential,
                                      final SignInWithPhoneNumberCallback callback) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            currentUserPhoneNumber = auth.getCurrentUser().getPhoneNumber();
                            currentUserID = auth.getCurrentUser().getUid();
                            Log.d("AUTH_MANAGER_FIREBASE", currentUserID);
                            callback.onSignInComplete(currentUserID);
                        }else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    @Override
    public String getCurrentUserID() {
        return currentUserID;
    }
    @Override
    public String getCurrentUserPhoneNumber() {
        return currentUserPhoneNumber;
    }
}
