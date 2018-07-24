package com.jiang.guideframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jiang.frame.Guide;
import com.jiang.frame.GuidePage;

public class MultiActivity extends AppCompatActivity {
    private Guide guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        findViewById(R.id.btn_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide = Guide.with(MultiActivity.this)
                        .label("multi")
                        .alwaysShow(true)
                        .anchor(null)
                        .addPage(GuidePage.create()
                                .addLight(v)
                                .arbitrary(true)
                                .setLayoutResource(R.layout.view_guide_simple))
                        .addPage(GuidePage.create()
                                .addLight(v)
                                .arbitrary(true)
                                .setLayoutResource(R.layout.view_guide_changeless))
                        .build()
                        .show();
            }
        });
    }
}
