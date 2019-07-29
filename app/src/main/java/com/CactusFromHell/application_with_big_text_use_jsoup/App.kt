package com.CactusFromHell.application_with_big_text_use_jsoup

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import kotlin.concurrent.thread
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig



class Data(val name: String, val url: String)


class App: Application() {

    companion object {
        lateinit var data_from_site: List<Data>
    }

    override fun onCreate() {
        super.onCreate()

        // Creating an extended library configuration.
        val config = YandexMetricaConfig.newConfigBuilder("5e12de53-d4fc-4135-b8fb-4f59d7557103").build()
        // Initializing the AppMetrica SDK.
        YandexMetrica.activate(applicationContext, config)
        // Automatic tracking of user activity.
        YandexMetrica.enableActivityAutoTracking(this)

        thread(start  = true) {
            val XmlFileInputStream: InputStream= resources.openRawResource(R.raw.list_data);
            val jsonString = readTextFile(XmlFileInputStream)

            val gson = GsonBuilder().create()
            data_from_site = gson.fromJson(jsonString, Array<Data>::class.java).toList()
            DataController.getInstance(this.applicationContext).setDataInit(isReadyInApp = true)
        }
//        DataController.getInstance(this.applicationContext).setDateInit(true)
    }

    fun readTextFile(inputStream: InputStream): String {
        val outputStream = ByteArrayOutputStream()

        val buf = ByteArray(1024)

        var len: Int = inputStream.read(buf)
        try {
            while (len != -1) {
                outputStream.write(buf, 0, len)
                len = inputStream.read(buf)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {}
        return outputStream.toString()
    }
}

