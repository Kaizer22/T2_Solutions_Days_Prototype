package com.shebut_dev.tele2marketreinvented.data.data_manager.impl.custom_usecases.get;

import android.os.AsyncTask;
import android.util.Log;

import com.shebut_dev.tele2marketreinvented.data.data_manager.impl.custom_usecases.UseCaseStateCallback;
import com.shebut_dev.tele2marketreinvented.data.network.NetworkHelper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GetGbStatisticsUseCase  extends AsyncTask<String, String, String> {
    UseCaseStateCallback stateCallback;
    NetworkHelper helper;
    String result;

    public GetGbStatisticsUseCase(UseCaseStateCallback callback){
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
        URL url = null;
        result = "got nothing useful";
        try {
            url = new URL("https://www.android.com/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream((urlConnection.getInputStream()));
                //TODO трансформировать в JSONArray
                Scanner sc = new Scanner(in).useDelimiter("\\A");
                result = sc.hasNext() ? sc.next() : "";
            }finally {

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("GET_US_USE_CASE", result);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        stateCallback.onFinish(result);
        super.onPostExecute(s);
    }
}