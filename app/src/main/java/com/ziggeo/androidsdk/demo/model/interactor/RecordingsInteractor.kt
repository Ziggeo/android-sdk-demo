package com.ziggeo.androidsdk.demo.model.interactor

import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.net.ZUrlHelper
import com.ziggeo.androidsdk.net.models.videos.VideoModel
import com.ziggeo.androidsdk.net.services.videos.IVideosServiceRx
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import java.io.InputStream
import javax.inject.Inject


/**
 * Created by Alexander Bedulin on 03-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class RecordingsInteractor @Inject constructor(
    private val videoService: IVideosServiceRx,
    private val ziggeo: IZiggeo
) {

    fun getRecordingsList(): Single<List<VideoModel>> {
        return videoService.index(null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun destroy(videoToken: String): Completable {
        return videoService.destroy(videoToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getInfo(videoToken: String): Single<VideoModel> {
        return videoService[videoToken]
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateInfo(token: String, args: HashMap<String, String>): Single<VideoModel> {
        return videoService.update(token, args)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateInfo(videoModel: VideoModel): Single<VideoModel> {
        return videoService.update(videoModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun downloadImage(token: String): Single<InputStream> {
        return videoService.downloadImage(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getImageUrl(token: String): Single<String> {
        return Single.fromCallable {
            val builder = HttpUrl.get(videoService.getImageUrl(token)).newBuilder()
            ZUrlHelper.appendAuthTokens(
                builder, ziggeo
            )
            builder.build().toString()
        }
    }
}