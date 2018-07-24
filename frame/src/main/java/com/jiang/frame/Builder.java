package com.jiang.frame;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jiang.frame.listener.OnPageChangedListener;
import com.jiang.frame.listener.OnStateChangedListener;
import com.jiang.frame.meta.Page;

import java.util.ArrayList;
import java.util.List;

public class Builder {
    protected String label;
    protected boolean always;
    protected int showCount;
    protected List<Page> pages = new ArrayList<>();
    protected View anchor;
    protected OnPageChangedListener pageChangedListener;
    protected OnStateChangedListener stateChangedListener;
    protected Activity activity;
    protected Fragment fragment;

    Builder(Activity activity) {
        this.activity = activity;
    }

    Builder(Fragment fragment) {
        this.fragment = fragment;
        this.activity = fragment.getActivity();
    }

    public Builder alwaysShow(boolean always) {
        this.always = always;
        return this;
    }

    public Builder label(String label) {
        this.label = label;
        return this;
    }

    public Builder showCount(int count) {
        this.showCount = count;
        return this;
    }

    public Builder addPage(Page page) {
        this.pages.add(page);
        return this;
    }

    public Builder anchor(View anchor) {
        this.anchor = anchor;
        return this;
    }

    public Builder setOnPageChangedListener(OnPageChangedListener listener) {
        this.pageChangedListener = listener;
        return this;
    }

    public Builder setOnStateChangedListener(OnStateChangedListener listener) {
        this.stateChangedListener = listener;
        return this;
    }

    public Guide build() {
        return new Guide(this);
    }
}
