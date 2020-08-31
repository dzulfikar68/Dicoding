package com.digitcreativestudio.dzulfikar68.footballclubapp.api

import java.net.URL

class ApiRepository{

    fun doRequest(url: String): String{
        return URL(url).readText()
    }
}