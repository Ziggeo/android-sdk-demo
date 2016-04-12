package com.ziggeo.ziggeodemowithoutsources;

import android.app.Activity;
import android.os.Bundle;

import com.ziggeo.androidsdk.Ziggeo;

public class DemoActivityFullscreenRecorder extends Activity {

    public static final String TAG = DemoActivityFullscreenRecorder.class.getSimpleName();

    public static final String APP_TOKEN = ""; // TODO place your token here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
        long maxDuration = 20000L;
        ziggeo.createVideo(this, maxDuration);
    }

}
