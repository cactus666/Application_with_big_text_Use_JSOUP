package com.CactusFromHell.application_with_big_text_use_jsoup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.CactusFromHell.application_with_big_text_use_jsoup.App
import com.CactusFromHell.application_with_big_text_use_jsoup.ListAdapter
import com.CactusFromHell.application_with_big_text_use_jsoup.R

class ListFragment: Fragment() {

    private lateinit var navController: NavController
    private lateinit var list: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById(R.id.list)
        navController = Navigation.findNavController(view)

        list.setHasFixedSize(true);
        list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        list.adapter = ListAdapter(activity!!.applicationContext, App.data_from_site)
    }

}