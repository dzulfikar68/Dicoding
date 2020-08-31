package com.digitcreativestudio.dzulfikar68.footballclubapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.activity.DetailActivity
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.MatchAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.invisible
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Item
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.MatchPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.digitcreativestudio.dzulfikar68.footballclubapp.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_previous.*
import kotlinx.android.synthetic.main.fragment_previous.view.*
import org.jetbrains.anko.support.v4.toast

class PreviousFragment() : Fragment(), MatchView {

    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var loading_previous: ProgressBar
    private var events: MutableList<Event> = mutableListOf()

    companion object {
        const val ARG_ID = "id"
        fun newInstance(id: String): PreviousFragment {
            val fragment = PreviousFragment()

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
        val view = inflater.inflate(R.layout.fragment_previous, container, false)

        loading_previous = view.loading

        view.list_previous_league.layoutManager = LinearLayoutManager(activity)
        adapter = MatchAdapter(events)
        view.list_previous_league.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        //TODO: get intent
        val id: String? = arguments?.getString(ARG_ID)
        presenter.getPreviousMatchList(id)
    }

    override fun showLoading() {
        loading_previous.visible()
    }

    override fun hideLoading() {
        loading_previous.invisible()
    }

    override fun showNotFound() {
        alert.visible()
        alert.text = "data tidak tersedia"
    }

    override fun showMatch(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : MatchAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
//                toast(events[pos].eventId.toString())
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("item", events[pos].eventId)
                startActivity(intent)
            }
        })
    }

}