package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.MatchAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.TeamsAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.invisible
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.TeamParcel
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.MatchPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.TeamPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.TeamView
import com.digitcreativestudio.dzulfikar68.footballclubapp.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SearchTeamActivity : AppCompatActivity(), TeamView {

    private lateinit var listTeam: RecyclerView
    private lateinit var adapter: TeamsAdapter
    private lateinit var presenter: TeamPresenter
    private lateinit var search: TextView
    private lateinit var alert_not_found: TextView
    private lateinit var loading_search: ProgressBar
    private var teams: MutableList<Team> = mutableListOf()

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

        supportActionBar?.title = "Search Team Detail"
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
                visibility = View.GONE
            }.lparams {
                gravity = Gravity.CENTER_HORIZONTAL
                topMargin = dip(10)
            }

            listTeam = recyclerView{
                id = R.id.list_search
                lparams(width = matchParent, height = wrapContent)
                layoutManager = LinearLayoutManager(context)
            }
        }

//        listEvent.layoutManager = GridLayoutManager(this, 2)
//        listEvent.adapter =
//            MatchAdapter(events)
        adapter = TeamsAdapter(teams)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        //TODO: get intent
        val intent = intent
        val keyword = intent.getStringExtra("item")
        search.text = "keyword: " + keyword
//        toast("keyword?: "+keyword)
        presenter.getSearchTeam(keyword)
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

    override fun showListTeam(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : TeamsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
//                toast(events[pos].eventId.toString())
                val intent = Intent(applicationContext, TeamActivity::class.java)

                val parcel = TeamParcel(
                    teams[pos].teamId,
                    teams[pos].teamName,
                    teams[pos].teamBadge,
                    teams[pos].teamJersey,
                    teams[pos].teamCountry,
                    teams[pos].teamStadium,
                    teams[pos].teamFormedYear,
                    teams[pos].teamWebsite,
                    teams[pos].teamDescription
                )

                intent.putExtra("item", parcel)
                ctx.startActivity(intent)
            }
        })
    }
}