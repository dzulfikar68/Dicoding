package com.digitcreativestudio.dzulfikar68.footballclubapp.view

import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Table

interface TableView {
    fun showLoading()
    fun hideLoading()
    fun showTableList(data: List<Table>)
    fun showNotFound()
}