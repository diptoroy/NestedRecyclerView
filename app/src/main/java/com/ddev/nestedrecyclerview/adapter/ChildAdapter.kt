package com.ddev.nestedrecyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.nestedrecyclerview.model.DataChild
import com.ddev.nestedrecyclerview.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.data_child_row.view.*
import kotlinx.android.synthetic.main.data_row.view.*

class ChildAdapter : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    private var dataList = emptyList<DataChild>()
    private var count: Int = 0
    private lateinit var db: FirebaseFirestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.data_child_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.amount_Text.text = dataList[position].collectCustomer.toString()
        holder.itemView.customer_Text.text = dataList[position].collectAmount.toString()
        holder.itemView.click_count_text.text = dataList[position].clickCount.toString()

        var id: String = dataList[position].childId
        db = FirebaseFirestore.getInstance()
        holder.itemView.setOnClickListener {

            count = Integer.parseInt(holder.itemView.click_count_text.text as String);
            count++
            holder.itemView.click_count_text.text = count.toString()


            var name: String = dataList[position].collectCustomer
            var amount: Int = dataList[position].collectAmount
            var clickCount: Int = count
            val childId = DataChild(id, name, amount, clickCount)


            db.collection("ClickCount").document(id).set(childId).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("count!", "${it.result}")
//                    holder.itemView.click_count_text.text = "$count"

                } else {
                    Log.d("error", "${it.exception}")
                }
            }
        }

        db.collection("ClickCount").document(id).get().addOnCompleteListener {
            if (it.result!!.exists()){
                val click: Int = it.result!!.getLong("clickCount")!!.toInt()
                holder.itemView.click_count_text.text = "$click"
            }
        }

    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setChildData(newList: List<DataChild>) {
        notifyDataSetChanged()
        dataList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


