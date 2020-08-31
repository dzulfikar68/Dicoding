package com.digitcreativestudio.dzulfikar68.footballclubapp.view

import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Player
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showListTeam(data: List<Team>)
    fun showNotFound()
}