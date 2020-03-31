package com.ziggeo.androidsdk.demo.recorders;

import android.support.annotation.NonNull;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.callbacks.IRecorderCallback;
import com.ziggeo.androidsdk.callbacks.RecorderCallback;
import com.ziggeo.androidsdk.demo.BaseActivity;
import com.ziggeo.androidsdk.recorder.MicSoundLevel;
import com.ziggeo.androidsdk.recorder.RecorderConfig;
import com.ziggeo.demo.R;

import java.util.Arrays;
import java.util.List;

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
        RecorderConfig config = new RecorderConfig.Builder(getApplicationContext())
                .callback(prepareCallback())
                .sendImmediately(false)
                .enableCoverShot(false)
                .maxDuration(20000)
                .build();
        ziggeo.setRecorderConfig(config);
        ziggeo.startAudioRecorder();
    }
    private IRecorderCallback prepareCallback() {
        return new RecorderCallback() {
            @Override
            public void loaded() {
                super.loaded();
                Timber.d("Recorder. Loaded");
            }

            @Override
            public void manuallySubmitted() {
                super.manuallySubmitted();
                Timber.d("Recorder. ManuallySubmitted");
            }

            @Override
            public void recordingStarted() {
                super.recordingStarted();
                Timber.d("Recorder. RecordingStarted");
            }

            @Override
            public void recordingStopped(@NonNull String path) {
                super.recordingStopped(path);
                Timber.d("Recorder. RecordingStopped. Path:%s", path);
            }

            @Override
            public void countdown(int timeLeft) {
                super.countdown(timeLeft);
                Timber.d("Recorder. Countdown:%s", timeLeft);
            }

            @Override
            public void recordingProgress(long time) {
                super.recordingProgress(time);
                Timber.d("Recorder. RecordingProgress:%s", time);
            }

            @Override
            public void readyToRecord() {
                super.readyToRecord();
                Timber.d("Recorder. ReadyToRecord");
            }

            @Override
            public void accessForbidden(@NonNull List<String> permissions) {
                super.accessForbidden(permissions);
                Timber.d("Recorder. AccessForbidden:%s", Arrays.toString(permissions.toArray()));
            }

            @Override
            public void accessGranted() {
                super.accessGranted();
                Timber.d("Recorder. AccessGranted");
            }

            @Override
            public void noMicrophone() {
                super.noMicrophone();
                Timber.d("Recorder. NoMicrophone");
            }

            @Override
            public void hasMicrophone() {
                super.hasMicrophone();
                Timber.d("Recorder. HasMicrophone");
            }

            @Override
            public void microphoneHealth(@NonNull MicSoundLevel level) {
                super.microphoneHealth(level);
                Timber.d("Recorder. microphoneHealth:%s", level);
            }

            @Override
            public void error(@NonNull Throwable throwable) {
                super.error(throwable);
                Timber.d(throwable, "Recorder. Error");
            }

        };
    }

}
