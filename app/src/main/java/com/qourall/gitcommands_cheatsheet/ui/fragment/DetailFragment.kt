package com.qourall.gitcommands_cheatsheet.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.qourall.gitcommands_cheatsheet.R
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.time.LocalDate


class DetailFragment : Fragment() {


    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_detail, container, false)

        val op = root.findViewById<TextView>(R.id.operation)
        val com = root.findViewById<TextView>(R.id.command)
        val note = root.findViewById<TextView>(R.id.note)

        val k = args.command
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("secondary_options")

            val detail = userArray.getJSONObject(k)

            op.text = detail.getString("label")
            com.text = detail.getString("usage")
            note.text = detail.getString("nb")


        }
        catch (e: JSONException) {
            e.printStackTrace()
            Log.d("ddd",e.toString())
        }

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