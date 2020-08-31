package com.digitcreativestudio.dzulfikar68.footballclubapp.view

import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<Event>)
    fun showNotFound()
}