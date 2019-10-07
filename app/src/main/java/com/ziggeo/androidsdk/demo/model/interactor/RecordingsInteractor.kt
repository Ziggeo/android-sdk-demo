package com.ziggeo.androidsdk.demo.model.interactor

import com.ziggeo.androidsdk.net.models.videos.VideoModel
import com.ziggeo.androidsdk.net.services.videos.IVideosServiceRx
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 03-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class RecordingsInteractor @Inject constructor(
    private val videoService: IVideosServiceRx
) {

    fun getRecordingsList(): Single<List<VideoModel>> {
        return videoService.index(null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}