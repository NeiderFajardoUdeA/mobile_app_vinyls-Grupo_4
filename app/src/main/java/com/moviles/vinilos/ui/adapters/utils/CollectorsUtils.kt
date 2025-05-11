package com.moviles.vinilos.ui.adapters.utils

import androidx.recyclerview.widget.DiffUtil
import com.moviles.vinilos.models.Collector

class CollectorDiffCallback(
    private val oldList: List<Collector>,
    private val newList: List<Collector>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //Compara los IDs (identidad del ítem)
        return oldList[oldItemPosition].collectorId == newList[newItemPosition].collectorId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //Compara todos los campos del ítem
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
