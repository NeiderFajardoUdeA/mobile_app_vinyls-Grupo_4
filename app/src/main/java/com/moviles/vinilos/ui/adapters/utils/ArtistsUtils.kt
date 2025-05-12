package com.moviles.vinilos.ui.adapters.utils

import androidx.recyclerview.widget.DiffUtil
import com.moviles.vinilos.models.Artist

class ArtistDiffCallback(
    private val oldList: List<Artist>,
    private val newList: List<Artist>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //Compara los IDs (identidad del ítem)
        return oldList[oldItemPosition].artistId == newList[newItemPosition].artistId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //Compara todos los campos del ítem
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
