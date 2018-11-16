package com.ziggeo.androidsdk.demo.recorders;

import android.os.Bundle;
import android.util.Log;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.demo.BaseActivity;
import com.ziggeo.androidsdk.net.exceptions.ResponseException;
import com.ziggeo.demo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.internal.Util;

public class EmbeddedRecorderActivity extends BaseActivity implements Callback {

    public static final String TAG = EmbeddedRecorderActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Ziggeo ziggeo = new Ziggeo(APP_TOKEN, this);
        long maxDuration = 20000L;
        ziggeo.setSendImmediately(false);
        ziggeo.setMaxRecordingDuration(maxDuration);
        ziggeo.setNetworkRequestsCallback(this);
        ziggeo.attachRecorder(getSupportFragmentManager(), R.id.fl_content);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_embedded_recorder;
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
            ResponseException exception = new ResponseException(
                    response.code(), response.message()
            );
            onFailure(call, exception);
        }
    }

}
