package com.digitcreativestudio.dzulfikar68.footballclubapp.presenter

import android.util.Log
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.TheSportDBApi
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.EventResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.LeagueResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.SearchResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson
) {

    fun getMatchDetail(event: String?){
        view.showLoading()
        doAsync {
            val data= gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailMatch(event)),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if(data.events.isNullOrEmpty()){
                    view.showNotFound()
                } else {
                    view.showMatch(data.events)
                }
            }
        }
    }

    fun getPreviousMatchList(league: String?){
        view.showLoading()
        doAsync {
            val data= gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPreviousMatch(league)),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if(data.events.isNullOrEmpty()){
                    view.showNotFound()
                } else {
                    view.showMatch(data.events)
                }
            }
        }
    }

    fun getNextMatchList(league: String?){
        view.showLoading()
        doAsync {
            val data= gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch(league)),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if(data.events.isNullOrEmpty()){
                    view.showNotFound()
                } else {
                    view.showMatch(data.events)
                }
            }
        }
    }

    fun getSearchMatchList(keyword: String?){
        view.showLoading()
        doAsync {
            val data= gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchMatch(keyword)),
                SearchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if(data.event.isNullOrEmpty()){
                    view.showNotFound()
                } else {
                    view.showMatch(data.event)
                }
            }
        }
    }
}