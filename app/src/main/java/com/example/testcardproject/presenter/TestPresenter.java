package com.example.testcardproject.presenter;

import android.content.Context;
import android.util.Log;

import com.example.testcardproject.api.GetQuestionInfoApi;
import com.example.testcardproject.api.HistoryQuestionGetApi;
import com.example.testcardproject.api.QuestionSaveApi;
import com.example.testcardproject.bean.QuestionInfo;
import com.example.testcardproject.view.ITestView;
import com.google.gson.Gson;
import com.http.Util.Util;
import com.http.api.ApiListener;
import com.http.api.ApiUtil;
//import com.imooc.nick.cardtestproject.api.GetQuestionInfoApi;
//import com.imooc.nick.cardtestproject.api.HistoryQuestionGetApi;
//import com.imooc.nick.cardtestproject.bean.QuestionInfo;
//import com.imooc.nick.cardtestproject.db.QuestionDbController;
//import com.imooc.nick.cardtestproject.view.ITestView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestPresenter {

    private ITestView mITestView;
    private Context mContext;
    private List<QuestionInfo> mHistoryList;
    private QuestionInfo mCurrentInfo;

    public TestPresenter(ITestView iTestView) {
        mContext = (Context)iTestView;
        mITestView = iTestView;
    }

    public void getData() {
        //todo get all data
        if(Util.hasNetwork(mContext)) {
            getHistory();
            //sendRequestWithOkHttp();
        }else{
            refreshData();
        }
    }


    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
// 指定访问的服务器地址是电脑本机
                            .url("http://10.77.36.177/question.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("sdfsssaf",responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void getHistory(){


        new HistoryQuestionGetApi().get(mContext, new ApiListener() {
            @Override
            public void success(ApiUtil api) {

                HistoryQuestionGetApi  apiBase= (HistoryQuestionGetApi) api;
                mHistoryList=apiBase.list;
                getCurrentQuestionApi();
            }

            @Override
            public void failure(ApiUtil api) {

            }
        });
    }

    private void getCurrentQuestionApi(){
        new GetQuestionInfoApi().get(mContext, new ApiListener() {
            @Override
            public void success(ApiUtil api) {
                GetQuestionInfoApi apiBase= (GetQuestionInfoApi) api;
                mCurrentInfo=apiBase.mInfo;
                mHistoryList.add(0,mCurrentInfo);
                Log.d("sdfaf",mCurrentInfo.title);
                refreshData();
            }

            @Override
            public void failure(ApiUtil api) {

            }
        });
    }

    public void refreshData(){
        if (mITestView!=null){
            mITestView.updateUI(mHistoryList);
        }

    }

}
