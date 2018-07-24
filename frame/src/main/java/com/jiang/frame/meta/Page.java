package com.jiang.frame.meta;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.animation.Animation;

import java.util.List;

public interface Page {

    Page setLayoutResource(@LayoutRes int resId,int... cancelIds);

    int getLayoutResource();


    int[] getCancelView();

    Page addLight(View light);

    Page addLight(View light, @Shape int shape);

    Page addLight(View light, @Shape int shape, int padding);

    Page addLight(View light, @Shape int shape, int corner, int padding);

    List<Light> getLight();

    Page setBackgroundColor(int color);

    int getBackgroundColor();

    Page setEnterAnimation(Animation animation);

    Animation getEnterAnimation();

    Page setExitAnimation(Animation animation);

    Animation getExitAnimation();

    Page arbitrary(boolean arbitrary);

    boolean isArbitrary();
}
