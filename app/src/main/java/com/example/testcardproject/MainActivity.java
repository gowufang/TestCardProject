package com.example.testcardproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.testcardproject.adapter.CardFragmentPagerAdapter;
import com.example.testcardproject.bean.QuestionInfo;
import com.example.testcardproject.presenter.TestPresenter;
import com.example.testcardproject.view.ITestView;
import com.example.testcardproject.view.SingleEmotionRainView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import nickgao.com.emotionrain.EmotionRainView;

public class MainActivity extends FragmentActivity implements ITestView {

    private ViewPager viewpager;
    private TextView tvBottomText;
    private CardFragmentPagerAdapter mAapter;
    private SingleEmotionRainView emotion_rain_view;
    private LinearLayout invite_layout;
//    private InviteHelper mInviteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //add comment
        viewpager = (ViewPager)findViewById(R.id.viewpager);
        tvBottomText = (TextView)findViewById(R.id.tv_bottom_text);
        invite_layout = (LinearLayout)findViewById(R.id.invite_layout);
        emotion_rain_view=findViewById(R.id.emotion_rain_view);
        TestPresenter presenter = new TestPresenter(this);
        presenter.getData();

//        mInviteHelper = new InviteHelper(this,invite_layout);
//        mInviteHelper.initInviteData();

        TextView rightBtn = (TextView)findViewById(R.id.btn_right);
//        rightBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mInviteHelper.hideInviteView();
//            }
//        });
    }

    public void startRain() {
        emotion_rain_view.start(getBitmaps());
    }

    public Bitmap getBitmaps() {
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.pic1);

        return bitmap;
    }


    @Override
    public void updateUI(List<QuestionInfo> list) {
        Collections.reverse(list);
        mAapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                list);
        viewpager.setAdapter(mAapter);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setCurrentItem(list.size()-1);

//        viewpager.setPageTransformer(true,new CardTransformer());
    }

    @Override
    public void setBottomTipView(String count) {
        tvBottomText.setText("恭喜答对"+count+"题");
    }
//
//    public void startRain() {
//        emotion_rain_view.start(getBitmaps());
//    }


//    @Override
//    public void setBottomTipView(String count) {
//        tvBottomText.setText("恭喜你累计答对"+count+"题");
//    }
}
