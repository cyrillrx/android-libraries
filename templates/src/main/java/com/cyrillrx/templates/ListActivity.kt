package com.cyrillrx.templates

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list.*

/**
 * @author Cyril Leroux
 *         Created on 06/04/2018.
 */
abstract class ListActivity : AppCompatActivity() {

    protected abstract val adapter: BaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setupRecycler(findViewById(R.id.recycler))

        sendRequest()
    }

    protected open fun setupRecycler(recyclerView: RecyclerView) {

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        addItemDecoration(recyclerView, layoutManager)

        recyclerView.adapter = adapter
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        toolbar.title = title
    }

    override fun setTitle(titleId: Int) {
        super.setTitle(titleId)
        toolbar.setTitle(titleId)
    }

    protected open fun addItemDecoration(recyclerView: RecyclerView, layoutManager: LinearLayoutManager) {}

    protected abstract fun sendRequest()
}
