package com.ddev.nestedrecyclerview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddev.nestedrecyclerview.R
import com.ddev.nestedrecyclerview.adapter.ChildAdapter
import com.ddev.nestedrecyclerview.model.DataChild
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {

    private val childAdapter by lazy { ChildAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        val intentData = intent.getParcelableArrayListExtra<DataChild>("list") as ArrayList<DataChild>
        Log.d("intent","$intentData")


        setUpChildRecyclerView()

        childRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        childRecyclerView.setHasFixedSize(true)
        childRecyclerView.adapter = childAdapter
        childAdapter.setChildData(intentData)
    }

    private fun setUpChildRecyclerView() {

    }

}