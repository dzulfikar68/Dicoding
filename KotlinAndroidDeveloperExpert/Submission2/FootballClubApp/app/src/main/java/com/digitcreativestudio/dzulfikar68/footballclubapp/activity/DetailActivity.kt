package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ProgressBar
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.LeaguePagerAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.TheSportDBApi
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        loading_detail = loading

        val intent = intent
        val id = intent.getStringExtra("item")
//        toast("id_match?: "+id)
//
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        presenter.getMatchDetail(id)
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
                data.teams[0].imageTeam.toString().let {
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
                data.teams[0].imageTeam.toString().let {
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

    fun convertDate(date: String?) : String?{
//        val new_time = time?.split(":")?.toTypedArray()
//        val hour: String? = new_time[0]

//        val new_date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val abc= LocalDateTime.parse(date + " " + time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
////                .of(2016, Month.APRIL, 15, 3, 15)
//            abc.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT))
//        } else {
//            //TODO("VERSION.SDK_INT < O")
//            null
//        }

        val new_date = date?.split("-")?.toTypedArray()
        if(new_date!=null){
            var str: String = ""
            for(new in new_date){
                str = "-" + str
            }
            return str
        } else{
            return null
        }
    }
}