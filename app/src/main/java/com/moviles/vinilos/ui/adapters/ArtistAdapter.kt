package com.moviles.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.OnArtistClickListener
import com.moviles.vinilos.R
import androidx.recyclerview.widget.DiffUtil
import com.moviles.vinilos.databinding.ArtistItemBinding
import com.moviles.vinilos.models.Artist
import com.squareup.picasso.Picasso
import com.moviles.vinilos.ui.adapters.utils.ArtistDiffCallback

class ArtistsAdapter(private val clickListener: OnArtistClickListener) : RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>(){

    var artists :List<Artist> = emptyList()
        set(value) {
            val diffCallback = ArtistDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false)
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }
        holder.bind(artists[position], clickListener)
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    class ArtistViewHolder(val viewDataBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_item

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
        fun bind(artist: Artist, clickListener: OnArtistClickListener) {
            viewDataBinding.artist = artist
            viewDataBinding.cardViewArtist.setOnClickListener {
                clickListener.onArtistClick(artist.artistId)
            }
            viewDataBinding.executePendingBindings()
        }
    }
}