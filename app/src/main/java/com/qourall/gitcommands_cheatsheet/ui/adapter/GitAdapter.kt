package com.qourall.gitcommands_cheatsheet.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.qourall.gitcommands_cheatsheet.R
import com.qourall.gitcommands_cheatsheet.data.PrimaryModel
import com.qourall.gitcommands_cheatsheet.data.SecondaryModel
import com.qourall.gitcommands_cheatsheet.ui.fragment.CommandFragment
import com.qourall.gitcommands_cheatsheet.ui.fragment.CommandFragmentDirections
import com.qourall.gitcommands_cheatsheet.ui.fragment.GitFragmentDirections
import java.util.*
import kotlin.collections.ArrayList

class GitAdapter(private val context: Context, private val data: ArrayList<PrimaryModel>) :
    RecyclerView.Adapter<GitAdapter.GitViewHolder>(),
    Filterable {


    lateinit var navController: NavController

    private var list = ArrayList<PrimaryModel>()

    init {
        list = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        navController = Navigation.findNavController(parent)

        return GitViewHolder(
            LayoutInflater.from(context).inflate(R.layout.primary_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.itemTitle.text = list[position].label.capitalize()

        holder.itemView.setOnClickListener {
            val action =
                GitFragmentDirections.actionGitFragmentToCommandFragment(list[position].label)
            navController.navigate(action)
        }
    }

    override fun getItemCount(): Int {

        return list.size

    }

    inner class GitViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val itemTitle: TextView = v.findViewById(R.id.itemTitle)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Log.d("dvs",charSearch)
                if (charSearch.isEmpty()) {
                    list = data
                } else {
                    val resultList = ArrayList<PrimaryModel>()
                    for (row in list) {
                        if (row.value.contains(charSearch)) {
                            resultList.add(row)
                        }
                    }
                    list = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = list
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list = results?.values as ArrayList<PrimaryModel>
                notifyDataSetChanged()
            }

        }
    }
}