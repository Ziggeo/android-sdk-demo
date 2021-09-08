package com.ziggeo.androidsdk.demo.model.interactor

import com.ziggeo.androidsdk.IZiggeo
import com.ziggeo.androidsdk.net.models.ContentModel
import com.ziggeo.androidsdk.net.models.audios.Audio
import com.ziggeo.androidsdk.net.models.audios.AudioDetails
import com.ziggeo.androidsdk.net.models.images.Image
import com.ziggeo.androidsdk.net.models.images.ImageDetails
import com.ziggeo.androidsdk.net.models.videos.VideoModel
import com.ziggeo.androidsdk.net.services.audios.IAudiosServiceRX
import com.ziggeo.androidsdk.net.services.images.IImageServiceRx
import com.ziggeo.androidsdk.net.services.videos.IVideosServiceRx
import com.ziggeo.androidsdk.utils.FileUtils
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

    fun destroy(model: ContentModel): Completable {
        return when (model) {
            is VideoModel -> videoService.destroy(model.token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            is Audio -> audiosService.destroy(model.token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            else -> imagesService.destroy(model.token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun getInfo(mediaType: Int, token: String): Single<ContentModel> {
        return when (mediaType) {
            FileUtils.AUDIO -> {
                audiosService.get(token)
                    .map { it.audio as ContentModel }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            FileUtils.IMAGE -> {
                imagesService.get(token)
                    .map { it.image as ContentModel }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            else -> {
                videoService[token]
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    fun updateInfo(token: String, args: HashMap<String, String>): Single<VideoModel> {
        return videoService.update(token, args)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateInfo(model: ContentModel): Single<ContentModel> {
        return when (model) {
            is VideoModel -> videoService.update(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            is Audio -> {
                val audioModel = AudioDetails()
                audioModel.audio = model
                audiosService.update(audioModel)
                    .map { it.audio as ContentModel }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            is Image -> {
                val audioModel = ImageDetails()
                audioModel.image = model
                imagesService.update(audioModel)
                    .map { it.image as ContentModel }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            else -> Single.just(model)
        }
    }

    fun getPreviewUrl(token: String): Single<String> {
        return videoService.getImageUrl(token)
    }

    fun getImageUrl(token: String): Single<String> {
        return imagesService.getImageUrl(token)
    }

}