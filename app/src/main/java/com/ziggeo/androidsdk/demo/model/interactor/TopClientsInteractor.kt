package com.ziggeo.androidsdk.demo.model.interactor

import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.ClientModel
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
class TopClientsInteractor @Inject constructor(
    private var systemMessageNotifier: SystemMessageNotifier
) {

    fun getClientsList(): Single<List<ClientModel>> {
        val list = ArrayList<ClientModel>()
        list.add(
            ClientModel(
                R.drawable.logo_sap,
                "SAP",
                "https://sap.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_gofundme,
                "GoFundMe",
                "https://www.gofundme.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_swisspost,
                "SwissPost",
                "https://www.post.ch/en"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_virgin,
                "Virgin",
                "https://www.virginatlantic.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_itslearning,
                "ItsLearning",
                "https://itslearning.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_callidus,
                "Callidus Cloud",
                "https://www.calliduscloud.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_hireiq,
                "Hire IQ",
                "http://www.hireiqinc.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_fiverr,
                "Fiverr",
                "https://www.fiverr.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_circleup,
                "CircleUp",
                "https://circleup.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_youcruit,
                "Youcruit",
                "https://us.youcruit.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_netflix,
                "Netflix",
                "https://www.netflix.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_spotify,
                "Spotify",
                "https://spotify.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_nyustern,
                "NYU Stern",
                "http://www.stern.nyu.edu"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_dubizzle,
                "Dubizzle",
                "https://dubizzle.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_usv,
                "Union Square Ventures",
                "https://usv.com"
            )
        )
        list.add(
            ClientModel(
                R.drawable.logo_mavenclinic,
                "Maven Clinic",
                "https://www.mavenclinic.com"
            )
        )
        return Single.fromCallable<List<ClientModel>> {
            list
        }.doOnError(this::onError)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun onError(t: Throwable) {
        systemMessageNotifier.send(SystemMessage("", SystemMessageType.COMMON_ERROR))
        Timber.e(t)
    }

}