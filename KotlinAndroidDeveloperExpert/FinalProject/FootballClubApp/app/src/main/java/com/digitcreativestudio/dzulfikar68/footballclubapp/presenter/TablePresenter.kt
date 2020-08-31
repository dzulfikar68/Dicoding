package com.digitcreativestudio.dzulfikar68.footballclubapp.presenter

import com.digitcreativestudio.dzulfikar68.footballclubapp.CoroutineContextProvider
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.TheSportDBApi
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Table
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.TableResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.TeamResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.TableView
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TablePresenter(private val view: TableView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    var nullEvents: MutableList<Table> = mutableListOf()

    fun getTableList(league: String?){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data= gson.fromJson(apiRepository.doRequestCoroutine(TheSportDBApi.getTableList(league)).await(),
                TableResponse::class.java
            )

            view.hideLoading()
            if(data.table.isNullOrEmpty()){
                view.showTableList(nullEvents)
                view.showNotFound()
            } else {
                view.showTableList(data.table)
            }
        }
    }
}