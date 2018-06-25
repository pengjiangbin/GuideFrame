package com.jiang.guideframe.guide;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jiang.guideframe.guide.listener.OnPageChangedListener;
import com.jiang.guideframe.guide.listener.OnStateChangedListener;
import com.jiang.guideframe.guide.meta.Page;

import java.util.List;

public class GuideDelegate {
    private static final String SP_NAME = "guide_frame";
    private Builder builder;
    private String label;
    private boolean always;
    private int showCount;
    private boolean arbitraryCancel;
    private List<Page> pages;
    private View anchorView;
    private Frame currentFrame;
    private FrameLayout parentView;
    private SharedPreferences sp;
    private OnPageChangedListener pageChangedListener;
    private OnStateChangedListener stateChangedListener;
    private Activity activity;
    private Fragment fragment;
    private int childIndex = -1;
    private int currentPage = 0;
    private boolean defaultAnchor = false;//默认锚点为decorView

    public GuideDelegate(Builder builder) {
        this.builder = builder;
        this.label = builder.label;
        this.always = builder.always;
        this.showCount = builder.showCount;
        this.pages = builder.pages;
        this.pageChangedListener = builder.pageChangedListener;
        this.stateChangedListener = builder.stateChangedListener;
        this.activity = builder.activity;
        View anchor = builder.anchor;
        if (anchor == null) {
            anchor = activity.getWindow().getDecorView();
            defaultAnchor = true;
        }
        if (anchor instanceof FrameLayout) {
            parentView = (FrameLayout) anchor;
        } else {
            FrameLayout frameLayout = new FrameLayout(activity);
            ViewGroup parent = (ViewGroup) anchor.getParent();
            childIndex = parent.indexOfChild(anchor);
            parent.removeView(anchor);
            if (childIndex >= 0) {
                parent.addView(frameLayout, childIndex, anchor.getLayoutParams());
            } else {
                parent.addView(frameLayout, anchor.getLayoutParams());
            }
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.addView(anchor, params);
            parentView = frameLayout;
        }
        sp = activity.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public void show() {
        final int showedCount = sp.getInt(label, 0);
        if (!always) {
            if (showedCount >= showCount) {
                return;
            }
        }
        parentView.post(new Runnable() {
            @Override
            public void run() {
                if (pages == null || pages.size() == 0) {
                    throw new IllegalStateException("no page to show");
                }
                currentPage = 0;
                showPage();
                if (stateChangedListener != null) {
                    stateChangedListener.show();
                }
                sp.edit().putInt(label, showedCount + 1).apply();
            }
        });
    }

    private void showPage() {
        Page page = pages.get(currentPage);
        Frame frame = new Frame(activity, page);
        addContentToLayout(frame, page);
        parentView.addView(frame, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        currentFrame = frame;
        if (pageChangedListener != null) {
            pageChangedListener.onPageChange();
        }
    }

    public void addContentToLayout(final Frame frame, Page page) {
        frame.removeAllViews();
        int layoutId = page.getLayoutResource();
        if (layoutId == 0) {
            throw new IllegalArgumentException("layout resource can not be null");
        }
        View view = LayoutInflater.from(activity).inflate(layoutId, frame, false);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        int[] cancelIds = page.getCancelView();
        if (cancelIds != null && cancelIds.length > 0) {
            for (int cancelId : cancelIds) {
                View cancelView = view.findViewById(cancelId);
                if (cancelView != null) {
                    cancelView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            frame.dismiss();
                        }
                    });
                }
            }
        }
        frame.addView(view, params);


    }

    public void showPage(int position) {
        if (position < 0 || position > pages.size() - 1) {
            throw new IllegalArgumentException("position need in page's range");
        }
        currentPage = position;
    }

    public void dismiss() {

    }


    public void interrupted() {
        if (currentFrame != null && currentFrame.getParent() != null) {
            ViewGroup parent = (ViewGroup) currentFrame.getParent();
            parent.removeView(currentFrame);
            if (!(parent instanceof FrameLayout)) {
                ViewGroup viewGroup = (ViewGroup) parent.getParent();
                View anchor = parent.getChildAt(0);
                parent.removeAllViews();
                if (anchor != null) {
                    if (childIndex < 0) {
                        viewGroup.addView(anchor, parent.getLayoutParams());
                    } else {
                        viewGroup.addView(anchor, childIndex, parent.getLayoutParams());
                    }

                }
            }
        }
    }

}
