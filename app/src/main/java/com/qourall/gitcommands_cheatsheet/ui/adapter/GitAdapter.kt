package com.qourall.gitcommands_cheatsheet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qourall.gitcommands_cheatsheet.R
import com.qourall.gitcommands_cheatsheet.data.PrimaryModel
import com.qourall.gitcommands_cheatsheet.data.SecondaryModel
import com.qourall.gitcommands_cheatsheet.ui.fragment.CommandFragment

class GitAdapter(private val context: Context , private val data : ArrayList<PrimaryModel>) : RecyclerView.Adapter<GitAdapter.GitViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        return GitViewHolder(LayoutInflater.from(context).inflate(R.layout.primary_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.itemTitle.text = data[position].label.capitalize()

/*        holder.itemView.setOnClickListener {
            clickInterface.clickListener(position)
        }*/
    }

    override fun getItemCount(): Int {

        return data.size

    }

    inner class GitViewHolder(v : View) : RecyclerView.ViewHolder(v){

        val itemTitle: TextView = v.findViewById(R.id.itemTitle)

    }
}