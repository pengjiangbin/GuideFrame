package com.jiang.guideframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jiang.guideframe.guide.Guide;
import com.jiang.guideframe.guide.meta.GuidePage;

public class ChangelessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeless);
        final Button button = findViewById(R.id.btn_changeless);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guide.with(ChangelessActivity.this)
                        .label("btn")
                        .alwaysShow(true)
                        .anchor(null)
                        .addPage(GuidePage.create()
                                .addLight(button)
                                .setLayoutResource(R.layout.view_guide_changeless, R.id.btn_cancel))
                        .build()
                        .show();
            }
        });
    }
}
