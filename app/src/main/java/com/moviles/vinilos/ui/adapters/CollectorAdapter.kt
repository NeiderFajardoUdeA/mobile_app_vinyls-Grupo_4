package com.moviles.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.CollectorItemBinding
import com.moviles.vinilos.models.Collector
import com.squareup.picasso.Picasso

class CollectorAdapter() : RecyclerView.Adapter<CollectorAdapter.CollectorViewHolder>() {

    var collectors: List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }
        holder.bind(collectors[position])
    }

    override fun getItemCount(): Int {
        return collectors.size
    }

    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item

            @JvmStatic
            @BindingAdapter("imageUrl")
            fun ImageView.loadImage(url: String?) {
                if (!url.isNullOrEmpty()) {
                    Picasso.get()
                        .load(url)
                        .placeholder(R.drawable.ic_album_placeholder)
                        .error(R.drawable.ic_album_placeholder)
                        .fit()
                        .centerCrop()
                        .into(this)
                } else {
                    setImageResource(R.drawable.ic_album_placeholder)
                }
            }
        }
        fun bind(collector: Collector) {
            viewDataBinding.collector = collector
            viewDataBinding.executePendingBindings()
        }
    }
}