package com.CactusFromHell.application_with_big_text_use_jsoup.fragments

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.CactusFromHell.application_with_big_text_use_jsoup.R


class ContentFragment: Fragment(), View.OnClickListener {

    private lateinit var webView: WebView
    private lateinit var navController: NavController
    private lateinit var title_content: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_content, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        webView = view.findViewById(R.id.webView)
        view.findViewById<ImageView>(R.id.arrow_back).setOnClickListener(this)
        title_content = view.findViewById(R.id.title_content)
        loadContent(arguments!!.getString("data")!!)

        println(arguments!!.getString("data"))
    }

    private fun loadContent(content: String){
//        webView.settings.javaScriptEnabled = true
//        title_content.text = arguments!!.getString("name")!!
        webView.loadData(content, "text/html", "UTF-8")
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.arrow_back -> navController.popBackStack()
            R.id.share -> share()
        }
    }

    private fun share() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"

        val shareBody = "Here is the share content body"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here")
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }

}