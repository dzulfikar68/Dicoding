package com.digitcreativestudio.dzulfikar68.footballclubapp.view

import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.League

interface LeagueView{
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetail(data: List<League>)
}