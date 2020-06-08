package com.ziggeo.androidsdk.demo.ui.sdks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.data.feature.CategoryModel
import com.ziggeo.androidsdk.demo.model.data.feature.FeatureModel
import com.ziggeo.androidsdk.demo.model.data.feature.SdkModel
import com.ziggeo.androidsdk.demo.ui.global.FeatureViewHolder

/**
 * Created by Alexander Bedulin on 04-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
const val TYPE_CATEGORY = 0
const val TYPE_ITEM = 1

class AvailableSDKsAdapter(private val list: List<FeatureModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClickListener: ItemClickListener? = null

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is CategoryModel) {
            TYPE_CATEGORY
        } else {
            TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_clients, parent, false)
            val viewHolder = SdkInfoViewHolder(onItemClickListener, view)
            viewHolder.ivLogo?.setColorFilter(
                ContextCompat.getColor(view.context, R.color.grayFilter)
            )
            viewHolder
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
            CategoryNameViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SdkInfoViewHolder) {
            holder.bind(list[position] as SdkModel)
        } else if (holder is CategoryNameViewHolder) {
            holder.bind(list[position] as CategoryModel)
        }
    }

    class SdkInfoViewHolder(
        private var onItemClickListener: ItemClickListener?,
        private var view: View,
        var ivLogo: ImageView? = view.findViewById(R.id.iv_logo)
    ) : FeatureViewHolder(view) {

        fun bind(model: SdkModel) {
            view.setOnClickListener {
                onItemClickListener?.onItemClick(model)
            }
            ivLogo?.setImageResource(model.drawableRes)
        }
    }

    class CategoryNameViewHolder(
        private var view: View,
        private val tvName: TextView? = view.findViewById(R.id.tv_name)
    ) : FeatureViewHolder(view) {
        fun bind(model: CategoryModel) {
            tvName?.text = model.name
        }
    }

    interface ItemClickListener {
        fun onItemClick(model: SdkModel)
    }
}