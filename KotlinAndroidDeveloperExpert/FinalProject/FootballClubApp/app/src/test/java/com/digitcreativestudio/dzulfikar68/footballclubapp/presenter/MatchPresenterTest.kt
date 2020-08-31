package com.digitcreativestudio.dzulfikar68.footballclubapp.presenter

import com.digitcreativestudio.dzulfikar68.footballclubapp.TestContextProvider
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.TheSportDBApi
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.EventResponse
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.SearchResponse
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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

//    @Mock
    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getMatchDetail() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val event = "441613"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestCoroutine(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchDetail(event)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatch(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getPreviousMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val league = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestCoroutine(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPreviousMatchList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatch(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getNextMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val league = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestCoroutine(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatchList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatch(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getSearchMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = SearchResponse(events)
        val keyword = "liverpool vs barcelona"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestCoroutine(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getSearchMatchList(keyword)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatch(events)
            Mockito.verify(view).hideLoading()
        }
    }
}