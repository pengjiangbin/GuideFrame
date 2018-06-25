package com.jiang.guideframe.guide;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import com.jiang.guideframe.guide.meta.Light;
import com.jiang.guideframe.guide.meta.Page;
import com.jiang.guideframe.guide.meta.Shape;

import java.util.List;

public class Frame extends FrameLayout {
    private static final int DEFAULT_BACKGROUND = 0x70000000;
    private Page page;
    private Xfermode xfermode;
    private Paint paint;

    public Frame(@NonNull Context context, Page page) {
        super(context);
        this.page = page;
        init();

    }

    public Frame(@NonNull Context context) {
        super(context);
    }

    public Frame(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Frame(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.INNER));
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setWillNotDraw(false);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page.isArbitrary()){
                    dismiss();
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawColor(page.getBackgroundColor() != 0 ?
                page.getBackgroundColor() : DEFAULT_BACKGROUND);
        paint.setXfermode(xfermode);
        drawLight(canvas);
        paint.setXfermode(null);
        canvas.restoreToCount(saved);
    }

    private void drawLight(Canvas canvas) {
        List<Light> lights = page.getLight();
        if (lights == null) {
            return;
        }
        for (Light light : lights) {
            RectF rectF = light.getRectInParent((View) getParent());
            switch (light.getShape()) {
                case Shape.CIRCLE:
                    canvas.drawCircle(rectF.centerX(), rectF.centerY(), light.getRadius(), paint);
                    break;
                case Shape.RECT:
                    canvas.drawRect(rectF, paint);
                    break;
                case Shape.ROUND_RECT:
                    canvas.drawRoundRect(rectF, light.getCorner(), light.getCorner(), paint);
                    break;
                case Shape.OVAL:
                    canvas.drawOval(rectF, paint);
                    break;
                default:
                    canvas.drawRect(rectF, paint);
                    break;

            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Animation enterAnimation = page.getEnterAnimation();
        if (enterAnimation != null) {
            startAnimation(enterAnimation);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


    public void dismiss() {
        Animation exitAnimation = page.getExitAnimation();
        if (exitAnimation != null) {
            exitAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    remove();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            startAnimation(exitAnimation);
        }else{
            remove();
        }
    }

    private void remove() {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
    }
}
