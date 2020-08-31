package com.digitcreativestudio.dzulfikar68.footballclubapp.fragment

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ProgressBar
import com.digitcreativestudio.dzulfikar68.footballclubapp.*
import com.digitcreativestudio.dzulfikar68.footballclubapp.activity.DetailActivity
import com.digitcreativestudio.dzulfikar68.footballclubapp.activity.LeagueActivity
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.MatchAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.MatchPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next.*
import kotlinx.android.synthetic.main.fragment_next.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast

class NextFragment : Fragment(), MatchView {

    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var loading_next: ProgressBar
    private var events: MutableList<Event> = mutableListOf()

    companion object {
        const val ARG_ID = "id"
        fun newInstance(id: String): NextFragment {
            val fragment = NextFragment()

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
        val view = inflater.inflate(R.layout.fragment_next, container, false)

        loading_next = view.loading

        view.list_next_league.layoutManager = LinearLayoutManager(activity)
        adapter = MatchAdapter(events)
        view.list_next_league.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        //TODO: get intent
        val id: String? = arguments?.getString(NextFragment.ARG_ID)
        presenter.getNextMatchList(id)
    }

    override fun showLoading() {
        loading_next.visible()
    }

    override fun hideLoading() {
        loading_next.invisible()
    }

    override fun showNotFound() {
        alert.visible()
        alert.text = "data tidak tersedia"
    }

    override fun showMatch(data: List<Event>) {

//        if(!data.isNullOrEmpty()){
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
//        }
    }

}