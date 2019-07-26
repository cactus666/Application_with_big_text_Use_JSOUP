package com.CactusFromHell.application_with_big_text_use_jsoup

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.concurrent.thread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException
import java.util.Collections.replaceAll
import java.util.regex.Pattern


class DataController private constructor(context: Context){

    private lateinit var liveDataMiddle: MutableLiveData<String>
    private var liveDataInit = MutableLiveData<Boolean>()
    private var isReadyInApp: Boolean = false
    private var isReady: Boolean = false

    companion object {
        private var instance : DataController? = null

        fun getInstance(context: Context): DataController = synchronized(this) {
            if (instance == null)
                instance = DataController(context)
            return instance!!
        }

        fun clearInstance() {
            instance = null
        }
    }

    fun getDataMiddle(): LiveData<String> {
        liveDataMiddle = MutableLiveData<String>()
        return liveDataMiddle
    }

    fun getDataInit(): LiveData<Boolean> {
        return liveDataInit
    }


    fun setDataInit(isReadyInApp: Boolean = false, isReady: Boolean = false) {
        this.isReady = isReady
        if (!this.isReadyInApp)
            this.isReadyInApp = isReadyInApp

        Log.d("debug", "set Data init")
        if (this.isReadyInApp && this.isReady)
            liveDataInit.postValue(isReady)
    }

    fun setDataMiddle(new_url: String) {
        thread(start  = true) {
            Log.d("debug", "set data middle")

            var doc: Document? = null
            try {
                doc = Jsoup.connect(new_url).get()
            } catch (e: IOException) {
                Log.e("error", "${e.printStackTrace()}")
            }
            val html = Jsoup.parse(new_url)
            val result = doc!!.select("table.contentpaneopen")[2].getElementsByAttribute("align")//.select("h3")//.attr("justify")
            for(element: Element in result) {
//                val h3_block: Document = Jsoup.parse(element.toString())
//                if(element.tagName() == "h3" && element.getElementById("h3").toString() == "Видео")
//                    break
//                if(element.getElementsByTag("h3").toString() == "Видео")
//                    break
                println("qwer = ${element.tagName()} end")
            }
//            println("qwer = ${result} end")


//            val replaceAll = result.toString().replace("<h3>\"Видео\".+?", "")

//            val replaceAll = Pattern.matches(".*<h3>Видео<h3>",  result.toString())
//            println(replaceAll)


//            println(result)
//            liveDataMiddle.postValue(new_url)
        }
    }

}