package com.example.testcardproject.transform;

import android.os.Build;

import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

public class CardTransformer implements ViewPager.PageTransformer {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void transformPage(View page, float position) {
        if(position < 0) {
            page.setTranslationX(-position*page.getWidth());
            page.setTranslationZ(position);

            float scale = (page.getWidth()+40*position)/page.getWidth();
            page.setScaleY(scale);
            page.setScaleX(scale);

            page.setTranslationY(-position*40);
        }
    }
}
