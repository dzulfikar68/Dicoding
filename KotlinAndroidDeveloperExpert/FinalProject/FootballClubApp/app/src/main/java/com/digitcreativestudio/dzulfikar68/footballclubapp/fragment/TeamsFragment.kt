package com.digitcreativestudio.dzulfikar68.footballclubapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.activity.TeamActivity
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.StandingsAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.TeamsAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.invisible
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Table
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.TeamParcel
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.TablePresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.TeamPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.TableView
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.TeamView
import com.digitcreativestudio.dzulfikar68.footballclubapp.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class TeamsFragment : Fragment(), TeamView {

    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var loading_teams: ProgressBar
    private var teams: MutableList<Team> = mutableListOf()

    companion object {
        const val ARG_ID = "id"
        fun newInstance(id: String): TeamsFragment {
            val fragment = TeamsFragment()

            val bundle = Bundle().apply {
                putString(ARG_ID, id)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        loading_teams = view.loading

        view.list_item.layoutManager = GridLayoutManager(activity, 2)
        adapter = TeamsAdapter(teams)
        view.list_item.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        //TODO: get intent
        val id: String? = arguments?.getString(ARG_ID)
        presenter.getListTeam(id)
    }

    override fun showLoading() {
        loading_teams.visible()
    }

    override fun hideLoading() {
        loading_teams.invisible()
    }

    override fun showNotFound() {
        alert.visible()
        alert.text = "data tidak tersedia"
    }

    override fun showListTeam(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : TeamsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
//                toast(events[pos].eventId.toString())
                val intent = Intent(activity, TeamActivity::class.java)

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
                startActivity(intent)
            }
        })
    }
}