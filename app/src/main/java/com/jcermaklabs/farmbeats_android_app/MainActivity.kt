package com.jcermaklabs.farmbeats_android_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
//import com.android.volley.*
//import com.android.volley.toolbox.*
import org.json.JSONObject
import org.json.JSONTokener
//import com.android.volley.Request
//import com.android.volley.toolbox.StringRequest


class MainActivity : AppCompatActivity() {


    private lateinit var empToRetrieve: EditText
    private lateinit var farmbeatsData: TextView


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //retrieve data button
    //getting strings from JSON objects
    fun  retrieveData(view: View)
    {
        //declaring variables
        val requestManager = RequestController.getInstance(this)
        val url = "http://192.168.0.118/jake_readings"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val jsonObject = JSONTokener(response).nextValue() as JSONObject
                farmbeatsData.text = jsonObject.getString("farmbeats_data")
            },
            { error -> //handle error
                farmbeatsData.text = "Failure"
            })
        //adding the request to the request queue
        requestManager.addToRequestQueue(stringRequest)
    }
}