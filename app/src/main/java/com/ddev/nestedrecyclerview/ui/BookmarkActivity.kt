package com.ddev.nestedrecyclerview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddev.nestedrecyclerview.R
import com.ddev.nestedrecyclerview.adapter.Adapter
import com.ddev.nestedrecyclerview.adapter.BookmarkAdapter
import com.ddev.nestedrecyclerview.db.BookmarkDatabase
import com.ddev.nestedrecyclerview.model.Bookmark
import com.ddev.nestedrecyclerview.model.Data
import kotlinx.android.synthetic.main.activity_bookmark.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookmarkActivity : AppCompatActivity() {

    private val bookAdapter by lazy {
        BookmarkAdapter(
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        GlobalScope.launch {
            setUpRecyclerView()
        }

    }

    private suspend fun setUpRecyclerView() {
        bookmark_recyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false

        )
        bookmark_recyclerview.setHasFixedSize(true)
        val bookmarkList: List<Bookmark> = BookmarkDatabase.invoke(this).getBookmarkDao().getAllBookmark()
        bookmark_recyclerview.adapter = bookAdapter
        bookAdapter.setData(bookmarkList)

    }
}