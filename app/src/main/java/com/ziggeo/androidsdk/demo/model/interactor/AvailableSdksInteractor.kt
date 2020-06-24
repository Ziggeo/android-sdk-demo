package com.ziggeo.androidsdk.demo.model.interactor

import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.data.feature.CategoryModel
import com.ziggeo.androidsdk.demo.model.data.feature.FeatureModel
import com.ziggeo.androidsdk.demo.model.data.feature.SdkModel
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessage
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageNotifier
import com.ziggeo.androidsdk.demo.model.system.message.SystemMessageType
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 03-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AvailableSdksInteractor @Inject constructor(
    private var systemMessageNotifier: SystemMessageNotifier
) {

    fun getSDKsList(): Single<List<FeatureModel>> {
        val list = ArrayList<FeatureModel>()
        list.add(
            CategoryModel("Mobile SDKs")
        )

        list.add(
            SdkModel(
                R.drawable.ic_objc,
                "https://github.com/Ziggeo/iOS-Client-SDK"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_swift,
                "https://github.com/Ziggeo/Swift-Client-SDK"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_android,
                "https://github.com/Ziggeo/Android-Client-SDK"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_xamarin,
                "https://github.com/Ziggeo/Xamarin-SDK-Demo"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_reactnative,
                "https://github.com/Ziggeo/ReactNativeDemo"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_flutter,
                "https://github.com/Ziggeo/Flutter-SDK"
            )
        )

        list.add(
            CategoryModel("Server-Side SDKs")
        )

        list.add(
            SdkModel(
                R.drawable.ic_php,
                "https://github.com/Ziggeo/ZiggeoPhpSdk"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_python,
                "https://github.com/Ziggeo/ZiggeoPythonSdk"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_nodejs,
                "https://github.com/Ziggeo/ZiggeoNodeSdk"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_ruby,
                "https://github.com/Ziggeo/ZiggeoRubySdk"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_java,
                "https://github.com/Ziggeo/ZiggeoJavaSdk"
            )
        )
        list.add(
            SdkModel(
                R.drawable.ic_csharp,
                "https://github.com/Ziggeo/ZiggeoCSharpSDK"
            )
        )

        return Single.fromCallable<List<FeatureModel>> {
            list
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}