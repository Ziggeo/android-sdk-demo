package com.ziggeo.androidsdk.demo.ui.global

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.util.argument


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class MessageDialogFragment : DialogFragment() {

    private val startTag: String by argument(ARG_TAG, "")
    private val title: String? by argument(ARG_TITLE)
    private val message: String? by argument(ARG_MESSAGE)
    private val positiveText: String? by argument(ARG_POSITIVE_TEXT)
    private val negativeText: String? by argument(ARG_NEGATIVE_TEXT)

    private val clickListener
        get() = when {
            parentFragment is OnClickListener -> parentFragment as OnClickListener
            activity is OnClickListener -> activity as OnClickListener
            else -> object : OnClickListener {}
        }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(context!!).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(positiveText ?: getString(R.string.ok)) { _, _ ->
                dismissAllowingStateLoss()
                clickListener.dialogPositiveClicked(startTag)
            }
            negativeText?.let { negativeText ->
                setNegativeButton(negativeText) { _, _ ->
                    dismissAllowingStateLoss()
                    clickListener.dialogNegativeClicked(startTag)
                }
            }
        }.create()

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        clickListener.dialogCanceled(startTag)
    }

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_MESSAGE = "arg_message"
        private const val ARG_TAG = "arg_tag"
        private const val ARG_POSITIVE_TEXT = "arg_positive_text"
        private const val ARG_NEGATIVE_TEXT = "arg_negative_text"

        fun create(
            title: String? = null,
            message: String,
            positive: String? = null,
            negative: String? = null,
            tag: String? = null
        ) =
            MessageDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_MESSAGE, message)
                    putString(ARG_POSITIVE_TEXT, positive)
                    putString(ARG_NEGATIVE_TEXT, negative)
                    putString(ARG_TAG, tag)
                }
            }
    }

    interface OnClickListener {
        fun dialogPositiveClicked(tag: String) {}
        fun dialogNegativeClicked(tag: String) {}
        fun dialogCanceled(tag: String) {}
    }
}