package com.jiang.frame.meta;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        Shape.CIRCLE,
        Shape.RECT,
        Shape.ROUND_RECT,
        Shape.OVAL
})
public @interface Shape {
    int CIRCLE = 0;
    int RECT = 1;
    int ROUND_RECT = 2;
    int OVAL = 3;
}
