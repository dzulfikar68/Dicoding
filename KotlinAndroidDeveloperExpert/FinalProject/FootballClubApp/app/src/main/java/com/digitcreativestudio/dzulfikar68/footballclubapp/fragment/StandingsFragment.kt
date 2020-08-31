package com.digitcreativestudio.dzulfikar68.footballclubapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.StandingsAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.invisible
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Table
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.TablePresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.TeamPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.TableView
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.TeamView
import com.digitcreativestudio.dzulfikar68.footballclubapp.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class StandingsFragment : Fragment(), TableView {

    private lateinit var presenter: TablePresenter
    private lateinit var adapter: StandingsAdapter
    private lateinit var loading_standings: ProgressBar
    private var teams: MutableList<Table> = mutableListOf()

    companion object {
        const val ARG_ID = "id"
        fun newInstance(id: String): StandingsFragment {
            val fragment = StandingsFragment()

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
        val view = inflater.inflate(R.layout.fragment_table, container, false)

        loading_standings = view.loading

        view.list_item.layoutManager = LinearLayoutManager(activity)
        adapter = StandingsAdapter(teams)
        view.list_item.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TablePresenter(this, request, gson)

        //TODO: get intent
        val id: String? = arguments?.getString(ARG_ID)
        presenter.getTableList(id)
    }

    override fun showLoading() {
        loading_standings.visible()
    }

    override fun hideLoading() {
        loading_standings.invisible()
    }

    override fun showNotFound() {
        alert.visible()
        alert.text = "data tidak tersedia"
    }

    override fun showTableList(data: List<Table>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}