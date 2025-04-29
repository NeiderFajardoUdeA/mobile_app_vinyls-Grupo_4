package com.moviles.vinilos.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.moviles.vinilos.models.Artist
import org.json.JSONArray

class ArtistServiceAdapter(context: Context) {
    companion object{
        const val BASE_URL= "http://172.190.114.252/"
        private var instance: AlbumServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: AlbumServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getArtists(onComplete:(resp:List<Artist>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("musicians",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artist>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Artist(artistId = item.getInt("id"), name = item.getString("name"), image = item.getString("cover"), description = item.getString("recordLabel"), birthDate = item.getString("birthDate")))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
}