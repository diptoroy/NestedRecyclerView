package com.ddev.nestedrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.nestedrecyclerview.R
import com.ddev.nestedrecyclerview.model.Bookmark
import kotlinx.android.synthetic.main.bookmark_row.view.*
import kotlinx.android.synthetic.main.data_row.view.*

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    private var bookmarkList = emptyList<Bookmark>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bookmark_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.c_id.text = bookmarkList[position].id.toString()
        holder.itemView.name.text = bookmarkList[position].name.toString()

    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    fun setData(newList: List<Bookmark>){
        notifyDataSetChanged()
        bookmarkList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}