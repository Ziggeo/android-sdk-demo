Change Log
==========

Version 0.63.2 *(2017-03-07)*
----------------------------

 * New: Ability to get sdk via simple gradle depencency
 * New: Autostart recording after delay using `ziggeo.setAutostartRecordingAfter(long millis)`
 * New: `delete()` method for `StreamService` and `VideoService` was deprecated. Please, use `destroy()` instead.
 * New: Added dialog to confirm stop recording. Use through `ziggeo.initStopRecordingConfirmationDialog()`
 * New: Ability to format time on recorder screen `ziggeo.setTimeFormatAction()`
