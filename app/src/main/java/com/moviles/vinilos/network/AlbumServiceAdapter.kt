package com.moviles.vinilos.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.moviles.vinilos.models.Album
import com.moviles.vinilos.models.Track
import com.moviles.vinilos.utils.Config
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AlbumServiceAdapter(context: Context) {
    companion object{
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

    suspend fun getAlbums() = suspendCoroutine<List<Album>>{ cont->
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val tracks = mutableListOf<Track>()
                    for (j in 0 until item.getJSONArray("tracks").length()) {
                        val track = item.getJSONArray("tracks").getJSONObject(j)
                        tracks.add(Track(id = track.getInt("id"), name = track.getString("name"), duration = track.getString("duration")))
                    }
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description"), tracks = tracks))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun createAlbum(body: JSONObject): JSONObject = suspendCoroutine { cont ->
        val request = postrequest(
            "albums",
            body,
            { response ->
                cont.resume(response)
            },
            { error ->
                cont.resumeWithException(error)
            }
        )
        requestQueue.add(request)
    }

    suspend fun addTrack(albumId: Int, track: Track) = suspendCoroutine<Unit> { cont ->
        val body = "{ \"name\": \"${track.name}\", \"duration\": \"${track.duration}\" }"
        try {
            requestQueue.add(
                postRequest("albums/$albumId/tracks", body,
                    { response ->
                        cont.resume(Unit)
                        Log.d("AlbumServiceAdapter", "addTrack: $response")
                    },
                    {
                        cont.resumeWithException(it)
                    })
            )
        } catch (e: Exception) {
            cont.resumeWithException(e)
            Log.e("AlbumServiceAdapter", "Error adding track: ${e.message}", e)
        }
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, Config.BASE_URL+path, responseListener,errorListener)
    }

    private fun postrequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, Config.BASE_URL+path, body, responseListener, errorListener)
    }

    private fun postRequest(path:String, body:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return object: StringRequest(Request.Method.POST, Config.BASE_URL+path, responseListener,errorListener){
            override fun getBody(): ByteArray {
                return body.toByteArray()
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
    }
}