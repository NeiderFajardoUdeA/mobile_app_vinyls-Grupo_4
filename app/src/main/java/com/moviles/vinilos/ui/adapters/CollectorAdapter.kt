package com.moviles.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.OnCollectorClickListener
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.CollectorItemBinding
import com.moviles.vinilos.models.Collector
import com.moviles.vinilos.ui.adapters.utils.CollectorDiffCallback

class CollectorsAdapter(private val clickListener: OnCollectorClickListener) : RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>() {

    var collectors: List<Collector> = emptyList()
        set(value) {
            val diffCallback = CollectorDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
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
        holder.bind(collectors[position], clickListener)
    }

    override fun getItemCount(): Int {
        return collectors.size
    }

    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }
        fun bind(collector: Collector, clickListener: OnCollectorClickListener) {
            viewDataBinding.collector = collector
            viewDataBinding.cardViewCollector.setOnClickListener {
                clickListener.onCollectorClick(collector.collectorId)
            }
            viewDataBinding.executePendingBindings()
        }
    }
}