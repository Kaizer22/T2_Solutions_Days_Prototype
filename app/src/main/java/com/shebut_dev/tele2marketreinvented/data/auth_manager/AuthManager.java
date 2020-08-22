package com.shebut_dev.tele2marketreinvented.data.auth_manager;

import com.google.firebase.auth.PhoneAuthCredential;

public interface AuthManager {

    interface SignInWithPhoneNumberCallback {
        void onSignInComplete(String currentUserID);
        void onError(Exception e);
    }


    void signInWithPhoneNumber(PhoneAuthCredential credential,
                               SignInWithPhoneNumberCallback callback);

    String getCurrentUserID();
    String getCurrentUserPhoneNumber();
}
