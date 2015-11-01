package com.ziggeo.ziggeodemowithoutsources;

import android.app.Activity;
import android.os.Bundle;

import com.ziggeo.androidsdk.Ziggeo;

public class DemoActivityEmbeddedRecorder extends Activity {

    public static final String TAG = DemoActivityEmbeddedRecorder.class.getSimpleName();

    public static final String APP_TOKEN = "0edc011c3056fe19e7c850785358066a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embedded_recorder);

        Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
        long maxDuration = 20000L;
        ziggeo.attachRecorder(getFragmentManager(), R.id.fl_content, maxDuration);
    }

}
