Change Log
==========

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
