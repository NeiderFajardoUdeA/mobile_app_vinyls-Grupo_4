package com.moviles.vinilos.network

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.Volley
import com.moviles.vinilos.models.Collector
import com.moviles.vinilos.utils.Config
import org.json.JSONArray
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CollectorServiceAdapter(context: Context) {
    companion object{
        private var instance: CollectorServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CollectorServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    suspend fun getCollectors() = suspendCoroutine<List<Collector>>{ cont->
        requestQueue.add(getRequest("collectors",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Collector(collectorId = item.getInt("id"), name = item.getString("name"), email = item.getString("email"), telephone = item.getString("telephone")))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it as VolleyError)
            }))
    }
    private fun getRequest(path: String, responseListener: com.android.volley.Response.Listener<String>, errorListener: com.android.volley.Response.ErrorListener): com.android.volley.toolbox.StringRequest {
        return com.android.volley.toolbox.StringRequest(com.android.volley.Request.Method.GET, Config.BASE_URL + path, responseListener, errorListener)
    }
}