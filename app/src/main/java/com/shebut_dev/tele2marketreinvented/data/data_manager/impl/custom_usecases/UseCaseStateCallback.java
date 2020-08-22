package com.shebut_dev.tele2marketreinvented.data.data_manager.impl.custom_usecases;

public interface UseCaseStateCallback {

    void onPreExecute();
    void onFinish(String result);
}
