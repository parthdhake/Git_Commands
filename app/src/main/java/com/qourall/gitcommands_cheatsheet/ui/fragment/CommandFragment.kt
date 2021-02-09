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
import com.qourall.gitcommands_cheatsheet.data.SecondaryModel
import com.qourall.gitcommands_cheatsheet.ui.adapter.CommandAdapter
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


class CommandFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_command, container, false)

        val list : ArrayList<SecondaryModel> = ArrayList()


        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("secondary_options")
            for (i in 0 until userArray.length()) {

                val sec_model = SecondaryModel()

                val detail = userArray.getJSONObject(i)

                sec_model.label = detail.getString("label")
                sec_model.value = detail.getString("value")

                if (detail.has("usage"))
                    sec_model.usage = detail.getString("usage")
                if (detail.has("nb"))
                    sec_model.nb = detail.getString("nb")

                list.add(sec_model)

            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
            Log.d("ddd",e.toString())
        }
        val customAdapter = CommandAdapter(requireContext(),list)
        Log.d("dddd",list.toString())
        recyclerView.adapter = customAdapter
        return root
    }
    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = requireContext().assets.open("secondary.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }


}