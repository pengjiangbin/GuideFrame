package com.jiang.guideframe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jiang.frame.Guide;
import com.jiang.frame.GuidePage;

public class MainActivity extends AppCompatActivity {

    private Guide guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Animation enterAnimation = new AlphaAnimation(0f, 1f);
        enterAnimation.setDuration(600);
        enterAnimation.setFillAfter(true);
        final LinearLayout linearLayout = findViewById(R.id.ll_parent);
        final Animation exitAnimation = new AlphaAnimation(1f, 0f);
        exitAnimation.setDuration(600);
        exitAnimation.setFillAfter(true);
        final Button btnDefault = findViewById(R.id.btn_default);
        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide = Guide.with(MainActivity.this)
                        .label("btn")
                        .alwaysShow(true)
//                        .showCount(2)
                        .anchor(linearLayout)
                        .addPage(GuidePage.create()
                                .addLight(btnDefault)
                                .arbitrary(true)
                                .setLayoutResource(R.layout.view_guide_simple)
                                .setEnterAnimation(enterAnimation)
                                .setExitAnimation(exitAnimation))
                        .build()
                        .show();
                if (!guide.enable()) {
                    Toast.makeText(MainActivity.this, "引导结束", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btn_anchor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnchorActivity.class));
            }
        });

        findViewById(R.id.btn_changeless).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChangelessActivity.class));
            }
        });
        findViewById(R.id.btn_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MultiActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        guide.dismiss();
    }
}
