package com.qourall.gitcommands_cheatsheet.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qourall.gitcommands_cheatsheet.R
import com.qourall.gitcommands_cheatsheet.data.PrimaryModel
import com.qourall.gitcommands_cheatsheet.data.SecondaryModel
import com.qourall.gitcommands_cheatsheet.ui.adapter.CommandAdapter
import com.qourall.gitcommands_cheatsheet.ui.adapter.GitAdapter
import kotlinx.android.synthetic.main.fragment_git.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class GitFragment : Fragment() {

    lateinit var customAdapter: GitAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_git, container, false)

        val list: ArrayList<PrimaryModel> = ArrayList()

        val recyclerView = root.findViewById<RecyclerView>(R.id.primaryrecyclerView)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        try {

            val obj1 = JSONObject(loadJSONFromAsset())
            val priArray = obj1.getJSONArray("primary_options")
            for (i in 0 until priArray.length()) {
                val pri_model = PrimaryModel()

                val pridetail = priArray.getJSONObject(i)

                pri_model.label = pridetail.getString("label")
                pri_model.value = pridetail.getString("value")

                list.add(pri_model)

            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.d("ddd", e.toString())
        }
        customAdapter = GitAdapter(requireContext(), list)
        Log.d("dddd", list.toString())
        recyclerView.adapter = customAdapter
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(requireContext(), "CHange", Toast.LENGTH_SHORT).show()
                customAdapter.filter.filter(newText)

                return false
            }

        })
    }

    private fun loadJSONFromAsset(): String {
        val json1: String?
        try {
            val inputStream1 = requireContext().assets.open("primary.json")
            val size1 = inputStream1.available()
            val buffer1 = ByteArray(size1)
            val charset1: Charset = Charsets.UTF_8
            inputStream1.read(buffer1)
            inputStream1.close()
            json1 = String(buffer1, charset1)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json1
    }

}