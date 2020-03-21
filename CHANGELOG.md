## Changelog

Version 0.82.9*(2020-03-12)*
----------------------------
* Camera1 API now forced for all devices with LEGACY level of Camera2 support.
* Fixed `cancelledByUser` callback
* Fixed Player and Recorder style config to prevent crashing.
* Improvements for handling back button press

Version 0.82.6*(2020-03-12)*
----------------------------
* Fixed crash related to memory leak after recording stop

Version 0.82.5*(2020-03-11)*
----------------------------
* Added stop confirmation check when back button pressed

Version 0.82.4*(2020-03-10)*
----------------------------
* Fixed recording orientation for camera facing front
* Added a few checks to prevents crashing when set the wake lock 

Version 0.82.3*(2020-03-09)*
----------------------------
* Added wakelock to prevent screen turn off during recording

Version 0.82.1*(2019-11-26)*
----------------------------
* Fixed memory leak in UploadingService

Version 0.82.0*(2019-11-22)*
----------------------------
* Deleted `cancelRequest` method
* Added QR scanner feature
* OkHttp version set to `3.12.6` - this is max version that supports API 16
* Improvements for uploading queue and related notifications
* Bugfixing

Version 0.81.1*(2019-11-03)*
----------------------------
* Player config now requires context
* Fixed crash when back press during recording.
* Video/Stream model improvements
* Added more methods to videos service
* TargetSdk, compileSdk, buildTools, gradle and libs versions updated
* Streaming implemented for screen recorder
* Fixed screen recorder resolution and landscape orientation
* Fixed time for retrieving images for cover shots
* Added rampToZoomFactor for CameraView
* Added preloading configuration 

Version 0.80.3*(2019-9-9)*
----------------------------
* Removed Timber and VideoCache dependencies
* Some internal changes for Xamarin
* Old deprecated things removed

Version 0.80.2*(2019-8-1)*
----------------------------
* Added more configs to `RecorderConfig`: `resolution`, `videoBitrate`,
`audioBitrate`, `audioSampleRate`

Version 0.80.1*(2019-7-24)*
----------------------------
* Added video streaming
* Zoom enabled by default
* Added sound amplitude chart for audio recorder
* Added seek bar for audio recorder and player

Version 0.79.7*(2019-05-19)*
----------------------------
* Added support for FullHD aspect ratio
* Fixed crash during the very first initialisation.
* Method renamed to be consistent with common naming styles. This will allow kotlin/dart replace getter/setter with direct field access syntax.
  * `configureUploading` -> `setUploadingConfig`
  * `configureRecorder` -> `setRecorderConfig`
  * `configurePlayer` -> `setPlayerConfig`
* Added constructors and getters/setters for styling classes
* Fixed crash in CrashLogger in case of lack of the crash info
* Implemented correct `equals` and `hashCode` for `SessionModel` and `UploadingConfig`
* Fixed several callback events for uploading
* Fixed `cancelledByUser` callback for file selector
* Switched from `implementation` to `api` for internal OkHttp dependency

Version 0.79.1*(2019-03-27)*
----------------------------
* Methods `configureUploading`, `configureRecorder`, `configurePlayer` are deprecated.
Use `setUploadingConfig`, `setRecorderConfig`, `setPlayerConfig` instead.
* Fixed crash during first initialisation
* Fixed extra args for file selector
* Fixed fullHD resolution support.

Version 0.79.0*(2019-03-18)*
----------------------------
* Added zoom support for the camera.
* Added `mute` config for player
  
Version 0.78.4*(2019-03-05)*
----------------------------
* Fixed access to the recorder configuration.
  
Version 0.78.3*(2019-03-04)*
----------------------------
* Fixed issue in player cause by wrong type in JSON
  
Version 0.78.2*(2019-03-04)*
----------------------------
* Fixed stop confirmation for screen recorder
* Fixes for Xamarin integration
  
Version 0.78.1*(2019-02-14)*
----------------------------
* Added `setSensorCallback()` method for all possible info from sensors 
* Added docs for several methods
* Fixed player issue with seekbar: it was not possible to change the progress of the video
* Fixes for Xamarin integration:  
  * removed duplicated `error` callback from two different interfaces, instead, it moved to a separate `IErrorCallback` 
  * own styles implemented instead of using Exoplayer’s styles 
  * fixed assigning of ids for views 
  * added attrs missing in Exoplayer’s binding
  
Version 0.78.0*(2019-02-06)*
----------------------------
 - Changes in autostart logic. Method `setAutostartRecordingAfter` is deprecated, instead there are now two methods
   a. `setAutostartRecording` - if `true`, when the recorder is initialized, the count down timer will be started. After the time will run out the recording will be started.
   Default if `false`.
   b. `setStartDelay` - set the value for count down timer before start the recording. Default is `3` seconds.

 - Foreground service for recordings uploading. During the uploading notifications will be visible in notification bar. In case of error there are two options available `Retry` and `Try later`
 In case of try later the SDK will try to make uploading after the delay. Default delay is 2 hours.
 Also it is possible to do uploading only when WiFi is available.
 See sample below:
 ```
         ziggeo.configureUploading(new UploadingConfig.Builder()
                .syncInterval(15 * 60 * 1000)
                .useWifiOnly(true)
                .build());
 ```
 - Face outline image.
 FaceOutlineConfig config = new FaceOutlineConfig();
  config.setShow(true);
// config.setOutlineImageResource(R.drawable.youImage); // optional
// put created object in `RecorderConfig`
```
 - New file selector with multiple selection ability.
 - `ziggeo.clearRecorder` - the method that allows the devs to clear the cache through code. Would remove the videos even if they are not uploaded.
 - Playlist for the player. `ziggo.startPlayer(token1, token2, ...)`
 - Player theming. 
   There is now ability to style the player. You can use one of the predefined themes and change colors for them. Styling available through styles.xml and through code. If both way used, only params in code will be handled. 
   Sample can be found in `ZiggeoPlayerActivity`.
```
 - New callback system for player, recorder and camera
 - Subtitles for video player
 `playerConfig.showSubtitles(boolean value)`

Fixes/Small changes
 - New method `getNumberOfCameras()` in `CameraView` class.
 - fixed camera issue on Samsung Galaxy J7
 - date pattern in file names changed from `dd-MM-yyyy_hh-mm-ss` to `dd.MM.yyyy_HH.mm.ss`

Broken changes
 - java8
 - method `startRecorder(boolean audioOnly)` is deprecated. Please, use `startAudioRecorder()`.
 - method `startRecorder()` is deprecated. Please, use `startCameraRecorder()`.
 - method `attachRecorder(FragmentManager fragmentManager, int contentId)` is deprecated. Please, use `attachCameraRecorder(FragmentManager fragmentManager, int contentId)`.
 - method `attachRecorder(FragmentManager fragmentManager, int contentId, boolean audioOnly)` is deprecated. Please, use `attachAudioRecorder(FragmentManager fragmentManager, int contentId)`.
 - methods `setExtraArgsForRecorder`, `getExtraArgsForRecorder`, `configureStopRecordingConfirmationDialog`, `getStopRecordingConfirmationDialogConfig`, `setAutostartRecordingAfter`, `setAutostartRecordingAfter`, `getAutostartRecordingAfter`, `setVideoRecordingProcessCallback`, `setRecordingProcessCallback`, `getVideoRecordingProcessCallback`, `getRecordingProcessCallback`,
 `setTurnOffCameraWhileUploading`, `setColorForStoppedCameraOverlay`, `setDrawableForStoppedCameraOverlay`, `setCoverSelectorEnabled`, `setCoverSelectorEnabled`, `setMaxRecordingDuration`, `setPreferredCameraFacing`, `setPreferredQuality`, `getPreferredQuality`, `setCameraSwitchDisabled`, `setSendImmediately`, `isSendImmediately` are deprecated. Please, use methods in `CameraRecorderConfig.Builder` class.
 - methods `configureLocalPlayback`, `getLocalPlaybackConfig`, `setExtraArgsForPlayer` are deprecated. Please, use corresponding
 - method `addCallback` in `CameraView` class is deprecated. Please, use `setCameraCallback` and `

Version 0.77.1*(2018-12-10)*
----------------------------
* Added methods for setting theme params for non-native SDKs.

Version 0.77.0*(2018-10-16)*
--------------------------
* Deep linking support
* Playlist support
* Added theming params for hiding recorder and player controls
* Audio recorder implemented
* `IVideoRecordingCallback` is deprecated, use `IRecordingProcessCallback` instead.

Version 0.76.1*(2018-10-29)*
--------------------------
* Fixed token value returned by `create` request for Xamarin.

Version 0.76.0*(2018-10-10)*
--------------------------
* Method `onProgressUpdate(long sentBytes, long totalBytes)` in ProgressCallback class is deprecated.
  Use `onProgressUpdate(@NonNull File file, long sentBytes, long totalBytes)` instead.
* Added delegates for tracking Uploading progress in Xamarin
* Gradle version updated
* Fixed crash logging
* Added deep linking for player
* Player switched to CDN server.
* Fixed issue when screen recording is not started if screen share permission has not been given previously.
* Methods `startScreenRecordService`, `stopScreenRecordService`, `stopScreenRecord` are deprecated.
* Added check on null bundle to prevent crash.

Version 0.75.0*(2018-8-19)*
--------------------------
* New: Methods marked as deprecated
 `startScreenCaptureService`, `stopScreenCaptureService`, `startScreenCapture`, `stopScreenCapture`
 Use instead
 `startScreenRecordService`, `stopScreenRecordService`, `startScreenRecord`, `stopScreenRecord`

Version 0.75.0*(2018-8-19)*
--------------------------
* New: Added send-and-close feature for screen recorder.
* New: Showing sound bar on player screen when sound icon was long clicked.
* Fix: Forced screen recorder service notification text and background color to black
* Fix: Fixed crash when extra args for recorder are null
* Fix: Added nullability annotations for player
* Fix: For screen recorder if cover shot selector is visible, it will be closed when new recording is started
* Fix: UI lag when stopping the recording
* Fix: Removed library for getting the device name

Version 0.74.3*(2018-8-10)*
--------------------------
* Added crash tracking system.
* Bugfixing
* Gradle version updated

Version 0.74.2*(2018-8-1)*
--------------------------
 * Removed old deprecated methods
 ```
 setExtraArgsForCreateVideo
 setExtraArgsForEmbeddedRecorder
 setExtraArgsForEmbeddedPlayer
 setExtraArgsForPlayVideo
 setRecorderCacheFolder
 getRecorderCacheFolder
 setMaxRecorderCacheSize
 getMaxRecorderCacheSize
 setMaxRecorderCacheFilesCount
 getMaxRecorderCacheFilesCount
 setPlayerCacheFolder
 getPlayerCacheFolder
 setMaxPlayerCacheSize
 getMaxPlayerCacheSize
 setMaxPlayerCacheFilesCount
 getMaxPlayerCacheFilesCount
 initStopRecordingConfirmationDialog
 getCacheFolder
 getMaxCachedVideosCount
 getMaxCacheSize
 setMaxCachedFilesCount
 ```
 * Added mute sound functionality for player

Version 0.74.0*(2018-7-28)*
--------------------------
 * API 21+: Added screen recorder feature.
 * Fixed vector icon causing crash on android below API 21.
 * `com.ziggeo.androidsdk.net.rest.ProgressCallback` moved to `com.ziggeo.androidsdk.net.callbacks.ProgressCallback`
 * Class `ResponseException` renamed to `ResponseException`
 * In class `ResponseException` methods `getResponse` and `setResponse` are deprecated. Use `getMessage` and `setMessage` instead.
 * Classes `IStreamsService`, `StreamsService`,  `IVideosService`,  `VideosService` moved from `com.ziggeo.androidsdk.net.rest.services.` to `com.ziggeo.androidsdk.net.services.`

Version 0.73.12*(2018-7-18)*
--------------------------
 * Fixed error during recorder initialisation on Android 8.0 for Camera2 api.

Version 0.73.11*(2018-6-21)*
--------------------------
 * Changed checkmark behavior - currently it closes the recorder.
   Also the recorder will be closed if video is sent immediately after the recording is finished.


Version 0.73.10*(2018-6-13)*
--------------------------
 * Changed return type for `ziggeo.getVideoRecordingProcessCallback()` from class to its interface.
 * Fixed issue 20. Lost bundle args were causing a crash.
 * Gradle version updated.
 * Small xml layout improvements.
 * Removed deprecated method from IVideoRecordingCallback.
 * Added applyEffect method to Videos API.

Version 0.73.8*(2018-5-12)*
--------------------------
 * Added callback to indicate video uploading started.

Version 0.73.7*(2018-5-11)*
--------------------------
 * Fixed recorder icons visibility

Version 0.73.6*(2018-5-08)*
--------------------------
 * Removed rests of Hungarian notation.
 * Replaced old icons with vectors
 * General code/layouts improvements

Version 0.73.5*(2018-5-07)*
--------------------------
 * Additional permission check for player
 * Small code improvements and bugfixes

Version 0.73.4*(2018-4-25)*
--------------------------
 * Removed some unused classes.
 * Small code improvements.
 * Improved gradle config for naming of output build files.

Version 0.73.3*(2018-4-23)*
---------------------------
 * Added a few getters to prevent issues when binding the SDK with Xamarin.

Version 0.73.2*(2018-4-11)*
--------------------------
 * Added ability to set client and server auth tokens for all requests.

Version 0.73.1*(2018-3-28)*
--------------------------
 * Fix: Added 1 hour expiration time for a session token
 * Config improvements:
    * Gradle wrapper updated to 4.4
    * Gradle plugin version updated to 3.1.0
    * Build tools version updated to 27.0.3
    * Project dependencies provided with the latest approach

Version 0.73.0*(2018-3-20)*
---------------------------
 * Docs updated.
 * Fixed issue when dialog for stop recording confirmation overlaps with covershot selection dialog.
 * Fixed issue when dialog for stop recording confirmation does not hide when recording actually stops after time limit exceeded.
 * Changes for player's cache
  * To configure cache `ziggeo.configureLocalPlayback(@Nullable LocalPlaybackConfig config)` should be used.
    Other methods is deprecated.
  * Default maximum cache size changed to 512Mb
  * Default maximum files count in cache changed to 5
  * Default cache path cahnged to `/Android/data/[app_package_name]/cache/video-cache/` if card is mounted and app has appropriate permission
    or `video-cache` subdirectory in default application's cache directory otherwise.
 * Added `ziggeo.setDrawableForStoppedCameraOverlay(@DrawableRes int drawableResource)`
 * Deprecated `onError()` method in IVideoRecordingCallback and added `onError(throwable)`.
 * Deprecated method for configuration of stop rectoring confirmation dialog.
   Instead of `ziggeo.initStopRecordingConfirmationDialog(boolean show, @StringRes int titleResId, @StringRes int mesResId,
                                                                   @StringRes int posBtnResId, @StringRes int negBtnResId)`

    please use `ziggeo.configureStopRecordingConfirmationDialog(@Nullable StopRecordingConfirmationDialogConfig config)`
 * Deprecated methods related to recorder's cache
  * `ziggeo.setRecorderCacheFolder(@NonNull String cacheFolderPath)`
  * `ziggeo.getRecorderCacheFolder()`
  * `ziggeo.setMaxRecorderCacheSize(long maxCacheSize)`
  * `ziggeo.getMaxRecorderCacheSize()`
  * `ziggeo.setMaxRecorderCacheFilesCount(int filesCount)`
  * `ziggeo.getMaxRecorderCacheFilesCount()`
 * Deleted old deprecated method for overriding layout for recorder
   `ziggeo.setRecorderLayout(@LayoutRes int layoutId)`
 * Deleted old deprecated classes and methods
   * `IOUtils.checkVideoIsInCache(String token)`
   * `HttpStatusCodes`
   * `CameraHelper`
   * `ziggeo.cancel()`
   * `ziggeo.setCacheFolder(@NonNull String cacheFolderPath)`
   * `ziggeo.setMaxCacheSize(long maxCacheSize)`
   * `ziggeo.setShowCoverShotSelectionPopup(boolean showCoverShotSelectionPopup)`
   * `ziggeo.startPlayer(@NonNull Context context, @NonNull Uri path)`
   * `ziggeo.startPlayer(@NonNull Context context, @NonNull String videoToken)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager fragmentManager, int contentId, long maxDurationInMillis,                Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, boolean disableCameraSwitching, Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, int preferredCameraId, Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, CameraHelper.Quality videoQuality, Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, boolean disableCameraSwitching, CameraHelper.Quality videoQuality, Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, int preferredCameraId, CameraHelper.Quality videoQuality, Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, boolean disableCameraSwitching, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, @CameraView.Facing int preferredCamera, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, @Nullable CameraHelper.Quality videoQuality, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, boolean disableCameraSwitching, @Nullable CameraHelper.Quality videoQuality, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, @CameraView.Facing int preferredCamera, @Nullable CameraHelper.Quality videoQuality, @Nullable Callback callback)`

Version 0.72.7*(2018-3-6)*
--------------------------
 * Lambdas removed from the SDK.

Version 0.72.6*(2018-3-5)*
--------------------------
 * Method `setExtraArgsForCreateVideo` was deprecated, please use `setExtraArgsForEmbeddedRecorder`.
 * Method `setExtraArgsForPlayVideo` was deprecated, please use `setExtraArgsForEmbeddedPlayer`.

Version 0.72.4*(2018-3-1)*
--------------------------
 * Fix: Session token now automatically appended to every request.

Version 0.72.3*(2018-2-26)*
---------------------------
 * New: Removed deprecated callbacks for video compressor.
 * Fix: Fixed authentication through server/client tokens.

Version 0.72.2*(2018-2-16)*
---------------------------
 * New: Removed deprecated video encoder.

Version 0.72.1*(2018-2-8)*
--------------------------
 * New: Changed launch mode for recorder activity to `singleInstance`.

Version 0.72.0*(2018-1-29)*
----------------------------
 * New: Added settings for player cache.
 * Fix: Cache for player
 * Fix: Checking for filled cache.
 * Fix: Blank preview for case when the app returns from background.

Version 0.71.7*(2017-12-25)*
----------------------------
 * Fix: Fixed issue with loosing config for camera in case of autoswitching from Camera2 to Camera1.
 * Fix: Added seconds to video file name to prevent losing videos recorded during one minute.

Version 0.71.6*(2017-11-17)*
----------------------------
 * Fix: Crash when starting activity from application context.

Version 0.71.5*(2017-11-12)*
----------------------------
 * Fix: Removed compressor for medium video size. Lower bitrate set for medium and low quality. 
 * Fix: Showing preview after start pressed if the preview was hidden. 
 * Versions of dependencies updated.

Version 0.71.1*(2017-11-7)*
----------------------------
 * New: Analytics integration.
 * Fix: Orientation calculation for recorder in Camera1 
 * Fix: Params transferring between Ziggeo object and internal activities/fragments
 * Fix: Default state for `switchCameraDisabled` set to `false`
 * Other fixes and improvements

Version 0.70.1*(2017-10-10)*
----------------------------
 * New: Added ability to start player as separate screen.

Version 0.70.0*(2017-09-19)*
----------------------------
 * Fix: Fixed player layout.
 * Fix: Fixed logic for immediate video sending.
 * New: Methods for getting Videos and Stream services exposed to interface.

Version 0.69.2*(2017-09-19)*
----------------------------
 * New: Video compressor exposed to interface.

Version 0.69.1*(2017-09-15)*
----------------------------
 * Fix: Forced AAC audio codec and H264 video codec for Camera1

Version 0.69.0*(2017-09-14)*
----------------------------
 * Fix: Improved video size scaling for medium quality
 * New: Added video compressor in case of using medium video quality
 * New: Added two methods to VideoRecordingCallback to indicate compression progress

Version 0.68.1*(2017-08-17)*
----------------------------
 * Fix: Missing resource id crash
 * Fix: Removed wrong `Deprecated` mark

Version 0.68.0*(2017-08-16)*
----------------------------
 * Broken compatibility: `CameraView` should be using for layout customisation instead of `SurfaceView`
 * New: Customisation through layout is `Deprecated`. `CameraView` should be used instead.
 * Fix: Reduced video file size
 * New: Added quality settings for `CameraView`
 * Fix: Bugfixing
 
Version 0.67.6*(2017-08-10)*
----------------------------
 * Fix: Resolved issue related to uploading speed.

Version 0.67.5 *(2017-08-2)*
----------------------------
 * New: `createVideo` methods now deprecated. All params should be set through setters.
 Use `startRecorder` instead.
 * New: Change `onProgressUpdate` callback. Now it accepts `sent` and `total` bytes as args.
 * New: If max duration is zero video will be endless.
 * New: Added extra time format for case of zero maximum duration.
 * Fix: Small issues
 * Fix: UI improvements
 
Version 0.67.0 *(2017-07-9)*
----------------------------
 * Fix: Refactoring of SDK configuration
 * Fix: Issue related to missing cover selector dialog
 
Version 0.66.0 *(2017-04-28)*
----------------------------
 * New: SDK refactored with support library
 * New: Added sample for playing uploaded video
 
Version 0.65.1 *(2017-04-13)*
----------------------------
 * Fix: Proper selection of resolution for video recording 

Version 0.65.0 *(2017-04-05)*
----------------------------
 * New: Added support for EU host.
 * Fix: Minor UI fixes

Version 0.64.0 *(2017-04-03)*
----------------------------
 * New: Added special `CameraView` class to provide ability to create fully customizable recorder.

Version 0.63.2 *(2017-03-07)*
----------------------------
 * New: Ability to get sdk via simple gradle dependency
 * New: Autostart recording after delay using `ziggeo.setAutostartRecordingAfter(long millis)`
 * New: `delete()` method for `StreamService` and `VideoService` was deprecated. Please, use `destroy()` instead.
 * New: Added dialog to confirm stop recording. Use through `ziggeo.initStopRecordingConfirmationDialog()`
 * New: Ability to format time on recorder screen `ziggeo.setTimeFormatAction()`
