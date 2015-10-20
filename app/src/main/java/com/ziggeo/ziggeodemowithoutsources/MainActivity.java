package com.ziggeo.ziggeodemowithoutsources;

import android.app.Activity;
import android.os.Bundle;

import com.ziggeo.androidsdk.Ziggeo;

/**
 * Created by Alexandr on 19.10.2015.
 */
public class MainActivity extends Activity {

    public static final String APP_TOKEN = "0edc011c3056fe19e7c850785358066a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
        ziggeo.createVideo(this, 1000 * 60 / 3);
    }

}
