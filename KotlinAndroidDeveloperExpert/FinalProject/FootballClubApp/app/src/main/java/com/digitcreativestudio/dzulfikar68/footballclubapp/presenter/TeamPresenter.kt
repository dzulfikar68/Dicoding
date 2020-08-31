package com.digitcreativestudio.dzulfikar68.footballclubapp.presenter

import com.digitcreativestudio.dzulfikar68.footballclubapp.CoroutineContextProvider
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.TheSportDBApi
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.*
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(private val view: TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    var nullEvents: MutableList<Team> = mutableListOf()

    fun getSearchTeam(keyword: String?){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data= gson.fromJson(apiRepository.doRequestCoroutine(TheSportDBApi.getSearchTeam(keyword)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            if(data.teams.isNullOrEmpty()){
                view.showListTeam(nullEvents)
                view.showNotFound()
            } else {
                view.showListTeam(data.teams)
            }
        }
    }

    fun getListTeam(league: String?){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data= gson.fromJson(apiRepository.doRequestCoroutine(TheSportDBApi.getListTeam(league)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            if(data.teams.isNullOrEmpty()){
                view.showListTeam(nullEvents)
                view.showNotFound()
            } else {
                view.showListTeam(data.teams)
            }
        }
    }
}