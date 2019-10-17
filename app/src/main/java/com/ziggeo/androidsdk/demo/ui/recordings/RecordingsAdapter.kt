package com.ziggeo.androidsdk.demo.ui.recordings

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.net.models.videos.VideoModel


/**
 * Created by Alexander Bedulin on 04-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class RecordingsAdapter(private val list: List<VideoModel>) :
    RecyclerView.Adapter<RecordingsAdapter.RecordingsViewHolder>() {

    companion object {
        const val DATE_FORMAT = "dd.MM.yyyy HH:mm"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordingsViewHolder {
        return RecordingsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recording, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecordingsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class RecordingsViewHolder(
        view: View,
        private var ivIcon: ImageView? = view.findViewById(R.id.iv_icon),
        private var tvVideoToken: TextView? = view.findViewById(R.id.tv_video_token),
        private var tvTags: TextView? = view.findViewById(R.id.tv_tags),
        private var tvStatus: TextView? = view.findViewById(R.id.tv_status),
        private var tvDate: TextView? = view.findViewById(R.id.tv_date)
    ) : RecyclerView.ViewHolder(view) {

        fun bind(model: VideoModel) {
            ivIcon?.setImageResource(R.drawable.ic_videocam_white_24dp)
            tvVideoToken?.text = model.token
            val tags = model.tags?.toString()?.replace("[", "")?.replace("]", "")
            if (tags.isNullOrEmpty()) {
                tvTags?.visibility = View.GONE
            } else {
                tvTags?.visibility = View.VISIBLE
                tvTags?.text = tags
            }
            tvDate?.text = DateFormat.format(DATE_FORMAT, model.submissionDate * 1000L)

            if (!model.stateString.isNullOrEmpty()) {
                val context = itemView.context
                tvStatus?.text = model.stateString
                val color = when (model.stateString) {
                    VideoModel.STATUS_FAILED -> ContextCompat.getColor(context, R.color.red)
                    VideoModel.STATUS_READY -> ContextCompat.getColor(context, R.color.green)
                    VideoModel.STATUS_PROCESSING -> ContextCompat.getColor(context, R.color.yellow)
                    else -> ContextCompat.getColor(context, R.color.colorSecondaryText)
                }
                tvStatus?.setTextColor(color)
            }
        }
    }
}