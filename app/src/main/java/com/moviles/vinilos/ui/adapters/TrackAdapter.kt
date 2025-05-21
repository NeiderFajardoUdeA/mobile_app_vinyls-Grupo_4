package com.moviles.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.R
import com.moviles.vinilos.models.Track

class TracksAdapter(private val tracks: List<Track>) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {

    class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackName: TextView = itemView.findViewById(R.id.trackName)
        val trackDuration: TextView = itemView.findViewById(R.id.trackDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.track_item, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.trackName.text = track.name
        holder.trackDuration.text = track.duration
    }

    override fun getItemCount(): Int = tracks.size
}