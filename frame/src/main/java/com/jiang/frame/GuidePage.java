package com.jiang.frame;



import android.view.View;
import android.view.animation.Animation;

import com.jiang.frame.meta.Light;
import com.jiang.frame.meta.Page;
import com.jiang.frame.meta.Shape;

import java.util.ArrayList;
import java.util.List;

public class GuidePage implements Page {
    private int layoutId;
    private int backgroundColor;
    private boolean arbitraryCancel;
    private List<Light> lights = new ArrayList<>();
    private int[] cancelViews;
    private Animation enterAnimation;
    private Animation exitAnimation;

    public static Page create() {
        return new GuidePage();
    }


    @Override
    public Page setLayoutResource(int resId, int... cancelIds) {
        this.layoutId=resId;
        this.cancelViews=cancelIds;
        return this;
    }

    @Override
    public int getLayoutResource() {
        return layoutId;
    }


    @Override
    public int[] getCancelView() {
        return cancelViews;
    }

    @Override
    public Page addLight(View light) {
        addLight(light, Shape.RECT);
        return this;
    }

    @Override
    public Page addLight(View light, int shape) {
        addLight(light, shape, 0);
        return this;
    }

    @Override
    public Page addLight(View light, int shape, int padding) {
        addLight(light, shape, 0, padding);
        return this;
    }

    public Page addLight(View view, int shape, int corner, int padding) {
        if (lights == null) {
            lights = new ArrayList<>();
        }
        Light light = Light.create(view)
                .setShape(shape)
                .setCorner(corner)
                .setPadding(padding);
        lights.add(light);
        return this;
    }

    @Override
    public List<Light> getLight() {
        return lights;
    }

    @Override
    public Page setBackgroundColor(int color) {
        this.backgroundColor = color;
        return this;
    }

    @Override
    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public Page setEnterAnimation(Animation animation) {
        this.enterAnimation = animation;
        return this;
    }

    @Override
    public Animation getEnterAnimation() {
        return enterAnimation;
    }

    @Override
    public Page setExitAnimation(Animation animation) {
        this.exitAnimation = animation;
        return this;
    }

    @Override
    public Animation getExitAnimation() {
        return exitAnimation;
    }

    @Override
    public Page arbitrary(boolean arbitrary) {
        this.arbitraryCancel = arbitrary;
        return this;
    }

    @Override
    public boolean isArbitrary() {
        return arbitraryCancel;
    }

}
