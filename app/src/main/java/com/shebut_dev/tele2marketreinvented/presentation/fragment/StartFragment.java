package com.shebut_dev.tele2marketreinvented.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.data.auth_manager.AuthManager;
import com.shebut_dev.tele2marketreinvented.data.auth_manager.impl.AuthManagerFirebaseImpl;
import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.data.model.UserModel;
import com.shebut_dev.tele2marketreinvented.presentation.activity.MainActivity;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class StartFragment extends Fragment {

    PhoneAuthCredential myPhoneAuthCredential;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks verCallbacks;


    String sessionVerificationID;

    private void init(){
        verCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                myPhoneAuthCredential = phoneAuthCredential;

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                e.printStackTrace();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Toast.makeText(getActivity(),"CODE HAS BEEN SENT", Toast.LENGTH_LONG)
                .show();
                sessionVerificationID = s;
                super.onCodeSent(s, forceResendingToken);
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_start, container, false);
        init();
        initInteractions(root);
        return root;
    }

    private void initInteractions(View root) {
        Button sendCode = root.findViewById(R.id.button_send_code);
        Button signIn = root.findViewById(R.id.button_sign_in);

        sendCode.setOnClickListener(l -> verifyPhoneNumber());
        signIn.setOnClickListener(l -> signInWithCredentials());
    }

    private void signInWithCredentials(){
        MainActivity baseActivity = (MainActivity) getActivity();
        EditText codeEditText = getActivity().findViewById(R.id.edit_text_code);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(
                sessionVerificationID,
                codeEditText.getText().toString()
        );
        baseActivity.getAuthManager().signInWithPhoneNumber(credential, new AuthManager.SignInWithPhoneNumberCallback() {
            @Override
            public void onSignInComplete(String currentUserID) {
                Toast.makeText(getActivity(), "HELLO" + currentUserID, Toast.LENGTH_LONG)
                        .show();
                baseActivity.getDataManager().postUser(new UserModel(baseActivity.getAuthManager().getCurrentUserID(),
                                10,
                                "Ivan",
                                baseActivity.getAuthManager().getCurrentUserPhoneNumber(),
                                new LinkedList<>()),
                        new DataManager.PostUserCallback() {
                            @Override
                            public void onFinish(String userID) {
                                baseActivity.navigateToMainScreen();
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
            }
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void verifyPhoneNumber(){
        EditText phoneEditText = getActivity().findViewById(R.id.edit_text_phone);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneEditText.getText().toString(),
                60,
                TimeUnit.SECONDS,
                getActivity(),
                verCallbacks
        );
    }
}
