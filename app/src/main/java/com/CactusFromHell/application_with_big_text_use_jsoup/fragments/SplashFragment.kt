package com.CactusFromHell.application_with_big_text_use_jsoup.fragments

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
import com.squareup.picasso.Picasso


class SplashFragment: Fragment() {

    private lateinit var navController: NavController
    private val mHandler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_splash, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("debug", "create splash")
        if (arguments!!.getString("url") != "null") {
            val liveData = DataController.getInstance(activity!!).getData()
            liveData.observe(this, Observer<String>() {
                Log.d("debug", "work now")
                val bundle = Bundle()
//            bundle.putSt
            navController.navigate(R.id.action_splashFragment_to_contentFragment, bundle)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
//        Glide
//            .with(this)
//            .load("file:///android_asset/splash_1.gif")
//            .into(view.findViewById<ImageView>(R.id.splash_gif))

//        Picasso
//            .with(activity)
//            .load(R.drawable.popup_bg)
//            .into(view.findViewById<ImageView>(R.id.splash_gif))

        Log.d("debug","argum = ${arguments != null} and ${arguments!!.getString("url") == "null"}")

        if (arguments != null && arguments!!.getString("url") == "null")
            mHandler.postDelayed({ navController.navigate(R.id.action_splashFragment_to_listFragment) }, 1 * 1000)
        else
            DataController.getInstance(activity!!).setData(arguments!!.getString("url")!!)
        Log.d("debug", "end_on_view_created")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("debug","destroy")
    }
}