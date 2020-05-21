package com.ttd.androidlearning.custom_view;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ttd.androidlearning.R;

/**
 * @author wt
 * @time 2020/5/21
 */
public class TouchTransferActivity extends AppCompatActivity implements View.OnClickListener , CompoundButton.OnCheckedChangeListener {
    private TouchTransferVG1 vg1;
    private TouchTransferView1 v1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_touch_transfer);

        vg1 = findViewById(R.id.vg_1);
        v1 = findViewById(R.id.v_1);

        ((CheckBox)findViewById(R.id.cb_intercept)).setOnCheckedChangeListener(this);
        ((CheckBox)findViewById(R.id.cb_consume_1)).setOnCheckedChangeListener(this);
        ((CheckBox)findViewById(R.id.cb_consume_2)).setOnCheckedChangeListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(this,"事件传递到了Activity",Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.cb_intercept:
                vg1.setIntercept(isChecked);
                break;
            case R.id.cb_consume_1:
                v1.setConsume(isChecked);
                break;
            case R.id.cb_consume_2:
                vg1.setConsume(isChecked);
                break;
        }
    }
}
