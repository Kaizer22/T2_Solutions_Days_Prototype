package com.shebut_dev.tele2marketreinvented.data.data_manager.impl.custom_usecases.get;

import android.os.AsyncTask;
import android.util.Log;

import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.custom_usecases.UseCaseStateCallback;
import com.shebut_dev.tele2marketreinvented.data.network.CustomUrlProvider;
import com.shebut_dev.tele2marketreinvented.data.network.NetworkHelper;

public class GetUserByIdUseCase extends AsyncTask<String, String, String> {
    UseCaseStateCallback stateCallback;
    NetworkHelper helper;
    String result;

    public GetUserByIdUseCase(UseCaseStateCallback callback){
        stateCallback = callback;
        helper = new NetworkHelper();
    }

    @Override
    protected void onPreExecute() {
        stateCallback.onPreExecute();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        //TODO трансформировать в JSONArray
        result = NetworkHelper.requestGET(CustomUrlProvider.GET_USER_BY_ID_URL);
        Log.d("GET_US_USE_CASE", result);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        stateCallback.onFinish(result);
        super.onPostExecute(s);
    }
}
