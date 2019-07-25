package com.CactusFromHell.application_with_big_text_use_jsoup

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class Data(val name: String, val url: String)


class App: Application() {

    companion object {
        lateinit var data_from_site: List<Data>
    }

    override fun onCreate() {
        super.onCreate()

        val XmlFileInputStream: InputStream= resources.openRawResource(R.raw.list_data);
        val jsonString = readTextFile(XmlFileInputStream)

        val gson = GsonBuilder().create()
        data_from_site = gson.fromJson(jsonString, Array<Data>::class.java).toList()
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

