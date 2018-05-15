Change Log
==========
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
