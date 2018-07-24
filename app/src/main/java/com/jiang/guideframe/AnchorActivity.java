package com.jiang.guideframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiang.frame.Guide;
import com.jiang.frame.GuidePage;

public class AnchorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anchor);
        final Button btnAnchor = findViewById(R.id.btn_anchor);
        final LinearLayout anchor = findViewById(R.id.anchor);
        final TextView tvAnchor = findViewById(R.id.tv_anchor);
        btnAnchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<anchor.getChildCount();i++){
                    Log.d("MainActivity", anchor.getChildAt(i).toString());
                }
                Guide.with(AnchorActivity.this)
                        .label("btn")
                        .alwaysShow(true)
                        .anchor(tvAnchor)
                        .addPage(GuidePage.create()
                                .addLight(btnAnchor)
                                .arbitrary(true)
                                .setLayoutResource(R.layout.view_guide_simple))
                        .build()
                        .show();
                Log.d("MainActivity", "----------------------");
                for (int i=0;i<anchor.getChildCount();i++){
                    Log.d("MainActivity", anchor.getChildAt(i).toString());
                }
            }
        });
    }
}
