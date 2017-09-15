Change Log
==========

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
