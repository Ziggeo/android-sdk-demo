package com.ziggeo.ziggeodemowithoutsources;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.io.rest.HttpStatusCodes;
import com.ziggeo.androidsdk.io.rest.exceptions.RestResponseException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DemoActivityEmbeddedRecorder extends Activity  implements Callback {

    public static final String TAG = DemoActivityEmbeddedRecorder.class.getSimpleName();

    public static final String APP_TOKEN = ""; // TODO place your token here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embedded_recorder);

        Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
        long maxDuration = 20000L;
        ziggeo.setSendImmediately(false);
        ziggeo.attachRecorder(getFragmentManager(), R.id.fl_content, maxDuration, this);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.e(TAG, "Request failure. Exception:" + e.toString());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (HttpStatusCodes.isSuccess(response.code()) && response.body() != null) {
            String responseString = response.body().string();
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
