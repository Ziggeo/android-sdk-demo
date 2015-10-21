package com.ziggeo.ziggeodemowithoutsources;

import android.app.Activity;
import android.os.Bundle;

import com.ziggeo.androidsdk.Ziggeo;

public class MainActivity extends Activity {

    public static final String APP_TOKEN = "APP_TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
        ziggeo.createVideo(this, 1000 * 60 / 3);
    }

}
