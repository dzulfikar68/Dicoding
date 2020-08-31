package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
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
import org.jetbrains.anko.sdk27.coroutines.onClick

class LeagueActivity : AppCompatActivity(), LeagueView{

    private lateinit var presenter: LeaguePresenter
    private lateinit var data: Item
    private lateinit var description: String
    private var menuItem: Menu? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.league_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

//            back button
            android.R.id.home -> {
                finish()
                true
            }

//            detailes button
            R.id.actionbar_league_detailes -> {
                //intent ke detail league
                toast("intent to detil league")
                startActivity<ListTeamActivity>("item" to data)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_league)

        val intent = intent
//        val data: String? = intent.getStringExtra("id_league")
        data = intent.getParcelableExtra<Item>("item")
//        toast("id_league?: "+data.id)

        supportActionBar?.title = "League Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //viewpagger
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
        description = data[0].descriptionInEnglish.toString()
        description_league.text = data[0].descriptionInEnglish
        country_league.text = "@"+data[0].leagueCountry
        formed_league.text = "Since "+data[0].formedYear
        website_league.text = data[0].linkWebsite

        description_league.onClick {
            alert(description) {
                noButton {}
            }.show()
        }
    }
}