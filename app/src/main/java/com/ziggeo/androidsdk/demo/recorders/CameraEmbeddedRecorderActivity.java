package com.ziggeo.androidsdk.demo.recorders;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.demo.BaseActivity;
import com.ziggeo.demo.R;

public class CameraEmbeddedRecorderActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_embedded_recorder;
    }

    @Override
    protected void init() {
        super.init();
        Ziggeo ziggeo = new Ziggeo(APP_TOKEN, this);
        ziggeo.attachCameraRecorder(getSupportFragmentManager(), R.id.fl_content);
    }
}
