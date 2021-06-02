package com.ddev.nestedrecyclerview.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddev.nestedrecyclerview.util.OnClickListener
import com.ddev.nestedrecyclerview.R
import com.ddev.nestedrecyclerview.adapter.Adapter
import com.ddev.nestedrecyclerview.model.Bookmark
import com.ddev.nestedrecyclerview.model.Data
import com.ddev.nestedrecyclerview.model.DataChild
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.data_row.*

class MainActivity : AppCompatActivity(),
    OnClickListener {

    private val dataAdapter by lazy {
        Adapter(
            this
        )
    }
    private var isBookmark:Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()

        all_bookmark_btn.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setUpRecyclerView() {
        dataRecyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false

        )
        dataRecyclerview.setHasFixedSize(true)
        val dataList = ArrayList<Data>()
        val dataChild1 = ArrayList<DataChild>()
        dataChild1.add(
            DataChild(
                "22",
                "Jubair",
                2000,
                0
            )
        )
        val dataChild2 = ArrayList<DataChild>()
        dataChild2.add(
            DataChild(
                "44",
                "Ahmed",
                3200,
                0
            )
        )
        val dataChild3 = ArrayList<DataChild>()
        dataChild3.add(
            DataChild(
                "55",
                "Golam",
                1234,
                0
            )
        )
        dataChild3.add(
            DataChild(
                "66",
                "faruk",
                4567,
                0
            )
        )
        dataList.add(
            Data(
                1,
                "Dipto Roy",
                dataChild1
            )
        )
        dataList.add(
            Data(
                2,
                "Asif Khan",
                dataChild2
            )
        )
        dataList.add(
            Data(
                3,
                "Jahangir Hossain",
                dataChild3
            )
        )
        dataRecyclerview.adapter = dataAdapter
        dataAdapter.setData(dataList)



    }

    override fun onClick(item: Data, position: Int) {
        Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
        Log.d("Clicked :","$item")


        val intent = Intent(this, NextActivity::class.java)
        intent.putExtra("list", ArrayList(item.customer))
        startActivity(intent)
    }



}




