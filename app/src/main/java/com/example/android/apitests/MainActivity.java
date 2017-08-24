package com.example.android.apitests;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SendMessageServer sendMessageServer = new SendMessageServer();
        sendMessageServer.execute();
        try {
            String respons = sendMessageServer.get();
            if (respons == null) {
             Log.e("error","fatal");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Log.e("CATEG "," список " +categ.toString());
    }

    class SendMessageServer extends AsyncTask<Void, Void, String> {
        String message;

//        protected String getStatus(String key, String strJson) {
//            JSONObject dataJsonObj = null;
//            String secondName = "";
//            try {
//                dataJsonObj = new JSONObject(strJson);
//                secondName = dataJsonObj.getString(key);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return secondName;
//        }


        protected String doInBackground(Void... bitmam) {

            try {


                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .addEncoded("login", "LoginTest")
                        .addEncoded("pass", "passTest")
                        .addEncoded("name", "NameTast")
                        .addEncoded("family", "FamilyTest")
                        .addEncoded("city", "CityTest")
                        .addEncoded("tel", "00000000")
                        .build();

                Request request = new Request.Builder()
                        .url("http://9834436605.myjino.ru/api/add-post")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .post(formBody)
                        .build();

                okhttp3.Call call = client.newCall(request);
                Response response = call.execute();
                Callback callback = new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                    }
                };

                callback.onResponse(call, response);
                message = response.body().string().trim();

                Log.e("Server send Message  ", message);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return message;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);

        }
    }


}



