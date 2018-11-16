package com.ziggeo.androidsdk.demo.recorders;

import android.support.annotation.NonNull;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.demo.BaseActivity;
import com.ziggeo.androidsdk.recording.RecordingProcessCallback;
import com.ziggeo.demo.R;

import butterknife.OnClick;
import timber.log.Timber;

public class AudioRecorderActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_audio_recorder;
    }

    @OnClick(R.id.btn_run_audio_recorder)
    void onRunAudioRecorderClicked() {
        Ziggeo ziggeo = new Ziggeo(APP_TOKEN, this);
        ziggeo.setRecordingProcessCallback(new RecordingProcessCallback() {
            @Override
            public void onStarted() {
                Timber.e("Recording started");
            }

            @Override
            public void onStopped(@NonNull String path) {
                Timber.d("Recording stopped:%s", path);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                Timber.e("Recording error:%s", throwable.toString());
            }
        });
        ziggeo.startRecorder(true);
    }

}
