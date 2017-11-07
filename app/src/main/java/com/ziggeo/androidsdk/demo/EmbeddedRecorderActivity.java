package com.ziggeo.androidsdk.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.net.rest.exceptions.RestResponseException;
import com.ziggeo.demo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.internal.Util;

public class EmbeddedRecorderActivity extends AppCompatActivity implements Callback {

    public static final String TAG = EmbeddedRecorderActivity.class.getSimpleName();

    public static final String APP_TOKEN = ""; // TODO place your token here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embedded_recorder);

        Ziggeo ziggeo = new Ziggeo(APP_TOKEN, this);
        long maxDuration = 20000L;
        ziggeo.setSendImmediately(false);
        ziggeo.setMaxRecordingDuration(maxDuration);
        ziggeo.setNetworkRequestsCallback(this);
        ziggeo.attachRecorder(getSupportFragmentManager(), R.id.fl_content);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.e(TAG, "Request failure. Exception:" + e.toString());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful() && response.body() != null) {
            String responseString = response.body().string();
            Util.closeQuietly(response);
            Log.d(TAG, "Request success:" + responseString);

//            do something here

        } else {
            RestResponseException exception = new RestResponseException(
                    response.code(), response.message()
            );
            onFailure(call, exception);
        }
    }

}
