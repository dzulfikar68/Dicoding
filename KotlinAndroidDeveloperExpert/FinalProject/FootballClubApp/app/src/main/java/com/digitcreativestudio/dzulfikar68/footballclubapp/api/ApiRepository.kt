package com.digitcreativestudio.dzulfikar68.footballclubapp.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepository{

    fun doRequest(url: String): String{
        return URL(url).readText()
    }

    fun doRequestCoroutine(url: String): Deferred<String> = GlobalScope.async{
        URL(url).readText()
    }
}