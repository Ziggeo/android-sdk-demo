package com.ziggeo.androidsdk.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ziggeo.androidsdk.IZiggeo;
import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.qr.QrScannerCallback;
import com.ziggeo.androidsdk.qr.QrScannerConfig;

import java.util.List;

import timber.log.Timber;

public class QrScannerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IZiggeo ziggeo = Ziggeo.getInstance(this);
        ziggeo.setQrScannerConfig(new QrScannerConfig(new QrScannerCallback() {
            @Override
            public void onQrDecoded(@NonNull String value) {
                super.onQrDecoded(value);
                Timber.d("onQrDecoded:%s", value);
            }

            @Override
            public void loaded() {
                super.loaded();
                Timber.d("loaded");
            }

            @Override
            public void error(@NonNull Throwable throwable) {
                super.error(throwable);
                Timber.e(throwable);
            }

            @Override
            public void accessForbidden(@NonNull List<String> permissions) {
                super.accessForbidden(permissions);
                Timber.d("accessForbidden");
            }

            @Override
            public void accessGranted() {
                super.accessGranted();
                Timber.d("accessGranted");
            }
        }));
        ziggeo.startQrScanner();
    }
}
