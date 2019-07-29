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
import java.lang.Exception
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
/*
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
                println("qwer = ${element.text()} end")
//                println("qwer = ${element.tagName()} end")
            }
//            println("qwer = ${result} end")


//            val replaceAll = result.toString().replace("<h3>\"Видео\".+?", "")

//            val replaceAll = Pattern.matches(".*<h3>Видео<h3>",  result.toString())
//            println(replaceAll)


//            println(result)
//            liveDataMiddle.postValue(new_url)
        }
    }
*/
fun setDataMiddle(new_url: String) {
    thread(start  = true) {
        try {
            val doc = Jsoup.connect(new_url).get()

            val result = doc.getElementsByClass("contentpaneopen")[2]

            val r2 = Jsoup.parse(result.toString())
            var s = r2.getElementsByAttribute("align").toString()
            s = s.replace("<div align=\"justify\">", "")
            try {
                s = s.substring(0, s.indexOf("<h3>Видео</h3>"))
            }catch(e: Exception){
                try {
                    s = s.substring(0, s.indexOf("<h3>Растительноядные соседи</h3>"))
                }catch(e: Exception){
                    try {
                        s = s.substring(0, s.indexOf("<h3>Динозавры соседних свит</h3>"))
                    }catch(e: Exception){
                        try {
                            s = s.substring(0, s.indexOf("<h3>Представители близких семейств</h3>"))
                        }catch(e: Exception){}
                    }
                }
            }
            s = s.replace("/ii".toRegex(), "http://dinosaurs.afly.ru/ii")
            s = s.replace("<a href=.*>".toRegex(), "")
            s = s.replace("width=\"\\d*\"".toRegex(), "width = 95%")
            s = s.replace("height=\"\\d*\"".toRegex(), "height = 60%")

            val table = s.substring(s.indexOf("<table"), s.indexOf("</table>") + 8)
            val table_doc = Jsoup.parse("<html><head></head><body>$table</body></html>")
            val new_table = """
                <table ><tbody> 
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(0).getElementsByTag("td").get(0)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(1).getElementsByTag("td").get(0)} </td> 
   </tr> 
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(0).getElementsByTag("td").get(1)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(1).getElementsByTag("td").get(1)} </td> 
   </tr>  
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(0).getElementsByTag("td").get(2)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(1).getElementsByTag("td").get(2)} </td> 
   </tr> 
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(0).getElementsByTag("td").get(3)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(1).getElementsByTag("td").get(3)} </td> 
   </tr> 
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(0).getElementsByTag("td").get(4)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(1).getElementsByTag("td").get(4)} </td> 
   </tr> 
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(2).getElementsByTag("td").get(0)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(3).getElementsByTag("td").get(0)} </td> 
   </tr>  
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(2).getElementsByTag("td").get(1)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(3).getElementsByTag("td").get(1)} </td> 
   </tr>  
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(2).getElementsByTag("td").get(2)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(3).getElementsByTag("td").get(2)} </td> 
   </tr>  
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(2).getElementsByTag("td").get(3)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(3).getElementsByTag("td").get(3)} </td> 
   </tr> 
   <tr align="center"> 
    <td align="left"> ${table_doc.getElementsByTag("tr").get(2).getElementsByTag("td").get(4)} </td> 
    <td align="right"> ${table_doc.getElementsByTag("tr").get(3).getElementsByTag("td").get(4)} </td> 
   </tr> 
  </tbody> 
 </table>
            """.trimIndent()
//            s = s.replace("<table ".toRegex(), "<table width = 95% ")

            s = s.replace(s.substring(s.indexOf("<h3>Визитная карточка"), s.indexOf("</table>") + 8), new_table)

            println(s+" END2")
            val r3 = Jsoup.parse(s)

            liveDataMiddle.postValue(r3.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }




    }
}
}