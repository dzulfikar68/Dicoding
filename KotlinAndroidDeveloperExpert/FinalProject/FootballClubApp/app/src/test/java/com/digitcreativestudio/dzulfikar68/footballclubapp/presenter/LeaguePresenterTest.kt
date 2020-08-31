package com.digitcreativestudio.dzulfikar68.footballclubapp.presenter

import com.digitcreativestudio.dzulfikar68.footballclubapp.TestContextProvider
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.EventResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.League
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.LeagueResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.LeagueView
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeaguePresenterTest {

    @Mock
    private lateinit var view: LeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    //    @Mock
    private lateinit var presenter: LeaguePresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = LeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getLeagueDetail() {
        val leagues: MutableList<League> = mutableListOf()
        val response = LeagueResponse(leagues)
        val league = "4346"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestCoroutine(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLeagueDetail(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueDetail(leagues)
            Mockito.verify(view).hideLoading()
        }
    }
}