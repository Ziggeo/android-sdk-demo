package com.ziggeo.androidsdk.demo.model.interactor

import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.net.models.ContentModel
import com.ziggeo.androidsdk.net.models.videos.VideoModel
import com.ziggeo.androidsdk.net.services.audios.IAudiosServiceRX
import com.ziggeo.androidsdk.net.services.images.IImageServiceRx
import com.ziggeo.androidsdk.net.services.videos.IVideosServiceRx
import io.reactivex.Completable
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
    private val videoService: IVideosServiceRx,
    private val audiosService: IAudiosServiceRX,
    private val imagesService: IImageServiceRx,
    private val ziggeo: IZiggeo
) {

    fun getRecordingsList(): Single<List<ContentModel>> {
        return Single.zip(
            videoService.index(null),
            audiosService.index(null),
            imagesService.index(null),
            { videos, audios, images ->
                videos.addAll(audios)
                videos.addAll(images)
                videos.sortByDescending { it.date }
                videos
            }
        )
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

    fun getImageUrl(token: String): Single<String> {
        return videoService.getImageUrl(token)
    }
}