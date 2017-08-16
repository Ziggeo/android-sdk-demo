package com.ziggeo.androidsdk.demo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.net.rest.ProgressCallback;
import com.ziggeo.androidsdk.widgets.cameraview.CameraView;
import com.ziggeo.demo.R;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.internal.Util;

/**
 * Created by Alex Bedulin on 4/3/17.
 */
public class CameraViewActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String APP_TOKEN = ""; // TODO your token here

    static final String TAG = CameraViewActivity.class.getSimpleName();

    public static final String[] VIDEO_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    CameraView cvCamera;
    FloatingActionButton fabRecord;
    FloatingActionButton fabTakePicture;
    FloatingActionButton fabSwitchCamera;

    private File fileToSaveRecording;
    private Ziggeo ziggeo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);
        ziggeo = new Ziggeo(APP_TOKEN, this);
        setupCamera();
    }

    private void setupCamera() {
        fabRecord = (FloatingActionButton) findViewById(R.id.fab_record);
        fabRecord.setOnClickListener(this);
        fabTakePicture = (FloatingActionButton) findViewById(R.id.fab_take_picture);
        fabTakePicture.setOnClickListener(this);
        fabSwitchCamera = (FloatingActionButton) findViewById(R.id.fab_switch_camera);
        fabSwitchCamera.setOnClickListener(this);

        cvCamera = (CameraView) findViewById(R.id.cv_camera);
        cvCamera.addCallback(new CameraView.Callback() {
            @Override
            public void onCameraOpened(CameraView cameraView) {
                super.onCameraOpened(cameraView);
                Log.d(TAG, "onCameraOpened");
            }

            @Override
            public void onCameraClosed(CameraView cameraView) {
                super.onCameraClosed(cameraView);
                Log.d(TAG, "onCameraClosed");
            }

            @Override
            public void onPictureTaken(CameraView cameraView, byte[] data) {
                super.onPictureTaken(cameraView, data);
                Log.d(TAG, "onPictureTaken");
                showImagePreview(data);
            }

            @Override
            public void onRecodingStarted() {
                super.onRecodingStarted();
                Log.d(TAG, "onRecodingStarted");
            }

            @Override
            public void onRecodingStopped() {
                super.onRecodingStopped();
                Log.d(TAG, "onRecodingStopped");
//                uploadFile();
            }

            @Override
            public void onRecodingError(Throwable throwable) {
                super.onRecodingError(throwable);
                Log.e(TAG, "onRecodingError:" + throwable.toString());
            }
        });

        updateIcons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isCameraAccessGranted() && isRecordAudioGranted() && isWriteStorageGranted()) {
            cvCamera.start();
        } else {
            ActivityCompat.requestPermissions(this, VIDEO_PERMISSIONS, 0);
        }
    }

    @Override
    protected void onPause() {
        cvCamera.stop();
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_record:
                if (cvCamera.isRecording()) {
                    cvCamera.stopRecording();
                } else {
                    if (prepareRecordingFile()) {
                        final int maxDuration = 0; // endless
                        cvCamera.startRecording(fileToSaveRecording.getPath(), maxDuration);
                    }
                }
                break;
            case R.id.fab_take_picture:
                cvCamera.takePicture();
                break;
            case R.id.fab_switch_camera:
                if (cvCamera.getFacing() == CameraView.FACING_FRONT) {
                    cvCamera.setFacing(CameraView.FACING_BACK);
                } else {
                    cvCamera.setFacing(CameraView.FACING_FRONT);
                }
                break;
        }

        updateIcons();
    }

    private void updateIcons() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                fabRecord.setImageResource(
                        cvCamera.isRecording() ? R.drawable.ic_stop_recording : R.drawable.ic_start_recording
                );

                fabSwitchCamera.setImageResource(
                        cvCamera.getFacing() == CameraView.FACING_FRONT ? R.drawable.ic_camera_switch_to_rear : R.drawable.ic_camera_switch_to_front
                );
            }
        });
    }

    private boolean prepareRecordingFile() {
        if (fileToSaveRecording == null || !fileToSaveRecording.exists()) {
            fileToSaveRecording = new File(Environment.getExternalStorageDirectory() + "/Download", "tempRecord.mp4");
            try {
                fileToSaveRecording.getParentFile().mkdirs();
                return fileToSaveRecording.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        } else return true;
    }

    void showImagePreview(final byte[] data) {
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
        new AlertDialog.Builder(this)
                .setView(iv)
                .setPositiveButton(R.string.common_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private boolean isCameraAccessGranted() {
        return isPermissionGranted(Manifest.permission.CAMERA);
    }

    private boolean isRecordAudioGranted() {
        return isPermissionGranted(Manifest.permission.RECORD_AUDIO);
    }

    private boolean isWriteStorageGranted() {
        return isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private boolean isPermissionGranted(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    void uploadFile() {
        ziggeo.videos().create(fileToSaveRecording, null, new ProgressCallback() {
            @Override
            public void onProgressUpdate(long sent, long total) {
                Log.d(TAG, "Sent " + sent + "/" + total);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse:" + response.body().string());
                Util.closeQuietly(response);
            }
        });
    }

}
