package com.qourall.gitcommands_cheatsheet.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qourall.gitcommands_cheatsheet.R
import com.qourall.gitcommands_cheatsheet.data.PrimaryModel
import com.qourall.gitcommands_cheatsheet.data.SecondaryModel
import com.qourall.gitcommands_cheatsheet.ui.adapter.CommandAdapter
import com.qourall.gitcommands_cheatsheet.ui.adapter.GitAdapter
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class GitFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_git, container, false)

        val list : ArrayList<PrimaryModel> = ArrayList()

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
        }
        catch (e: JSONException) {
            e.printStackTrace()
            Log.d("ddd",e.toString())
        }
        val customAdapter = GitAdapter(requireContext(),list)
        Log.d("dddd",list.toString())
        recyclerView.adapter = customAdapter
        return root
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
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json1
    }

}