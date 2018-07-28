package com.ziggeo.androidsdk.demo.api;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.net.exceptions.ResponseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;

/**
 * Created by Alex Bedulin on 18-Apr-18.
 */
public class VideosApiSamples {

    private static final String TAG = VideosApiSamples.class.getSimpleName();

    private Ziggeo ziggeo;

    public VideosApiSamples(@NonNull String applicationToken, @NonNull Context context) {
        ziggeo = new Ziggeo(applicationToken, context);
    }

    public void downloadImageAndSaveToFile(@NonNull String videoTokenOrKey) {
        ziggeo.videos().downloadImage(videoTokenOrKey, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Error during image download:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) {
                final ResponseBody body = response.body();
                if (response.isSuccessful() && body != null) {
                    final File imageFile = new File(Environment.getExternalStorageDirectory() + "/Download/image.png");
                    saveByteStreamToFile(body.byteStream(), imageFile);
                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }

                Util.closeQuietly(response);
            }
        });
    }

    public void downloadVideoAndSaveToFile(@NonNull String videoTokenOrKey) {
        ziggeo.videos().downloadVideo(videoTokenOrKey, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Error during image download:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.e(TAG, "onResponse");
                final ResponseBody body = response.body();
                if (response.isSuccessful() && body != null) {
                    final File videoFile = new File(Environment.getExternalStorageDirectory() + "/Download/video.mp4");
                    saveByteStreamToFile(body.byteStream(), videoFile);
                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }

                Util.closeQuietly(response);
            }
        });
    }

    private void saveByteStreamToFile(@NonNull InputStream is, @NonNull File fileSaveTo) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileSaveTo);
            byte[] buffer = new byte[1024];
            while (true) {
                int readed = is.read(buffer);

                if (readed == -1) {
                    break;
                }
                fos.write(buffer, 0, readed);
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } finally {
            Util.closeQuietly(fos);
            Util.closeQuietly(is);
        }
    }

}
