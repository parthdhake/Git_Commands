package com.qourall.gitcommands_cheatsheet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.qourall.gitcommands_cheatsheet.R
import com.qourall.gitcommands_cheatsheet.data.SecondaryModel
import com.qourall.gitcommands_cheatsheet.ui.fragment.CommandFragmentDirections

class CommandAdapter(private val context: Context , private val data : ArrayList<SecondaryModel>) : RecyclerView.Adapter<CommandAdapter.CommandViewHolder>() {


    lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder {
        navController = Navigation.findNavController(parent)

        return CommandViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) {
        holder.itemTitle.text = data[position].usage
        holder.itemContent.text = data[position].label.capitalize()


        holder.itemView.setOnClickListener {
            val action = CommandFragmentDirections.actionCommandFragmentToDetailFragment(position)
            navController.navigate(action)
        }

/*        holder.itemView.setOnClickListener {
            clickInterface.clickListener(position)
        }*/
    }

    override fun getItemCount(): Int {

        return data.size

    }

    inner class CommandViewHolder(v : View) : RecyclerView.ViewHolder(v){


        val itemTitle: TextView = v.findViewById(R.id.itemTitle)
        val itemContent: TextView = v.findViewById(R.id.itemContent)

    }


}