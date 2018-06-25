package com.jiang.guideframe.guide.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;

public class ViewUtils {
    private static final String NO_SAVE_STATE_FRAMELAYOUT = "NoSaveStateFrameLayout";

    public static Rect getRectInParent(View parent, View child) {
        if (parent == null || child == null) {
            throw new IllegalArgumentException("parent or child can not be null");
        }
        Rect result = new Rect();
        if (parent == child) {
            child.getHitRect(result);
            return result;
        }
        View tmpView = child;
        Rect tmpRect = new Rect();
        View decorView = null;
        Context context = child.getContext();
        if (context instanceof Activity) {
            decorView = ((Activity) context).getWindow().getDecorView();
        }
        while (tmpView != parent && tmpView != decorView) {
            tmpView.getHitRect(tmpRect);
            if (!tmpView.getClass().equals(NO_SAVE_STATE_FRAMELAYOUT)) {
                result.left += tmpRect.left;
                result.top += tmpRect.top;
            }
            tmpView = (View) tmpView.getParent();
        }
        result.right = result.left + child.getMeasuredWidth();
        result.bottom = result.top + child.getMeasuredHeight();
        return result;
    }
}
