package com.digitcreativestudio.dzulfikar68.footballclubapp.presenter

import android.util.Log
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.TheSportDBApi
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.EventResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.LeagueResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.LeagueView
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeaguePresenter(private val view: LeagueView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson
) {

    fun getLeagueDetail(league: String?){
        view.showLoading()
        doAsync {
            val data= gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailLeague(league)),
                LeagueResponse::class.java
            )

            if(data.leagues.isEmpty()){
                Log.e("LEAGUE_NOTHING", data.leagues.toString())
            } else{
                uiThread {
                    view.hideLoading()
                    view.showLeagueDetail(data.leagues)
                }
            }
        }
    }
}