package com.moviles.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.OnAlbumClickListener
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.AlbumItemBinding
import com.moviles.vinilos.models.Album
import com.moviles.vinilos.ui.adapters.utils.AlbumDiffCallback
import com.squareup.picasso.Picasso

class AlbumsAdapter(private val clickListener: OnAlbumClickListener) : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    var albums :List<Album> = emptyList()
        set(value) {
            val diffCallback = AlbumDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        holder.bind(albums[position], clickListener)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item

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
        fun bind(album: Album, clickListener: OnAlbumClickListener) {
            viewDataBinding.album = album
            viewDataBinding.cardViewAlbum.setOnClickListener {
                clickListener.onAlbumClick(album.albumId)
            }
            viewDataBinding.executePendingBindings()
        }
    }
}