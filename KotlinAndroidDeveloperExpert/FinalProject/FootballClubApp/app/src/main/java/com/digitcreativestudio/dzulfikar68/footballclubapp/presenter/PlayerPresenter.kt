package com.digitcreativestudio.dzulfikar68.footballclubapp.presenter

import com.digitcreativestudio.dzulfikar68.footballclubapp.CoroutineContextProvider
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.TheSportDBApi
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Player
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.PlayerResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.TeamResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.PlayerView
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    var nullEvents: MutableList<Player> = mutableListOf()

    fun getListPlayer(team: String?){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data= gson.fromJson(apiRepository.doRequestCoroutine(TheSportDBApi.getListPlayer(team)).await(),
                PlayerResponse::class.java
            )

            view.hideLoading()
            if(data.player.isNullOrEmpty()){
                view.showListPlayer(nullEvents)
                view.showNotFound()
            } else {
                view.showListPlayer(data.player)
            }
        }
    }
}