package com.digitcreativestudio.dzulfikar68.footballclubapp.view

import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Player
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showListPlayer(data: List<Player>)
    fun showNotFound()
}