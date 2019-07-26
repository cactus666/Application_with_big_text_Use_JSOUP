package com.CactusFromHell.application_with_big_text_use_jsoup


import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.navigation.NavController
import androidx.navigation.Navigation
import android.R.id.edit
import android.content.SharedPreferences.Editor


class ListAdapter(context: Context, private val mData: List<Data>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater
    private val context: Context
    private lateinit var navController: NavController

    init {
        this.mInflater = LayoutInflater.from(context)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_for_list, parent, false)
        navController = Navigation.findNavController(parent)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mData[position].name
        holder.url = mData[position].url
    }



    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name: TextView = itemView.findViewById(R.id.redirect_to_content)
        lateinit var url: String

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val bundle = Bundle()
            bundle.putString("url", url)
            navController.navigate(R.id.action_listFragment_to_splashFragment2, bundle)
        }
    }
}

