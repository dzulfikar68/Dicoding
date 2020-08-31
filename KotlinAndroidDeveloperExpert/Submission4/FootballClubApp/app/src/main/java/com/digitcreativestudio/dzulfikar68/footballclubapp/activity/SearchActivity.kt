package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.MainAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.MatchAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.fragment.NextFragment
import com.digitcreativestudio.dzulfikar68.footballclubapp.invisible
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Item
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.MatchPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.digitcreativestudio.dzulfikar68.footballclubapp.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.toast
import java.lang.Boolean.TRUE

class SearchActivity : AppCompatActivity(), MatchView {

    private lateinit var listEvent: RecyclerView
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var search: TextView
    private lateinit var alert_not_found: TextView
    private lateinit var loading_search: ProgressBar
    private var events: MutableList<Event> = mutableListOf()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
//            back button
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Search Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)

            search = textView {
                text = "keyword"
                typeface = Typeface.DEFAULT_BOLD
                textSize = 16f
            }.lparams{
                height = wrapContent
                width = matchParent
                bottomMargin = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)
            }

            loading_search = progressBar{

            }.lparams {
                width = matchParent
                height = wrapContent
            }

            alert_not_found = textView{
                visibility = GONE
            }.lparams {
                gravity = Gravity.CENTER_HORIZONTAL
                topMargin = dip(10)
            }

            listEvent = recyclerView{
                id = R.id.list_search
                lparams(width = matchParent, height = wrapContent)
                layoutManager = LinearLayoutManager(context)
            }
        }

//        listEvent.layoutManager = GridLayoutManager(this, 2)
//        listEvent.adapter =
//            MatchAdapter(events)
        adapter = MatchAdapter(events)
        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        //TODO: get intent
        val intent = intent
        val keyword = intent.getStringExtra("item")
        search.text = "keyword: " + keyword
//        toast("keyword?: "+keyword)
        presenter.getSearchMatchList(keyword)
    }


    override fun showLoading() {
        loading_search.visible()
    }

    override fun hideLoading() {
        loading_search.invisible()
    }

    override fun showNotFound() {
        alert_not_found.visible()
        alert_not_found.text = "data tidak tersedia"
    }

    override fun showMatch(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : MatchAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
//                toast(events[pos].eventId.toString())
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra("item", events[pos].eventId)
                startActivity(intent)
            }
        })
    }
}