package com.CactusFromHell.application_with_big_text_use_jsoup

import android.content.Context
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData



class DataController private constructor(context: Context){

    private val liveData = MutableLiveData<String>()
    private val mHandler = Handler()

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

    fun getData(): LiveData<String> {
        return liveData
    }

    fun setData(new_url: String) {
        mHandler.postDelayed({
            for(i in 1..100000){
                println("frf")
            }
            liveData.postValue(new_url)
        }, 0)

    }

}