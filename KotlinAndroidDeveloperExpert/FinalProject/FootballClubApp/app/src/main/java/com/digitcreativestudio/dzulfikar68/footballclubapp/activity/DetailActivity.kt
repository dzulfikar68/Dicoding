package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.LeaguePagerAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.TheSportDBApi
import com.digitcreativestudio.dzulfikar68.footballclubapp.database
import com.digitcreativestudio.dzulfikar68.footballclubapp.invisible
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.*
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.LeaguePresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.MatchPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.LeagueView
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.digitcreativestudio.dzulfikar68.footballclubapp.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_league.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class DetailActivity : AppCompatActivity(), MatchView{

    private lateinit var presenter: MatchPresenter
    private lateinit var loading_detail: ProgressBar

    private lateinit var event: Event
    private lateinit var id: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
//            back button
            android.R.id.home -> {
                finish()
                true
            }

//            favorite button
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use{
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})", "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        } else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
        }
    }

    private fun addToFavorite(){
        try{
            database.use{
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to event.eventId,
                    Favorite.MATCH_DATE to event.matchDate,
                    Favorite.HOME_TEAM to event.homeTeam,
                    Favorite.HOME_SCORE to event.homeScore,
                    Favorite.AWAY_TEAM to event.awayTeam,
                    Favorite.AWAY_SCORE to event.awayScore
                )
            }
            toast("Added to favorite event")
//            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
//            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try{
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})", "id" to id)
            }
            toast("Removed to favorite event")
//            snackbar(swipeRefresh, "Removed to favorite").show()
        }catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
//            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        loading_detail = loading

        val intent = intent
        id = intent.getStringExtra("item")
//        toast("id_match?: "+id)
//
        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        presenter.getMatchDetail(id)

        supportActionBar?.title = "Event Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showLoading() {
        loading_detail.visible()
    }

    override fun hideLoading() {
        loading_detail.invisible()
    }

    override fun showNotFound() {
        alert.visible()
        alert.text = "data tidak tersedia"
    }

    override fun showMatch(data: List<Event>) {
        event = Event(data[0].eventId,
            data[0].matchDate,
            data[0].homeTeam,
            data[0].homeScore,
            data[0].awayTeam,
            data[0].awayScore)

        score_event.text =  data[0].homeScore + " - " + data[0].awayScore
        date_detail.text = data[0].matchDate
        time_detail.text = convertTime(data[0].matchTime)
        round_detail.text = data[0].leagueRound

        name_home.text = data[0].homeTeam
        Shots1.text = data[0].homeShots
        GoalDetails1.text = data[0].homeGoalDetails?.replace(";", "\n")?.replace(":", " : ")
        RedCards1.text = data[0].homeRedCards?.replace(";", ", ")
        YellowCards1.text = data[0].homeYellowCards?.replace(";", ", ")
        LineupGoalkeeper1.text = data[0].homeLineupGoalkeeper?.replace(";", ".")
        LineupDefense1.text = data[0].homeLineupDefense?.replace(";", ", ")
        LineupMidfield1.text = data[0].homeLineupMidfield?.replace(";", ", ")
        LineupForward1.text = data[0].homeLineupForward?.replace(";", ", ")
        LineupSubstitutes1.text = data[0].homeLineupSubstitutes?.replace(";", ", ")

        name_away.text = data[0].awayTeam
        Shots1.text = data[0].awayShots
        GoalDetails2.text = data[0].awayGoalDetails?.replace(";", "\n")?.replace(":", " : ")
        RedCards2.text = data[0].awayRedCards?.replace(";", ", ")
        YellowCards2.text = data[0].awayYellowCards?.replace(";", ", ")
        LineupGoalkeeper2.text = data[0].awayLineupGoalkeeper?.replace(";", "")
        LineupDefense2.text = data[0].awayLineupDefense?.replace(";", ", ")
        LineupMidfield2.text = data[0].awayLineupMidfield?.replace(";", ", ")
        LineupForward2.text = data[0].awayLineupForward?.replace(";", ", ")
        LineupSubstitutes2.text = data[0].awayLineupSubstitutes?.replace(";", ", ")

        setImageHomeTeam(data[0].homeId)
        setImageAwayTeam(data[0].awayId)
    }

    fun setImageHomeTeam(id: String?){
        doAsync {
            val request = ApiRepository()
            val gson = Gson()

            val data= gson.fromJson(request.doRequest(TheSportDBApi.getImageTeam(id)),
                TeamResponse::class.java
            )

            uiThread {
//                toast("IM-1 " + data.teams[0].imageTeam.toString())
                data.teams[0].teamBadge.toString().let {
                    Picasso.get().load(it).into(image_home)
                }
            }
        }
    }

    fun setImageAwayTeam(id: String?){
        doAsync {
            val request = ApiRepository()
            val gson = Gson()

            val data = gson.fromJson(request.doRequest(TheSportDBApi.getImageTeam(id)),
                TeamResponse::class.java
            )

            uiThread {
//                toast("IM-2 " + data.teams[0].imageTeam.toString())
                data.teams[0].teamBadge.toString().let {
                    Picasso.get().load(it).into(image_away)
                }
            }
        }
    }

    fun convertTime(time: String?) : String?{
        if(time?.length==8){
            return time.removeRange(5, 8)
        } else{
            return time?.removeRange(5, 14)
        }
    }
}