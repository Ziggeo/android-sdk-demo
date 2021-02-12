package com.ziggeo.androidsdk.demo.ui.recordings

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ziggeo.androidsdk.net.models.ContentModel
import com.ziggeo.androidsdk.net.models.audios.Audio
import com.ziggeo.androidsdk.net.models.images.Image
import com.ziggeo.androidsdk.net.models.videos.VideoModel


/**
 * Created by Alexander Bedulin on 04-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class RecordingsAdapter(private val list: List<ContentModel>) :
    RecyclerView.Adapter<RecordingsAdapter.RecordingsViewHolder>() {

    var onItemClickListener: ItemClickListener? = null

    companion object {
        const val DATE_FORMAT = "dd.MM.yyyy HH:mm"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordingsViewHolder {
        return RecordingsViewHolder(
            onItemClickListener,
            LayoutInflater.from(parent.context)
                .inflate(com.ziggeo.androidsdk.demo.R.layout.item_recording, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecordingsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class RecordingsViewHolder(
        private var onItemClickListener: ItemClickListener?,
        private var view: View,
        private var ivIcon: ImageView? = view.findViewById(com.ziggeo.androidsdk.demo.R.id.iv_icon),
        private var tvVideoToken: TextView? = view.findViewById(com.ziggeo.androidsdk.demo.R.id.tv_video_token),
        private var tvTags: TextView? = view.findViewById(com.ziggeo.androidsdk.demo.R.id.tv_tags),
        private var tvStatus: TextView? = view.findViewById(com.ziggeo.androidsdk.demo.R.id.tv_status),
        private var tvDate: TextView? = view.findViewById(com.ziggeo.androidsdk.demo.R.id.tv_date)
    ) : RecyclerView.ViewHolder(view) {

        fun bind(model: ContentModel) {
            view.setOnClickListener {
                onItemClickListener?.onItemClick(view, adapterPosition)
            }
            tvVideoToken?.text = model.token
            if (model is VideoModel) {
                ivIcon?.setImageResource(com.ziggeo.androidsdk.demo.R.drawable.ic_videocam_white_24dp)

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
                        VideoModel.STATUS_FAILED -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.red
                        )
                        VideoModel.STATUS_READY -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.green
                        )
                        VideoModel.STATUS_PROCESSING -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.yellow
                        )
                        else -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.colorSecondaryText
                        )
                    }
                    tvStatus?.setTextColor(color)
                }
            }

            if (model is Audio) {
                ivIcon?.setImageResource(com.ziggeo.androidsdk.demo.R.drawable.ic_mic_white_24dp)

                val tags = model.tags?.toString()?.replace("[", "")?.replace("]", "")
                if (tags.isNullOrEmpty()) {
                    tvTags?.visibility = View.GONE
                } else {
                    tvTags?.visibility = View.VISIBLE
                    tvTags?.text = tags
                }
                tvDate?.text = DateFormat.format(DATE_FORMAT, model.submissionDate)

                if (!model.approved.isNullOrEmpty()) {
                    val context = itemView.context
                    tvStatus?.text = model.approved
                    val color = when (model.approved) {
                        VideoModel.STATUS_FAILED -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.red
                        )
                        VideoModel.STATUS_READY -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.green
                        )
                        VideoModel.STATUS_PROCESSING -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.yellow
                        )
                        else -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.colorSecondaryText
                        )
                    }
                    tvStatus?.setTextColor(color)
                }
            }

            if (model is Image) {
                ivIcon?.setImageResource(com.ziggeo.androidsdk.demo.R.drawable.ic_photo_white_24dp)

                val tags = model.tags?.toString()?.replace("[", "")?.replace("]", "")
                if (tags.isNullOrEmpty()) {
                    tvTags?.visibility = View.GONE
                } else {
                    tvTags?.visibility = View.VISIBLE
                    tvTags?.text = tags
                }
                tvDate?.text = DateFormat.format(DATE_FORMAT, model.date)

                if (!model.approved.isNullOrEmpty()) {
                    val context = itemView.context
                    tvStatus?.text = model.approved
                    val color = when (model.approved) {
                        VideoModel.STATUS_FAILED -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.red
                        )
                        VideoModel.STATUS_READY -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.green
                        )
                        VideoModel.STATUS_PROCESSING -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.yellow
                        )
                        else -> ContextCompat.getColor(
                            context,
                            com.ziggeo.androidsdk.demo.R.color.colorSecondaryText
                        )
                    }
                    tvStatus?.setTextColor(color)
                }
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}