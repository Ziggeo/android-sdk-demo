package com.ziggeo.androidsdk.demo.ui.topclients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.data.feature.ClientModel


/**
 * Created by Alexander Bedulin on 04-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class TopClientsAdapter(private val list: List<ClientModel>) :
    RecyclerView.Adapter<TopClientsAdapter.TopClientsViewHolder>() {

    var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopClientsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clients, parent, false)
        val viewHolder = TopClientsViewHolder(
            onItemClickListener,
            view
        )
        viewHolder.ivLogo?.setColorFilter(
            ContextCompat.getColor(view.context, R.color.grayFilter)
        )
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TopClientsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class TopClientsViewHolder(
        private var onItemClickListener: ItemClickListener?,
        private var view: View,
        var ivLogo: ImageView? = view.findViewById(R.id.iv_logo)
    ) : RecyclerView.ViewHolder(view) {

        fun bind(model: ClientModel) {
            view.setOnClickListener {
                onItemClickListener?.onItemClick(model)
            }
            ivLogo?.setImageResource(model.drawableRes)
        }
    }

    interface ItemClickListener {
        fun onItemClick(model: ClientModel)
    }
}