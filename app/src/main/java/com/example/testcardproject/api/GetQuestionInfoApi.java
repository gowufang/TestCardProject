package com.example.testcardproject.api;

import android.util.Log;

import com.example.testcardproject.CardContants;
import com.example.testcardproject.bean.QuestionInfo;
import com.google.gson.Gson;
import com.http.api.ApiUtil;
//import com.imooc.nick.cardtestproject.CardContants;
//import com.imooc.nick.cardtestproject.bean.QuestionInfo;

import org.json.JSONObject;

public class GetQuestionInfoApi extends ApiUtil {

    public QuestionInfo mInfo;
    @Override
    protected String getUrl() {
        return CardContants.URL+"/question.json";
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        try {
            JSONObject data =  jsonObject.optJSONObject("data");
            JSONObject info =  data.optJSONObject("info");

            mInfo = new Gson().fromJson(info.toString(),QuestionInfo.class);
            Log.d("GetQuestionInfoApi",mInfo.title);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
