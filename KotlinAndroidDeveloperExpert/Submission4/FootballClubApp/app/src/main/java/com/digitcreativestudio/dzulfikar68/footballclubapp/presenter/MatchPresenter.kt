package com.digitcreativestudio.dzulfikar68.footballclubapp.presenter

import android.util.Log
import com.digitcreativestudio.dzulfikar68.footballclubapp.CoroutineContextProvider
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.TheSportDBApi
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.EventResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.LeagueResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.SearchResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    var nullEvents: MutableList<Event> = mutableListOf()

    fun getMatchDetail(event: String?){
        view.showLoading()
        GlobalScope.launch(context.main){
//        doAsync {
            val data= gson.fromJson(apiRepository.doRequestCoroutine(TheSportDBApi.getDetailMatch(event)).await(),
                EventResponse::class.java
            )

//            uiThread {
                view.hideLoading()
                if(data.events.isNullOrEmpty()){
                    view.showMatch(nullEvents)
                    view.showNotFound()
                } else {
                    view.showMatch(data.events)
                }
//            }
        }
    }

    fun getPreviousMatchList(league: String?){
        view.showLoading()
//        doAsync {
        GlobalScope.launch(context.main){
            val data= gson.fromJson(apiRepository.doRequestCoroutine(TheSportDBApi.getPreviousMatch(league)).await(),
                EventResponse::class.java
            )

//            uiThread {
                view.hideLoading()
                if(data.events.isNullOrEmpty()){
                    view.showMatch(nullEvents)
                    view.showNotFound()
                } else {
                    view.showMatch(data.events)
                }
//            }
        }
    }

    fun getNextMatchList(league: String?){
        view.showLoading()
        GlobalScope.launch(context.main){
//        doAsync {
            val data= gson.fromJson(apiRepository.doRequestCoroutine(TheSportDBApi.getNextMatch(league)).await(),
                EventResponse::class.java
            )

//            uiThread {
                view.hideLoading()
                if(data.events.isNullOrEmpty()){
                    view.showMatch(nullEvents)
                    view.showNotFound()
                } else {
                    view.showMatch(data.events)
                }
//            }
        }
    }

    fun getSearchMatchList(keyword: String?){
        view.showLoading()
//        doAsync {
        GlobalScope.launch(context.main){
            val data= gson.fromJson(apiRepository.doRequestCoroutine(TheSportDBApi.getSearchMatch(keyword)).await(),
                SearchResponse::class.java
            )

//            uiThread {
                view.hideLoading()
                if(data.event.isNullOrEmpty()){
                    view.showMatch(nullEvents)
                    view.showNotFound()
                } else {
                    view.showMatch(data.event)
                }
//            }
        }
    }
}