package com.moviles.vinilos.ui.adapters.utils

import androidx.recyclerview.widget.DiffUtil
import com.moviles.vinilos.models.Album

class AlbumDiffCallback(
    private val oldList: List<Album>,
    private val newList: List<Album>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //Compara los IDs (identidad del ítem)
        return oldList[oldItemPosition].albumId == newList[newItemPosition].albumId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //Compara todos los campos del ítem
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
