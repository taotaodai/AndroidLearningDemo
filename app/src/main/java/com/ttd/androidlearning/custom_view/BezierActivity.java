package com.ttd.androidlearning.custom_view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ttd.androidlearning.R;

/**
 * @author wt
 * @time 2020/5/22
 */
public class BezierActivity extends AppCompatActivity implements View.OnClickListener {
    private CustomViewBezier vBezier;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bezier);

        vBezier = findViewById(R.id.v_bezier);
        findViewById(R.id.btn_2l).setOnClickListener(this);
        findViewById(R.id.btn_3l).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.btn_2l:
//                vBezier.setThreeLevel(false);
//                break;
//            case R.id.btn_3l:
//                vBezier.setThreeLevel(true);
//                break;
        }
    }
}
