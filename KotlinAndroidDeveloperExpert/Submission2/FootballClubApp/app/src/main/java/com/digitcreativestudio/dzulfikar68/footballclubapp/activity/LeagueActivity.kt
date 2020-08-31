package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Item
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.LeaguePagerAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.fragment.NextFragment
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.League
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.LeaguePresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.MatchPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.LeagueView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_league.*
import org.jetbrains.anko.*

class LeagueActivity : AppCompatActivity(), LeagueView{

    private lateinit var presenter: LeaguePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_league)

        val intent = intent
//        val data: String? = intent.getStringExtra("id_league")
        val data = intent.getParcelableExtra<Item>("item")
//        toast("id_league?: "+data.id)

        viewpager_league.adapter =
            LeaguePagerAdapter(supportFragmentManager, data.id)
        tabs_main.setupWithViewPager(viewpager_league)

        val request = ApiRepository()
        val gson = Gson()
        presenter = LeaguePresenter(this, request, gson)
        presenter.getLeagueDetail(data.id)
    }

    override fun showLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLeagueDetail(data: List<League>) {
        name_league.text = data[0].leagueName
        data[0].leagueBadge.let {
            Picasso.get().load(it).into(image_league)
        }
        description_league.text = data[0].descriptionInEnglish
        country_league.text = "@"+data[0].leagueCountry
        formed_league.text = "Since "+data[0].formedYear
        website_league.text = data[0].linkWebsite
    }
}