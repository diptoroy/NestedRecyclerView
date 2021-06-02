package com.ddev.nestedrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddev.nestedrecyclerview.model.Data
import com.ddev.nestedrecyclerview.util.OnClickListener
import com.ddev.nestedrecyclerview.R
import com.ddev.nestedrecyclerview.db.BookmarkDatabase
import com.ddev.nestedrecyclerview.model.Bookmark
import kotlinx.android.synthetic.main.data_row.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Adapter(var onClickListener: OnClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var dataList = emptyList<Data>()
    private var isBookmark = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.id_Text.text = dataList[position].id.toString()
        holder.itemView.name_Text.text = dataList[position].name.toString()

        holder.itemView.setOnClickListener {
            onClickListener.onClick(dataList[position], position)
        }

        GlobalScope.launch {
            if (BookmarkDatabase.invoke(holder.itemView.context).getBookmarkDao()
                    .isBookmark(dataList[position].id) == 1
            ) {
                holder.itemView.bookmark_btn.setImageResource(R.drawable.ic_baseline_bookmark_24)
            } else {
                holder.itemView.bookmark_btn.setImageResource(R.drawable.ic_baseline_bookmark_24_gray)
            }
        }


//        isBookmark = true
        holder.itemView.bookmark_btn.setOnClickListener {
            val bookmark = Bookmark()
            val id: Int = dataList[position].id
            val name: String = dataList[position].name

            bookmark.id = id
            bookmark.name = name


            GlobalScope.launch {
                if (BookmarkDatabase.invoke(holder.itemView.context).getBookmarkDao()
                        .isBookmark(id) != 1
                ) {
                    holder.itemView.bookmark_btn.setImageResource(R.drawable.ic_baseline_bookmark_24)
                    BookmarkDatabase.invoke(holder.itemView.context).getBookmarkDao()
                        .bookmarkInsert(bookmark)
                } else {
                    holder.itemView.bookmark_btn.setImageResource(R.drawable.ic_baseline_bookmark_24_gray)
                    BookmarkDatabase.invoke(holder.itemView.context).getBookmarkDao()
                        .bookmarkDelete(bookmark)
                }

            }

//                if (isBookmark){
//                holder.itemView.bookmark_btn.setImageResource(R.drawable.ic_baseline_bookmark_24)
//                isBookmark = false
//            }else{
//                holder.itemView.bookmark_btn.setImageResource(R.drawable.ic_baseline_bookmark_24_gray)
//                isBookmark = true
//            }
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(newList: List<Data>) {
        notifyDataSetChanged()
        dataList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}