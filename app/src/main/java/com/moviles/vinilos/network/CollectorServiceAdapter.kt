package com.moviles.vinilos.network

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.Volley
import com.moviles.vinilos.models.Collector
import org.json.JSONArray

class CollectorServiceAdapter(context: Context) {
    companion object{
        const val BASE_URL= "https://backvynils-q6yc.onrender.com/"
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
    fun getCollectors(onComplete:(resp:List<Collector>)->Unit, onError: (error: VolleyError)->Unit){
        requestQueue.add(getRequest("collectors",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Collector(collectorId = item.getInt("id"), name = item.getString("name"), email = item.getString("email"), telephone = item.getString("telephone")))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }
    private fun getRequest(path: String, responseListener: com.android.volley.Response.Listener<String>, errorListener: com.android.volley.Response.ErrorListener): com.android.volley.toolbox.StringRequest {
        return com.android.volley.toolbox.StringRequest(com.android.volley.Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }
}