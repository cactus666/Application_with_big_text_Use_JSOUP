package com.CactusFromHell.application_with_big_text_use_jsoup.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.CactusFromHell.application_with_big_text_use_jsoup.R
import com.bumptech.glide.Glide
import com.CactusFromHell.application_with_big_text_use_jsoup.DataController
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.CactusFromHell.application_with_big_text_use_jsoup.Data
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.util.*
import kotlin.concurrent.thread


class SplashFragment: Fragment() {

    private lateinit var navController: NavController
    private val mHandler = Handler()
    private lateinit var runnable: Runnable
    private val gif_arr: Array<Int>

    init {
        gif_arr = arrayOf(
            R.string.gif_url_1,
            R.string.gif_url_2,
            R.string.gif_url_3,
            R.string.gif_url_4,
            R.string.gif_url_5,
            R.string.gif_url_6,
            R.string.gif_url_7,
            R.string.gif_url_8,
            R.string.gif_url_9,
            R.string.gif_url_10
            )
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_splash, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("debug", "create splash")

        if(arguments != null) {
            if (arguments!!.getString("url") != "null") {
                val liveDataMiddle = DataController.getInstance(activity!!).getDataMiddle()
                liveDataMiddle.observe(this, Observer<String>() {
                    Log.d("debug", "work now")
                    val bundle = Bundle()
                    bundle.putString("data", it)
                    bundle.putString("name", arguments!!.getString("name")!!)
                    navController.navigate(R.id.action_splashFragment2_to_contentFragment, bundle)
                })
            }
        }
        else {
            val liveDataInit = DataController.getInstance(activity!!).getDataInit()
            liveDataInit.observe(this, Observer<Boolean>() {
                Log.d("debug", "init_liva data init")
                navController.navigate(R.id.action_splashFragment_to_listFragment)
//                mHandler.postDelayed({ navController.navigate(R.id.action_splashFragment_to_listFragment) }, 1 * 1000)
            })
        }
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        Glide
            .with(this)
            .load(getString(gif_arr[(0..9).random()]))
            .into(view.findViewById<ImageView>(R.id.splash_gif))

//        "file:///android_asset/splash_1.gif"

        runnable = Runnable { DataController.getInstance(activity!!.applicationContext).setDataInit(isReady = true) }
//        try{
//        thread(start  = true) {
            if (arguments == null) {
                mHandler.postDelayed(runnable, 3 * 1000)
            } else if (arguments!!.getString("url") != "null") {
                DataController.getInstance(activity!!).setDataMiddle(arguments!!.getString("url")!!)
            }
//        } catch(e: Exception){
//            Log.e("error", e.toString())
//
//        }
//        }


        /*
        thread(start  = true) {
            if (arguments != null && arguments!!.getString("url") == "null") {
//            DataController.getInstance(activity!!).setDateInit()
                mHandler.postDelayed({ navController.navigate(R.id.action_splashFragment_to_listFragment) }, 1 * 1000)
            } else {
                DataController.getInstance(activity!!).setDataMiddle(arguments!!.getString("url")!!)
            }
            Log.d("debug", "end_on_view_created")
        }
        */
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("debug","destroy")
    }
}