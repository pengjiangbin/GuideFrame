package com.jiang.frame.meta;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import com.jiang.frame.util.ViewUtils;

public class Light {
    private View lightView;
    private int shape = Shape.RECT;
    private int corner;
    private int padding;


    public static Light create(View lightView) {
        return new Light(lightView);
    }

    private Light(View lightView) {
        this.lightView = lightView;
    }

    public View getLightView() {
        return lightView;
    }

    public void setLightView(View lightView) {
        this.lightView = lightView;
    }

    public int getShape() {
        return shape;
    }

    public int getCorner() {
        return corner;
    }

    public int getPadding() {
        return padding;
    }

    public Light setShape(@Shape int shape) {
        this.shape = shape;
        return this;
    }

    public Light setCorner(int corner) {
        this.corner = corner;
        return this;
    }

    public Light setPadding(int padding) {
        this.padding = padding;
        return this;
    }

    public RectF getRectInParent(View parent) {
        RectF rectF = new RectF();
        Rect rect = ViewUtils.getRectInParent(parent, lightView);
        rectF.left = rect.left - padding;
        rectF.top = rect.top - padding;
        rectF.right = rect.right + padding;
        rectF.bottom = rect.bottom + padding;
        return rectF;
    }

    public int getRadius() {
        if (lightView == null) {
            throw new IllegalArgumentException("lightView can not be null");
        }
        return Math.max(lightView.getWidth() / 2, lightView.getHeight() / 2) + padding;
    }

}
