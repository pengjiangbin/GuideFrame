package com.jiang.guideframe.guide;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class Guide {
    private Builder builder;
    private GuideDelegate guideDelegate;

    public static Builder with(Activity activity) {
        return new Builder(activity);
    }

    public static Builder with(Fragment fragment) {
        return new Builder(fragment);
    }

    private GuideDelegate getDelegate() {
        if (guideDelegate == null) {
            guideDelegate = new GuideDelegate(builder);
        }
        return guideDelegate;
    }


    Guide(Builder builder) {
        this.builder = builder;
        guideDelegate = new GuideDelegate(builder);
    }

    public Guide show() {
        getDelegate().show();
        return this;
    }

    public Guide showPage(int position) {
        getDelegate().showPage(position);
        return this;
    }

    public Guide dismiss(){
        getDelegate().dismiss();
        return this;
    }

}
