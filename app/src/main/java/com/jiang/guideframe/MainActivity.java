package com.jiang.guideframe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jiang.guideframe.guide.Guide;
import com.jiang.guideframe.guide.meta.GuidePage;

public class MainActivity extends AppCompatActivity {

    private Guide guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnDefault = findViewById(R.id.btn_default);
        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide = Guide.with(MainActivity.this)
                        .label("btn")
                        .alwaysShow(true)
                        .anchor(null)
                        .addPage(GuidePage.create()
                                .addLight(btnDefault)
                                .arbitrary(true)
                                .setLayoutResource(R.layout.view_guide_simple))
                        .build()
                        .show();
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        guide.dismiss();
    }
}
